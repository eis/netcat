import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetCat {
	public static void main(String[] args) throws Exception {
		if (args.length > 2 && args[0].equalsIgnoreCase("-l") && numeric(args[2])) {
			int port = Integer.parseInt(args[2]);
			listen(port);
		} else {
			if (args.length > 2 && args[0].equalsIgnoreCase("-p") && numeric(args[1])) {
				int port = Integer.parseInt(args[1]);
				connect(args[2], port);
			} else {
				System.out.println("Usage: netcat -l -p <port>");
				System.out.println("       (to listen to a port)");
				System.out.println("   or  netcat -p <port> <host>");
				System.out.println("       (to connect to a port)");
			}
		}
	}

	static boolean numeric(String number) {
		if (number == null || number.length() == 0) {
			return false;
		}
		for(char ourChar : number.toCharArray()) {
			if (ourChar < '0' || ourChar > '9') {
				return false;
			}
		}
		return true;
	}

	private static void connect(String host, int port) throws Exception {
		System.err.println("Connecting to " + host + " port " + port);
		final Socket socket = new Socket(host, port);
		System.err.println("Connected");

		InputStream input1 = System.in;
		OutputStream output1 = socket.getOutputStream();
		Thread thread1 = new Thread(pipe(input1, output1));
		thread1.start();
		thread1.join();
		socket.shutdownOutput();
	}

	private static void listen(int port) throws Exception {
		System.err.println("Listening at port " + port);
		ServerSocket serverSocket = new ServerSocket(port);
		Socket socket = serverSocket.accept();
		System.err.println("Accepted");

		InputStream input2 = socket.getInputStream();
		PrintStream output2 = System.out;
		Thread thread2 = new Thread(pipe(input2, output2));
		thread2.start();
		thread2.join();
		socket.shutdownOutput();
	}

	private static Runnable pipe(final InputStream input, final OutputStream output) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					final int BUFFER_SIZE = 8192;
					byte[] bb = new byte[BUFFER_SIZE];
					int read;
					while ((read = input.read(bb)) > -1) {
						output.write(bb, 0, read);
						output.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		};
	}
}

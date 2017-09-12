Netcat tool in java
===================

<pre>
usage: netcat [OPTIONS] <HOST>
 -l,--listen       listen mode
 -p,--port <arg>   port number
</pre>

Listen
------
<pre>
java -jar target/netcat.jar -p 1009 -l
</pre>

Connect
-------
<pre>
java -jar target/netcat.jar -p 1009 localhost
</pre>

Receive a file
--------------
<pre>
java -jar target/netcat.jar -l -p 1009 > pom.xml
</pre>

Send a file
-----------
<pre>
java -jar target/netcat.jar -p 1009 localhost < pom.xml
</pre>

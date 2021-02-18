Netcat tool in java
===================

<pre>
Usage: netcat -l -p &lt;port&gt;
       (to listen to a port)
   or  netcat -p &lt;port&gt; &lt;host&gt;
       (to connect to a port)
</pre>

Listen
------
<pre>
java NetCat -l -p 1009
</pre>
<pre>
java -jar target/netcat.jar -l -p 1009
</pre>

Connect
-------
<pre>
java NetCat -p 1009 localhost
</pre>
<pre>
java -jar target/netcat.jar -p 1009 localhost
</pre>

Receive a file
--------------
<pre>
java NetCat -l -p 1009 > pom.xml
</pre>
<pre>
java -jar target/netcat.jar -l -p 1009 > pom.xml
</pre>

Send a file
-----------
<pre>
java NetCat -p 1009 localhost < pom.xml
</pre>
<pre>
java -jar target/netcat.jar -p 1009 localhost < pom.xml
</pre>

Acknowledgements
----------------
Borrowing from [seregamorph/netcat](https://github.com/seregamorph/netcat)

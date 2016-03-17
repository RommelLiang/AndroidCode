一，网络编程中两个主要的问题

一个是如何准确的定位网络上一台或多台主机，另一个就是找到主机后如何可靠高效的进行数据传输。

在TCP/IP协议中IP层主要负责网络主机的定位，数据传输的路由，由IP地址可以唯一地确定Internet上的一台主机。

而TCP层则提供面向应用的可靠（tcp）的或非可靠（UDP）的数据传输机制，这是网络编程的主要对象，一般不需要关心IP层是如何处理数据的。

目前较为流行的网络编程模型是客户机/服务器（C/S）结构。即通信双方一方作为服务器等待客户提出请求并予以响应。客户则在需要服务时向服务器提 出申请。服务器一般作为守护进程始终运行，监听网络端口，一旦有客户请求，就会启动一个服务进程来响应该客户，同时自己继续监听服务端口，使后来的客户也 能及时得到服务。

二，两类传输协议：TCP；UDP

TCP是Tranfer Control Protocol的 简称，是一种面向连接的保证可靠传输的协议。通过TCP协议传输，得到的是一个顺序的无差错的数据流。发送方和接收方的成对的两个socket之间必须建 立连接，以便在TCP协议的基础上进行通信，当一个socket（通常都是server socket）等待建立连接时，另一个socket可以要求进行连接，一旦这两个socket连接起来，它们就可以进行双向数据传输，双方都可以进行发送 或接收操作。

UDP是User Datagram Protocol的简称，是一种无连接的协议，每个数据报都是一个独立的信息，包括完整的源地址或目的地址，它在网络上以任何可能的路径传往目的地，因此能否到达目的地，到达目的地的时间以及内容的正确性都是不能被保证的。

比较：

UDP：1，每个数据报中都给出了完整的地址信息，因此无需要建立发送方和接收方的连接。

            2，UDP传输数据时是有大小限制的，每个被传输的数据报必须限定在64KB之内。

           3，UDP是一个不可靠的协议，发送方所发送的数据报并不一定以相同的次序到达接收方

TCP：1，面向连接的协议，在socket之间进行数据传输之前必然要建立连接，所以在TCP中需要连接

                时间。

            2，TCP传输数据大小限制，一旦连接建立起来，双方的socket就可以按统一的格式传输大的  

                    数据。

             3，TCP是一个可靠的协议，它确保接收方完全正确地获取发送方所发送的全部数据。

应用：

1，TCP在网络通信上有极强的生命力，例如远程连接（Telnet）和文件传输（FTP）都需要不定长度的数据被可靠地传输。但是可靠的传输是要付出代价的，对数据内容正确性的检验必然占用计算机的处理时间和网络的带宽，因此TCP传输的效率不如UDP高。

2，UDP操作简单，而且仅需要较少的监护，因此通常用于局域网高可靠性的分散系统中client/server应用程序。例如视频会议系统，并不要求音频视频数据绝对的正确，只要保证连贯性就可以了，这种情况下显然使用UDP会更合理一些。

三，基于Socket的java网络编程

1，什么是Socket

网络上的两个程序通过一个双向的通讯连接实现数据的交换，这个双向链路的一端称为一个Socket。Socket通常用来实现客户方和服务方的连接。Socket是TCP/IP协议的一个十分流行的编程界面，一个Socket由一个IP地址和一个端口号唯一确定。

但是，Socket所支持的协议种类也不光TCP/IP一种，因此两者之间是没有必然联系的。在Java环境下，Socket编程主要是指基于TCP/IP协议的网络编程。

2，Socket通讯的过程

Server端Listen(监听)某个端口是否有连接请求，Client端向Server 端发出Connect(连接)请求，Server端向Client端发回Accept（接受）消息。一个连接就建立起来了。Server端和Client 端都可以通过Send，Write等方法与对方通信。

对于一个功能齐全的Socket，都要包含以下基本结构，其工作过程包含以下四个基本的步骤：

　　（1） 创建Socket；

　　（2） 打开连接到Socket的输入/出流；

　　（3） 按照一定的协议对Socket进行读/写操作；

　　（4） 关闭Socket.（在实际应用中，并未使用到显示的close，虽然很多文章都推荐如此，不过在我的程序中，可能因为程序本身比较简单，要求不高，所以并未造成什么影响。）

3，创建Socket

创建Socket

java在包java.net中提供了两个类Socket和ServerSocket，分别用来表示双向连接的客户端和服务端。这是两个封装得非常好的类，使用很方便。其构造方法如下：

　　Socket(InetAddress address, int port);

　　Socket(InetAddress address, int port, boolean stream);

　　Socket(String host, int prot);

　　Socket(String host, int prot, boolean stream);

　　Socket(SocketImpl impl)

　　Socket(String host, int port, InetAddress localAddr, int localPort)

　　Socket(InetAddress address, int port, InetAddress localAddr, int localPort)

　　ServerSocket(int port);

　　ServerSocket(int port, int backlog);

　　ServerSocket(int port, int backlog, InetAddress bindAddr)

　　其中address、host和port分别是双向连接中另一方的IP地址、主机名和端 口号，stream指明socket是流socket还是数据报socket，localPort表示本地主机的端口号，localAddr和 bindAddr是本地机器的地址（ServerSocket的主机地址），impl是socket的父类，既可以用来创建serverSocket又可 以用来创建Socket。count则表示服务端所能支持的最大连接数。例如：学习视频网 http://www.xxspw.com

　　Socket client = new Socket("127.0.01.", 80);

　　ServerSocket server = new ServerSocket(80);

　　注意，在选择端口时，必须小心。每一个端口提供一种特定的服务，只有给出正确的端口，才 能获得相应的服务。0~1023的端口号为系统所保留，例如http服务的端口号为80,telnet服务的端口号为21,ftp服务的端口号为23, 所以我们在选择端口号时，最好选择一个大于1023的数以防止发生冲突。

　　在创建socket时如果发生错误，将产生IOException，在程序中必须对之作出处理。所以在创建Socket或ServerSocket是必须捕获或抛出例外。

4，简单的Client/Server程序

1. 客户端程序

　　import java.io.*;

　　import java.net.*;

　　public class TalkClient {

　　　　public static void main(String args[]) {

　　　　　　try{

　　　　　　　　Socket socket=new Socket("127.0.0.1",4700);

　　　　　　　　//向本机的4700端口发出客户请求

　　　　　　　　BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));

　　　　　　　　//由系统标准输入设备构造BufferedReader对象

　　　　　　　　PrintWriter os=new PrintWriter(socket.getOutputStream());

　　　　　　　　//由Socket对象得到输出流，并构造PrintWriter对象

　　　　　　　　BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));

　　　　　　　　//由Socket对象得到输入流，并构造相应的BufferedReader对象

　　　　　　　　String readline;

　　　　　　　　readline=sin.readLine(); //从系统标准输入读入一字符串

　　　　　　　　while(!readline.equals("bye")){

　　　　　　　　//若从标准输入读入的字符串为 "bye"则停止循环

　　　　　　　　　　os.println(readline);

　　　　　　　　　　//将从系统标准输入读入的字符串输出到Server

　　　　　　　　　　os.flush();

　　　　　　　　　　//刷新输出流，使Server马上收到该字符串

　　　　　　　　　　System.out.println("Client:"+readline);

　　　　　　　　　　//在系统标准输出上打印读入的字符串

　　　　　　　　　　System.out.println("Server:"+is.readLine());

　　　　　　　　　　//从Server读入一字符串，并打印到标准输出上

　　　　　　　　　　readline=sin.readLine(); //从系统标准输入读入一字符串

　　　　　　　　} //继续循环

　　　　　　　　os.close(); //关闭Socket输出流

　　　　　　　　is.close(); //关闭Socket输入流

　　　　　　　　socket.close(); //关闭Socket

　　　　　　}catch(Exception e) {

　　　　　　　　System.out.println("Error"+e); //出错，则打印出错信息

　　　　　　}

　　}

}

　2. 服务器端程序

　　import java.io.*;

　　import java.net.*;

　　import java.applet.Applet;

　　public class TalkServer{

　　　　public static void main(String args[]) {

　　　　　　try{

　　　　　　　　ServerSocket server=null;

　　　　　　　　try{

　　　　　　　　　　server=new ServerSocket(4700);

　　　　　　　　//创建一个ServerSocket在端口4700监听客户请求

　　　　　　　　}catch(Exception e) {

　　　　　　　　　　System.out.println("can not listen to:"+e);

　　　　　　　　//出错，打印出错信息

　　　　　　　　}

　　　　　　　　Socket socket=null;

　　　　　　　　try{

　　　　　　　　　　socket=server.accept();

　　　　　　　　　　//使用accept()阻塞等待客户请求，有客户

　　　　　　　　　　//请求到来则产生一个Socket对象，并继续执行

　　　　　　　　}catch(Exception e) {

　　　　　　　　　　System.out.println("Error."+e);

　　　　　　　　　　//出错，打印出错信息

　　　　　　　　}

　　　　　　　　String line;

　　　　　　　　BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));

　　　　　　　　　//由Socket对象得到输入流，并构造相应的BufferedReader对象

　　　　　　　　PrintWriter os=newPrintWriter(socket.getOutputStream());

　　　　　　　　　//由Socket对象得到输出流，并构造PrintWriter对象

　　　　　　　　BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));

　　　　　　　　　//由系统标准输入设备构造BufferedReader对象

　　　　　　　　System.out.println("Client:"+is.readLine());

　　　　　　　　//在标准输出上打印从客户端读入的字符串

　　　　　　　　line=sin.readLine();

　　　　　　　　//从标准输入读入一字符串

　　　　　　　　while(!line.equals("bye")){

　　　　　　　　//如果该字符串为 "bye"，则停止循环

　　　　　　　　　　os.println(line);

　　　　　　　　　　//向客户端输出该字符串

　　　　　　　　　　os.flush();

　　　　　　　　　　//刷新输出流，使Client马上收到该字符串

　　　　　　　　　　System.out.println("Server:"+line);

　　　　　　　　　　//在系统标准输出上打印读入的字符串

　　　　　　　　　　System.out.println("Client:"+is.readLine());

　　　　　　　　　　//从Client读入一字符串，并打印到标准输出上

　　　　　　　　　　line=sin.readLine();

　　　　　　　　　　//从系统标准输入读入一字符串

　　　　　　　　} 　//继续循环

　　　　　　　　os.close(); //关闭Socket输出流

　　　　　　　　is.close(); //关闭Socket输入流

　　　　　　　　socket.close(); //关闭Socket

　　　　　　　　server.close(); //关闭ServerSocket

　　　　　　}catch(Exception e){

　　　　　　　　System.out.println("Error:"+e);

　　　　　　　　//出错，打印出错信息

　　　　　　}

　　　　}

　　}

5，支持多客户的client/server程序

前面的Client/Server程序只能实现Server和一个客户的对话。在实际应用 中，往往是在服务器上运行一个永久的程序，
它可以接收来自其他多个客户端的请求，提供相应的服务。为了实现在服务器方给多个客户提供服务的功能，需要对上
面的程序进行改造，利用多线程实现多客户机制。服务器总是在指定的端口上监听是否有客户请求，一旦监听到客户请求，
服务器就会启动一个专门的服务线程来响 应该客户的请求，而服务器本身在启动完线程之后马上又进入监听状态，等待下一个客户的到来。
----------------------------------------------------------------------------------------------------------------------
服务器端（Server）非多线程：
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(5678);
		Socket client = server.accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		PrintWriter out = new PrintWriter(client.getOutputStream());
		while (true) {
			String str = in.readLine();
			System.out.println(str);
			out.println("has receive....");
			out.flush();
			if (str.equals("end"))
				break;
		}
		client.close();
	}
}
客户端（Client） 
package com.zeph.serverclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MyClient {
	static Socket server;

	public static void main(String[] args) throws Exception {
		server = new Socket(InetAddress.getLocalHost(), 5678);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String str = wt.readLine();
			out.println(str);
			out.flush();
			if (str.equals("end")) {
				break;
			}
			System.out.println(in.readLine());
		}
		server.close();
	}
}
服务器端（Server）多线程
package com.zeph.multiclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiClient extends Thread {
	private Socket client;

	public MultiClient(Socket c) {
		this.client = c;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream());
			// Mutil User but can't parallel

			while (true) {
				String str = in.readLine();
				System.out.println(str);
				out.println("has receive....");
				out.flush();
				if (str.equals("end"))
					break;
			}
			client.close();
		} catch (IOException ex) {
		} finally {
		}
	}

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(5678);
		while (true) {
			// transfer location change Single User or Multi User

			MultiClient mc = new MultiClient(server.accept());
			mc.start();
		}
	}
}
------------------------------------------------------------------------------------------------------------
 对于Java Socket编程而言，有两个概念，一个是ServerSocket，一个是Socket。服务端和客户端之间通过Socket建立连接，之后它们就可以进行通信了。首先ServerSocket将在服务端监听某个端口，当发现客户端有Socket来试图连接它时，它会accept该Socket的连接请求，同时在服务端建立一个对应的Socket与之进行通信。这样就有两个Socket了，客户端和服务端各一个。

       对于Socket之间的通信其实很简单，服务端往Socket的输出流里面写东西，客户端就可以通过Socket的输入流读取对应的内容。Socket与Socket之间是双向连通的，所以客户端也可以往对应的Socket输出流里面写东西，然后服务端对应的Socket的输入流就可以读出对应的内容。下面来看一些服务端与客户端通信的例子：

      1、客户端写服务端读

       服务端代码

 



Java代码 复制代码 收藏代码
1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //为了简单起见，所有的异常信息都往外抛  
5.      int port = 8899;  
6.      //定义一个ServerSocket监听在端口8899上  
7.      ServerSocket server = new ServerSocket(port);  
8.      //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
9.      Socket socket = server.accept();  
10.      //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。  
11.      Reader reader = new InputStreamReader(socket.getInputStream());  
12.      char chars[] = new char[64];  
13.      int len;  
14.      StringBuilder sb = new StringBuilder();  
15.      while ((len=reader.read(chars)) != -1) {  
16.         sb.append(new String(chars, 0, len));  
17.      }  
18.      System.out.println("from client: " + sb);  
19.      reader.close();  
20.      socket.close();  
21.      server.close();  
22.   }  
23.     
24.}  


        

服务端从Socket的InputStream中读取数据的操作也是阻塞式的，如果从输入流中没有读取到数据程序会一直在那里不动，直到客户端往Socket的输出流中写入了数据，或关闭了Socket的输出流。当然，对于客户端的Socket也是同样如此。在操作完以后，整个程序结束前记得关闭对应的资源，即关闭对应的IO流和Socket。

 

       客户端代码



Java代码 复制代码 收藏代码
1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //为了简单起见，所有的异常都直接往外抛  
5.      String host = "127.0.0.1";  //要连接的服务端IP地址  
6.      int port = 8899;   //要连接的服务端对应的监听端口  
7.      //与服务端建立连接  
8.      Socket client = new Socket(host, port);  
9.      //建立连接后就可以往服务端写数据了  
10.      Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.flush();//写完后要记得flush  
13.      writer.close();  
14.      client.close();  
15.   }  
16.     
17.}  


       
对于客户端往Socket的输出流里面写数据传递给服务端要注意一点，如果写操作之后程序不是对应着输出流的关闭，而是进行其他阻塞式的操作（比如从输入流里面读数据），记住要flush一下，只有这样服务端才能收到客户端发送的数据，否则可能会引起两边无限的互相等待。在稍后讲到客户端和服务端同时读和写的时候会说到这个问题。 
 

      2、客户端和服务端同时读和写

       前面已经说了Socket之间是双向通信的，它既可以接收数据，同时也可以发送数据。

       服务端代码

 



Java代码 复制代码 收藏代码
1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //为了简单起见，所有的异常信息都往外抛  
5.      int port = 8899;  
6.      //定义一个ServerSocket监听在端口8899上  
7.      ServerSocket server = new ServerSocket(port);  
8.      //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
9.      Socket socket = server.accept();  
10.      //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。  
11.      Reader reader = new InputStreamReader(socket.getInputStream());  
12.      char chars[] = new char[64];  
13.      int len;  
14.      StringBuilder sb = new StringBuilder();  
15.      while ((len=reader.read(chars)) != -1) {  
16.         sb.append(new String(chars, 0, len));  
17.      }  
18.      System.out.println("from client: " + sb);  
19.      //读完后写一句  
20.      Writer writer = new OutputStreamWriter(socket.getOutputStream());  
21.      writer.write("Hello Client.");  
22.      writer.flush();  
23.      writer.close();  
24.      reader.close();  
25.      socket.close();  
26.      server.close();  
27.   }  
28.     
29.}  


        

在上述代码中首先我们从输入流中读取客户端发送过来的数据，接下来我们再往输出流里面写入数据给客户端，接下来关闭对应的资源文件。而实际上上述代码可能并不会按照我们预先设想的方式运行，因为从输入流中读取数据是一个阻塞式操作，在上述的while循环中当读到数据的时候就会执行循环体，否则就会阻塞，这样后面的写操作就永远都执行不了了。除非客户端对应的Socket关闭了阻塞才会停止，while循环也会跳出。针对这种可能永远无法执行下去的情况的解决方法是while循环需要在里面有条件的跳出来，纵观上述代码，在不断变化的也只有取到的长度len和读到的数据了，len已经是不能用的了，唯一能用的就是读到的数据了。针对这种情况，通常我们都会约定一个结束标记，当客户端发送过来的数据包含某个结束标记时就说明当前的数据已经发送完毕了，这个时候我们就可以进行循环的跳出了。那么改进后的代码会是这个样子：


1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //为了简单起见，所有的异常信息都往外抛  
5.      int port = 8899;  
6.      //定义一个ServerSocket监听在端口8899上  
7.      ServerSocket server = new ServerSocket(port);  
8.      //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
9.      Socket socket = server.accept();  
10.      //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。  
11.      Reader reader = new InputStreamReader(socket.getInputStream());  
12.      char chars[] = new char[64];  
13.      int len;  
14.      StringBuilder sb = new StringBuilder();  
15.      String temp;  
16.      int index;  
17.      while ((len=reader.read(chars)) != -1) {  
18.         temp = new String(chars, 0, len);  
19.         if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
20.            sb.append(temp.substring(0, index));  
21.            break;  
22.         }  
23.         sb.append(temp);  
24.      }  
25.      System.out.println("from client: " + sb);  
26.      //读完后写一句  
27.      Writer writer = new OutputStreamWriter(socket.getOutputStream());  
28.      writer.write("Hello Client.");  
29.      writer.flush();  
30.      writer.close();  
31.      reader.close();  
32.      socket.close();  
33.      server.close();  
34.   }  
35.     
36.}  


       
在上述代码中，当服务端读取到客户端发送的结束标记，即“eof”时就会结束数据的接收，终止循环，这样后续的代码又可以继续进行了。 
 

       客户端代码

1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //为了简单起见，所有的异常都直接往外抛  
5.     String host = "127.0.0.1";  //要连接的服务端IP地址  
6.     int port = 8899;   //要连接的服务端对应的监听端口  
7.     //与服务端建立连接  
8.     Socket client = new Socket(host, port);  
9.      //建立连接后就可以往服务端写数据了  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.flush();  
13.      //写完以后进行读操作  
14.     Reader reader = new InputStreamReader(client.getInputStream());  
15.      char chars[] = new char[64];  
16.      int len;  
17.      StringBuffer sb = new StringBuffer();  
18.      while ((len=reader.read(chars)) != -1) {  
19.         sb.append(new String(chars, 0, len));  
20.      }  
21.      System.out.println("from server: " + sb);  
22.      writer.close();  
23.      reader.close();  
24.      client.close();  
25.   }  
26.     
27.}  


       
在上述代码中我们先是给服务端发送了一段数据，之后读取服务端返回来的数据，跟之前的服务端一样在读的过程中有可能导致程序一直挂在那里，永远跳不出while循环。这段代码配合服务端的第一段代码就正好让我们分析服务端永远在那里接收数据，永远跳不出while循环，也就没有之后的服务端返回数据给客户端，客户端也就不可能接收到服务端返回的数据。解决方法如服务端第二段代码所示，在客户端发送数据完毕后，往输出流里面写入结束标记告诉服务端数据已经发送完毕了，同样服务端返回数据完毕后也发一个标记告诉客户端。那么修改后的客户端代码就应该是这个样子： 


1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //为了简单起见，所有的异常都直接往外抛  
5.     String host = "127.0.0.1";  //要连接的服务端IP地址  
6.     int port = 8899;   //要连接的服务端对应的监听端口  
7.     //与服务端建立连接  
8.     Socket client = new Socket(host, port);  
9.      //建立连接后就可以往服务端写数据了  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.write("eof");  
13.      writer.flush();  
14.      //写完以后进行读操作  
15.     Reader reader = new InputStreamReader(client.getInputStream());  
16.      char chars[] = new char[64];  
17.      int len;  
18.      StringBuffer sb = new StringBuffer();  
19.      String temp;  
20.      int index;  
21.      while ((len=reader.read(chars)) != -1) {  
22.         temp = new String(chars, 0, len);  
23.         if ((index = temp.indexOf("eof")) != -1) {  
24.            sb.append(temp.substring(0, index));  
25.            break;  
26.         }  
27.         sb.append(new String(chars, 0, len));  
28.      }  
29.      System.out.println("from server: " + sb);  
30.      writer.close();  
31.      reader.close();  
32.      client.close();  
33.   }  
34.     
35.}  
36.   


       
我们日常使用的比较多的都是这种客户端发送数据给服务端，服务端接收数据后再返回相应的结果给客户端这种形式。只是客户端和服务端之间不再是这种一对一的关系，而是下面要讲到的多个客户端对应同一个服务端的情况。 
      3、多个客户端连接同一个服务端

       像前面讲的两个例子都是服务端接收一个客户端的请求之后就结束了，不能再接收其他客户端的请求了，这往往是不能满足我们的要求的。通常我们会这样做：




1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //为了简单起见，所有的异常信息都往外抛  
5.     int port = 8899;  
6.      //定义一个ServerSocket监听在端口8899上  
7.     ServerSocket server = new ServerSocket(port);  
8.      while (true) {  
9.         //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
10.       Socket socket = server.accept();  
11.         //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。  
12.       Reader reader = new InputStreamReader(socket.getInputStream());  
13.         char chars[] = new char[64];  
14.         int len;  
15.         StringBuilder sb = new StringBuilder();  
16.         String temp;  
17.         int index;  
18.         while ((len=reader.read(chars)) != -1) {  
19.            temp = new String(chars, 0, len);  
20.            if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
21.                sb.append(temp.substring(0, index));  
22.                break;  
23.            }  
24.            sb.append(temp);  
25.         }  
26.         System.out.println("from client: " + sb);  
27.         //读完后写一句  
28.       Writer writer = new OutputStreamWriter(socket.getOutputStream());  
29.         writer.write("Hello Client.");  
30.         writer.flush();  
31.         writer.close();  
32.         reader.close();  
33.         socket.close();  
34.      }  
35.   }  
36.     
37.}  


       
在上面代码中我们用了一个死循环，在循环体里面ServerSocket调用其accept方法试图接收来自客户端的连接请求。当没有接收到请求的时候，程序会在这里阻塞直到接收到来自客户端的连接请求，之后会跟当前建立好连接的客户端进行通信，完了后会接着执行循环体再次尝试接收新的连接请求。这样我们的ServerSocket就能接收来自所有客户端的连接请求了，并且与它们进行通信了。这就实现了一个简单的一个服务端与多个客户端进行通信的模式。 
       上述例子中虽然实现了一个服务端跟多个客户端进行通信，但是还存在一个问题。在上述例子中，我们的服务端处理客户端的连接请求是同步进行的，每次接收到来自客户端的连接请求后，都要先跟当前的客户端通信完之后才能再处理下一个连接请求。这在并发比较多的情况下会严重影响程序的性能，为此，我们可以把它改为如下这种异步处理与客户端通信的方式：




1.public class Server {  
2.     
3.   public static void main(String args[]) throws IOException {  
4.      //为了简单起见，所有的异常信息都往外抛  
5.     int port = 8899;  
6.      //定义一个ServerSocket监听在端口8899上  
7.     ServerSocket server = new ServerSocket(port);  
8.      while (true) {  
9.         //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
10.         Socket socket = server.accept();  
11.         //每接收到一个Socket就建立一个新的线程来处理它  
12.         new Thread(new Task(socket)).start();  
13.      }  
14.   }  
15.     
16.   /** 
17.    * 用来处理Socket请求的 
18.   */  
19.   static class Task implements Runnable {  
20.   
21.      private Socket socket;  
22.        
23.      public Task(Socket socket) {  
24.         this.socket = socket;  
25.      }  
26.        
27.      public void run() {  
28.  
29.         try {  
30.  
31.            handleSocket();  
32.         } catch (Exception e) {  
33.            e.printStackTrace();  
34.         }  
35.      }  
36.        
37.      /** 
38.       * 跟客户端Socket进行通信 
39.       * @throws Exception 
40.       */  
41.      private void handleSocket() throws Exception {  
42.         Reader reader = new InputStreamReader(socket.getInputStream());  
43.         char chars[] = new char[64];  
44.         int len;  
45.         StringBuilder sb = new StringBuilder();  
46.         String temp;  
47.         int index;  
48.         while ((len=reader.read(chars)) != -1) {  
49.            temp = new String(chars, 0, len);  
50.            if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
51.             sb.append(temp.substring(0, index));  
52.                break;  
53.            }  
54.            sb.append(temp);  
55.         }  
56.         System.out.println("from client: " + sb);  
57.         //读完后写一句  
58.       Writer writer = new OutputStreamWriter(socket.getOutputStream());  
59.         writer.write("Hello Client.");  
60.         writer.flush();  
61.         writer.close();  
62.         reader.close();  
63.         socket.close();  
64.      }  
65.        
66.   }  
67.     
68.}  


       
在上面代码中，每次ServerSocket接收到一个新的Socket连接请求后都会新起一个线程来跟当前Socket进行通信，这样就达到了异步处理与客户端Socket进行通信的情况。 
       在从Socket的InputStream中接收数据时，像上面那样一点点的读就太复杂了，有时候我们就会换成使用BufferedReader来一次读一行，如：



Java代码 复制代码 收藏代码
1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //为了简单起见，所有的异常信息都往外抛  
5.     int port = 8899;  
6.      //定义一个ServerSocket监听在端口8899上  
7.     ServerSocket server = new ServerSocket(port);  
8.      while (true) {  
9.         //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
10.         Socket socket = server.accept();  
11.         //每接收到一个Socket就建立一个新的线程来处理它  
12.         new Thread(new Task(socket)).start();  
13.      }  
14.   }  
15.     
16.   /** 
17.    * 用来处理Socket请求的 
18.   */  
19.   static class Task implements Runnable {  
20.   
21.      private Socket socket;  
22.        
23.      public Task(Socket socket) {  
24.         this.socket = socket;  
25.      }  
26.        
27.      public void run() {  
28.         try {  
29.            handleSocket();  
30.         } catch (Exception e) {  
31.            e.printStackTrace();  
32.         }  
33.      }  
34.        
35.      /** 
36.       * 跟客户端Socket进行通信 
37.      * @throws Exception 
38.       */  
39.      private void handleSocket() throws Exception {  
40.         BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
41.         StringBuilder sb = new StringBuilder();  
42.         String temp;  
43.         int index;  
44.         while ((temp=br.readLine()) != null) {  
45.            System.out.println(temp);  
46.            if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
47.             sb.append(temp.substring(0, index));  
48.                break;  
49.            }  
50.            sb.append(temp);  
51.         }  
52.         System.out.println("from client: " + sb);  
53.         //读完后写一句  
54.       Writer writer = new OutputStreamWriter(socket.getOutputStream());  
55.         writer.write("Hello Client.");  
56.         writer.write("eof\n");  
57.         writer.flush();  
58.         writer.close();  
59.         br.close();  
60.         socket.close();  
61.      }  
62.   }  
63.}  


       
这个时候需要注意的是，BufferedReader的readLine方法是一次读一行的，这个方法是阻塞的，直到它读到了一行数据为止程序才会继续往下执行，那么readLine什么时候才会读到一行呢？直到程序遇到了换行符或者是对应流的结束符readLine方法才会认为读到了一行，才会结束其阻塞，让程序继续往下执行。所以我们在使用BufferedReader的readLine读取数据的时候一定要记得在对应的输出流里面一定要写入换行符（流结束之后会自动标记为结束，readLine可以识别），写入换行符之后一定记得如果输出流不是马上关闭的情况下记得flush一下，这样数据才会真正的从缓冲区里面写入。对应上面的代码我们的客户端程序应该这样写： 


Java代码 复制代码 收藏代码
1.public class Client {  
2.  
3.   public static void main(String args[]) throws Exception {  
4.      //为了简单起见，所有的异常都直接往外抛  
5.     String host = "127.0.0.1";  //要连接的服务端IP地址  
6.     int port = 8899;   //要连接的服务端对应的监听端口  
7.     //与服务端建立连接  
8.     Socket client = new Socket(host, port);  
9.      //建立连接后就可以往服务端写数据了  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.write("eof\n");  
13.      writer.flush();  
14.      //写完以后进行读操作  
15.     BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));  
16.      StringBuffer sb = new StringBuffer();  
17.      String temp;  
18.      int index;  
19.      while ((temp=br.readLine()) != null) {  
20.         if ((index = temp.indexOf("eof")) != -1) {  
21.            sb.append(temp.substring(0, index));  
22.            break;  
23.         }  
24.         sb.append(temp);  
25.      }  
26.      System.out.println("from server: " + sb);  
27.      writer.close();  
28.      br.close();  
29.      client.close();  
30.   }  
31.}  


  

      4、设置超时时间

       假设有这样一种需求，我们的客户端需要通过Socket从服务端获取到XX信息，然后给用户展示在页面上。我们知道Socket在读数据的时候是阻塞式的，如果没有读到数据程序会一直阻塞在那里。在同步请求的时候我们肯定是不能允许这样的情况发生的，这就需要我们在请求达到一定的时间后控制阻塞的中断，让程序得以继续运行。Socket为我们提供了一个setSoTimeout()方法来设置接收数据的超时时间，单位是毫秒。当设置的超时时间大于0，并且超过了这一时间Socket还没有接收到返回的数据的话，Socket就会抛出一个SocketTimeoutException。

       假设我们需要控制我们的客户端在开始读取数据10秒后还没有读到数据就中断阻塞的话我们可以这样做：

 



Java代码 复制代码 收藏代码
1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //为了简单起见，所有的异常都直接往外抛  
5.     String host = "127.0.0.1";  //要连接的服务端IP地址  
6.     int port = 8899;   //要连接的服务端对应的监听端口  
7.     //与服务端建立连接  
8.     Socket client = new Socket(host, port);  
9.      //建立连接后就可以往服务端写数据了  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.write("eof\n");  
13.      writer.flush();  
14.      //写完以后进行读操作  
15.     BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));  
16.      //设置超时间为10秒  
17.     client.setSoTimeout(10*1000);  
18.      StringBuffer sb = new StringBuffer();  
19.      String temp;  
20.      int index;  
21.      try {  
22.         while ((temp=br.readLine()) != null) {  
23.            if ((index = temp.indexOf("eof")) != -1) {  
24.                sb.append(temp.substring(0, index));  
25.                break;  
26.            }  
27.            sb.append(temp);  
28.         }  
29.      } catch (SocketTimeoutException e) {  
30.         System.out.println("数据读取超时。");  
31.      }  
32.      System.out.println("from server: " + sb);  
33.      writer.close();  
34.      br.close();  
35.      client.close();  
36.   }  
37.}  
38.  
39.   


       5、接收数据乱码

       对于这种服务端或客户端接收中文乱码的情况通常是因为数据发送时使用的编码跟接收时候使用的编码不一致。比如有下面这样一段服务端代码：



Java代码 复制代码 收藏代码
1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //为了简单起见，所有的异常信息都往外抛  
5.      int port = 8899;  
6.      //定义一个ServerSocket监听在端口8899上  
7.      ServerSocket server = new ServerSocket(port);  
8.      while (true) {  
9.         //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
10.         Socket socket = server.accept();  
11.         //每接收到一个Socket就建立一个新的线程来处理它  
12.         new Thread(new Task(socket)).start();  
13.      }  
14.   }  
15.     
16.   /** 
17.    * 用来处理Socket请求的 
18.    */  
19.   static class Task implements Runnable {  
20.   
21.      private Socket socket;  
22.        
23.      public Task(Socket socket) {  
24.         this.socket = socket;  
25.      }  
26.        
27.      public void run() {  
28.         try {  
29.            handleSocket();  
30.         } catch (Exception e) {  
31.            e.printStackTrace();  
32.         }  
33.      }  
34.        
35.      /** 
36.       * 跟客户端Socket进行通信 
37.      * @throws Exception 
38.       */  
39.      private void handleSocket() throws Exception {  
40.         BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));  
41.         StringBuilder sb = new StringBuilder();  
42.         String temp;  
43.         int index;  
44.         while ((temp=br.readLine()) != null) {  
45.            System.out.println(temp);  
46.            if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
47.             sb.append(temp.substring(0, index));  
48.                break;  
49.            }  
50.            sb.append(temp);  
51.         }  
52.         System.out.println("客户端: " + sb);  
53.         //读完后写一句  
54.       Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");  
55.         writer.write("你好，客户端。");  
56.         writer.write("eof\n");  
57.         writer.flush();  
58.         writer.close();  
59.         br.close();  
60.         socket.close();  
61.      }  
62.   }  
63.}  


       
这里用来测试我就弄的混乱了一点。在上面服务端代码中我们在定义输入流的时候明确定义了使用GBK编码来读取数据，而在定义输出流的时候明确指定了将使用UTF-8编码来发送数据。如果客户端上送数据的时候不以GBK编码来发送的话服务端接收的数据就很有可能会乱码；同样如果客户端接收数据的时候不以服务端发送数据的编码，即UTF-8编码来接收数据的话也极有可能会出现数据乱码的情况。所以，对于上述服务端代码，为使我们的程序能够读取对方发送过来的数据，而不出现乱码情况，我们的客户端应该是这样的： 


Java代码 复制代码 收藏代码
1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //为了简单起见，所有的异常都直接往外抛  
5.     String host = "127.0.0.1";  //要连接的服务端IP地址  
6.     int port = 8899;   //要连接的服务端对应的监听端口  
7.     //与服务端建立连接  
8.     Socket client = new Socket(host, port);  
9.      //建立连接后就可以往服务端写数据了  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream(), "GBK");  
11.      writer.write("你好，服务端。");  
12.      writer.write("eof\n");  
13.      writer.flush();  
14.      //写完以后进行读操作  
15.     BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));  
16.      //设置超时间为10秒  
17.     client.setSoTimeout(10*1000);  
18.      StringBuffer sb = new StringBuffer();  
19.      String temp;  
20.      int index;  
21.      try {  
22.         while ((temp=br.readLine()) != null) {  
23.            if ((index = temp.indexOf("eof")) != -1) {  
24.                sb.append(temp.substring(0, index));  
25.                break;  
26.            }  
27.            sb.append(temp);  
28.         }  
29.      } catch (SocketTimeoutException e) {  
30.         System.out.println("数据读取超时。");  
31.      }  
32.      System.out.println("服务端: " + sb);  
33.      writer.close();  
34.      br.close();  
35.      client.close();  
36.   }  
37.} 



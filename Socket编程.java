һ����������������Ҫ������

һ�������׼ȷ�Ķ�λ������һ̨���̨��������һ�������ҵ���������οɿ���Ч�Ľ������ݴ��䡣

��TCP/IPЭ����IP����Ҫ�������������Ķ�λ�����ݴ����·�ɣ���IP��ַ����Ψһ��ȷ��Internet�ϵ�һ̨������

��TCP�����ṩ����Ӧ�õĿɿ���tcp���Ļ�ǿɿ���UDP�������ݴ�����ƣ����������̵���Ҫ����һ�㲻��Ҫ����IP������δ������ݵġ�

Ŀǰ��Ϊ���е�������ģ���ǿͻ���/��������C/S���ṹ����ͨ��˫��һ����Ϊ�������ȴ��ͻ��������������Ӧ���ͻ�������Ҫ����ʱ��������� �����롣������һ����Ϊ�ػ�����ʼ�����У���������˿ڣ�һ���пͻ����󣬾ͻ�����һ�������������Ӧ�ÿͻ���ͬʱ�Լ�������������˿ڣ�ʹ�����Ŀͻ�Ҳ �ܼ�ʱ�õ�����

�������ഫ��Э�飺TCP��UDP

TCP��Tranfer Control Protocol�� ��ƣ���һ���������ӵı�֤�ɿ������Э�顣ͨ��TCPЭ�鴫�䣬�õ�����һ��˳����޲��������������ͷ��ͽ��շ��ĳɶԵ�����socket֮����뽨 �����ӣ��Ա���TCPЭ��Ļ����Ͻ���ͨ�ţ���һ��socket��ͨ������server socket���ȴ���������ʱ����һ��socket����Ҫ��������ӣ�һ��������socket�������������ǾͿ��Խ���˫�����ݴ��䣬˫�������Խ��з��� ����ղ�����

UDP��User Datagram Protocol�ļ�ƣ���һ�������ӵ�Э�飬ÿ�����ݱ�����һ����������Ϣ������������Դ��ַ��Ŀ�ĵ�ַ���������������κο��ܵ�·������Ŀ�ĵأ�����ܷ񵽴�Ŀ�ĵأ�����Ŀ�ĵص�ʱ���Լ����ݵ���ȷ�Զ��ǲ��ܱ���֤�ġ�

�Ƚϣ�

UDP��1��ÿ�����ݱ��ж������������ĵ�ַ��Ϣ���������Ҫ�������ͷ��ͽ��շ������ӡ�

            2��UDP��������ʱ���д�С���Ƶģ�ÿ������������ݱ������޶���64KB֮�ڡ�

           3��UDP��һ�����ɿ���Э�飬���ͷ������͵����ݱ�����һ������ͬ�Ĵ��򵽴���շ�

TCP��1���������ӵ�Э�飬��socket֮��������ݴ���֮ǰ��ȻҪ�������ӣ�������TCP����Ҫ����

                ʱ�䡣

            2��TCP�������ݴ�С���ƣ�һ�����ӽ���������˫����socket�Ϳ��԰�ͳһ�ĸ�ʽ������  

                    ���ݡ�

             3��TCP��һ���ɿ���Э�飬��ȷ�����շ���ȫ��ȷ�ػ�ȡ���ͷ������͵�ȫ�����ݡ�

Ӧ�ã�

1��TCP������ͨ�����м�ǿ��������������Զ�����ӣ�Telnet�����ļ����䣨FTP������Ҫ�������ȵ����ݱ��ɿ��ش��䡣���ǿɿ��Ĵ�����Ҫ�������۵ģ�������������ȷ�Եļ����Ȼռ�ü�����Ĵ���ʱ�������Ĵ������TCP�����Ч�ʲ���UDP�ߡ�

2��UDP�����򵥣����ҽ���Ҫ���ٵļ໤�����ͨ�����ھ������߿ɿ��Եķ�ɢϵͳ��client/serverӦ�ó���������Ƶ����ϵͳ������Ҫ����Ƶ��Ƶ���ݾ��Ե���ȷ��ֻҪ��֤�����ԾͿ����ˣ������������Ȼʹ��UDP�������һЩ��

��������Socket��java������

1��ʲô��Socket

�����ϵ���������ͨ��һ��˫���ͨѶ����ʵ�����ݵĽ��������˫����·��һ�˳�Ϊһ��Socket��Socketͨ������ʵ�ֿͻ����ͷ��񷽵����ӡ�Socket��TCP/IPЭ���һ��ʮ�����еı�̽��棬һ��Socket��һ��IP��ַ��һ���˿ں�Ψһȷ����

���ǣ�Socket��֧�ֵ�Э������Ҳ����TCP/IPһ�֣��������֮����û�б�Ȼ��ϵ�ġ���Java�����£�Socket�����Ҫ��ָ����TCP/IPЭ��������̡�

2��SocketͨѶ�Ĺ���

Server��Listen(����)ĳ���˿��Ƿ�����������Client����Server �˷���Connect(����)����Server����Client�˷���Accept�����ܣ���Ϣ��һ�����Ӿͽ��������ˡ�Server�˺�Client �˶�����ͨ��Send��Write�ȷ�����Է�ͨ�š�

����һ��������ȫ��Socket����Ҫ�������»����ṹ���乤�����̰��������ĸ������Ĳ��裺

������1�� ����Socket��

������2�� �����ӵ�Socket������/������

������3�� ����һ����Э���Socket���ж�/д������

������4�� �ر�Socket.����ʵ��Ӧ���У���δʹ�õ���ʾ��close����Ȼ�ܶ����¶��Ƽ���ˣ��������ҵĳ����У�������Ϊ������Ƚϼ򵥣�Ҫ�󲻸ߣ����Բ�δ���ʲôӰ�졣��

3������Socket

����Socket

java�ڰ�java.net���ṩ��������Socket��ServerSocket���ֱ�������ʾ˫�����ӵĿͻ��˺ͷ���ˡ�����������װ�÷ǳ��õ��࣬ʹ�úܷ��㡣�乹�췽�����£�

����Socket(InetAddress address, int port);

����Socket(InetAddress address, int port, boolean stream);

����Socket(String host, int prot);

����Socket(String host, int prot, boolean stream);

����Socket(SocketImpl impl)

����Socket(String host, int port, InetAddress localAddr, int localPort)

����Socket(InetAddress address, int port, InetAddress localAddr, int localPort)

����ServerSocket(int port);

����ServerSocket(int port, int backlog);

����ServerSocket(int port, int backlog, InetAddress bindAddr)

��������address��host��port�ֱ���˫����������һ����IP��ַ���������Ͷ� �ںţ�streamָ��socket����socket�������ݱ�socket��localPort��ʾ���������Ķ˿ںţ�localAddr�� bindAddr�Ǳ��ػ����ĵ�ַ��ServerSocket��������ַ����impl��socket�ĸ��࣬�ȿ�����������serverSocket�ֿ� ����������Socket��count���ʾ���������֧�ֵ���������������磺ѧϰ��Ƶ�� http://www.xxspw.com

����Socket client = new Socket("127.0.01.", 80);

����ServerSocket server = new ServerSocket(80);

����ע�⣬��ѡ��˿�ʱ������С�ġ�ÿһ���˿��ṩһ���ض��ķ���ֻ�и�����ȷ�Ķ˿ڣ��� �ܻ����Ӧ�ķ���0~1023�Ķ˿ں�Ϊϵͳ������������http����Ķ˿ں�Ϊ80,telnet����Ķ˿ں�Ϊ21,ftp����Ķ˿ں�Ϊ23, ����������ѡ��˿ں�ʱ�����ѡ��һ������1023�����Է�ֹ������ͻ��

�����ڴ���socketʱ����������󣬽�����IOException���ڳ����б����֮�������������ڴ���Socket��ServerSocket�Ǳ��벶����׳����⡣

4���򵥵�Client/Server����

1. �ͻ��˳���

����import java.io.*;

����import java.net.*;

����public class TalkClient {

��������public static void main(String args[]) {

������������try{

����������������Socket socket=new Socket("127.0.0.1",4700);

����������������//�򱾻���4700�˿ڷ����ͻ�����

����������������BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));

����������������//��ϵͳ��׼�����豸����BufferedReader����

����������������PrintWriter os=new PrintWriter(socket.getOutputStream());

����������������//��Socket����õ��������������PrintWriter����

����������������BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));

����������������//��Socket����õ�����������������Ӧ��BufferedReader����

����������������String readline;

����������������readline=sin.readLine(); //��ϵͳ��׼�������һ�ַ���

����������������while(!readline.equals("bye")){

����������������//���ӱ�׼���������ַ���Ϊ "bye"��ֹͣѭ��

��������������������os.println(readline);

��������������������//����ϵͳ��׼���������ַ��������Server

��������������������os.flush();

��������������������//ˢ���������ʹServer�����յ����ַ���

��������������������System.out.println("Client:"+readline);

��������������������//��ϵͳ��׼����ϴ�ӡ������ַ���

��������������������System.out.println("Server:"+is.readLine());

��������������������//��Server����һ�ַ���������ӡ����׼�����

��������������������readline=sin.readLine(); //��ϵͳ��׼�������һ�ַ���

����������������} //����ѭ��

����������������os.close(); //�ر�Socket�����

����������������is.close(); //�ر�Socket������

����������������socket.close(); //�ر�Socket

������������}catch(Exception e) {

����������������System.out.println("Error"+e); //�������ӡ������Ϣ

������������}

����}

}

��2. �������˳���

����import java.io.*;

����import java.net.*;

����import java.applet.Applet;

����public class TalkServer{

��������public static void main(String args[]) {

������������try{

����������������ServerSocket server=null;

����������������try{

��������������������server=new ServerSocket(4700);

����������������//����һ��ServerSocket�ڶ˿�4700�����ͻ�����

����������������}catch(Exception e) {

��������������������System.out.println("can not listen to:"+e);

����������������//������ӡ������Ϣ

����������������}

����������������Socket socket=null;

����������������try{

��������������������socket=server.accept();

��������������������//ʹ��accept()�����ȴ��ͻ������пͻ�

��������������������//�����������һ��Socket���󣬲�����ִ��

����������������}catch(Exception e) {

��������������������System.out.println("Error."+e);

��������������������//������ӡ������Ϣ

����������������}

����������������String line;

����������������BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));

������������������//��Socket����õ�����������������Ӧ��BufferedReader����

����������������PrintWriter os=newPrintWriter(socket.getOutputStream());

������������������//��Socket����õ��������������PrintWriter����

����������������BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));

������������������//��ϵͳ��׼�����豸����BufferedReader����

����������������System.out.println("Client:"+is.readLine());

����������������//�ڱ�׼����ϴ�ӡ�ӿͻ��˶�����ַ���

����������������line=sin.readLine();

����������������//�ӱ�׼�������һ�ַ���

����������������while(!line.equals("bye")){

����������������//������ַ���Ϊ "bye"����ֹͣѭ��

��������������������os.println(line);

��������������������//��ͻ���������ַ���

��������������������os.flush();

��������������������//ˢ���������ʹClient�����յ����ַ���

��������������������System.out.println("Server:"+line);

��������������������//��ϵͳ��׼����ϴ�ӡ������ַ���

��������������������System.out.println("Client:"+is.readLine());

��������������������//��Client����һ�ַ���������ӡ����׼�����

��������������������line=sin.readLine();

��������������������//��ϵͳ��׼�������һ�ַ���

����������������} ��//����ѭ��

����������������os.close(); //�ر�Socket�����

����������������is.close(); //�ر�Socket������

����������������socket.close(); //�ر�Socket

����������������server.close(); //�ر�ServerSocket

������������}catch(Exception e){

����������������System.out.println("Error:"+e);

����������������//������ӡ������Ϣ

������������}

��������}

����}

5��֧�ֶ�ͻ���client/server����

ǰ���Client/Server����ֻ��ʵ��Server��һ���ͻ��ĶԻ�����ʵ��Ӧ�� �У��������ڷ�����������һ�����õĳ���
�����Խ���������������ͻ��˵������ṩ��Ӧ�ķ���Ϊ��ʵ���ڷ�������������ͻ��ṩ����Ĺ��ܣ���Ҫ����
��ĳ�����и��죬���ö��߳�ʵ�ֶ�ͻ����ơ�������������ָ���Ķ˿��ϼ����Ƿ��пͻ�����һ���������ͻ�����
�������ͻ�����һ��ר�ŵķ����߳����� Ӧ�ÿͻ������󣬶��������������������߳�֮�������ֽ������״̬���ȴ���һ���ͻ��ĵ�����
----------------------------------------------------------------------------------------------------------------------
�������ˣ�Server���Ƕ��̣߳�
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
�ͻ��ˣ�Client�� 
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
�������ˣ�Server�����߳�
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
 ����Java Socket��̶��ԣ����������һ����ServerSocket��һ����Socket������˺Ϳͻ���֮��ͨ��Socket�������ӣ�֮�����ǾͿ��Խ���ͨ���ˡ�����ServerSocket���ڷ���˼���ĳ���˿ڣ������ֿͻ�����Socket����ͼ������ʱ������accept��Socket����������ͬʱ�ڷ���˽���һ����Ӧ��Socket��֮����ͨ�š�������������Socket�ˣ��ͻ��˺ͷ���˸�һ����

       ����Socket֮���ͨ����ʵ�ܼ򵥣��������Socket�����������д�������ͻ��˾Ϳ���ͨ��Socket����������ȡ��Ӧ�����ݡ�Socket��Socket֮����˫����ͨ�ģ����Կͻ���Ҳ��������Ӧ��Socket���������д������Ȼ�����˶�Ӧ��Socket���������Ϳ��Զ�����Ӧ�����ݡ���������һЩ�������ͻ���ͨ�ŵ����ӣ�

      1���ͻ���д����˶�

       ����˴���

 



Java���� ���ƴ��� �ղش���
1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //Ϊ�˼���������е��쳣��Ϣ��������  
5.      int port = 8899;  
6.      //����һ��ServerSocket�����ڶ˿�8899��  
7.      ServerSocket server = new ServerSocket(port);  
8.      //server���Խ�������Socket����������server��accept����������ʽ��  
9.      Socket socket = server.accept();  
10.      //���ͻ��˽���������֮�����ǾͿ��Ի�ȡsocket��InputStream�������ж�ȡ�ͻ��˷���������Ϣ�ˡ�  
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


        

����˴�Socket��InputStream�ж�ȡ���ݵĲ���Ҳ������ʽ�ģ��������������û�ж�ȡ�����ݳ����һֱ�����ﲻ����ֱ���ͻ�����Socket���������д�������ݣ���ر���Socket�����������Ȼ�����ڿͻ��˵�SocketҲ��ͬ����ˡ��ڲ������Ժ������������ǰ�ǵùرն�Ӧ����Դ�����رն�Ӧ��IO����Socket��

 

       �ͻ��˴���



Java���� ���ƴ��� �ղش���
1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //Ϊ�˼���������е��쳣��ֱ��������  
5.      String host = "127.0.0.1";  //Ҫ���ӵķ����IP��ַ  
6.      int port = 8899;   //Ҫ���ӵķ���˶�Ӧ�ļ����˿�  
7.      //�����˽�������  
8.      Socket client = new Socket(host, port);  
9.      //�������Ӻ�Ϳ����������д������  
10.      Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.flush();//д���Ҫ�ǵ�flush  
13.      writer.close();  
14.      client.close();  
15.   }  
16.     
17.}  


       
���ڿͻ�����Socket�����������д���ݴ��ݸ������Ҫע��һ�㣬���д����֮������Ƕ�Ӧ��������Ĺرգ����ǽ�����������ʽ�Ĳ������������������������ݣ�����סҪflushһ�£�ֻ����������˲����յ��ͻ��˷��͵����ݣ�������ܻ������������޵Ļ���ȴ������Ժ󽲵��ͻ��˺ͷ����ͬʱ����д��ʱ���˵��������⡣ 
 

      2���ͻ��˺ͷ����ͬʱ����д

       ǰ���Ѿ�˵��Socket֮����˫��ͨ�ŵģ����ȿ��Խ������ݣ�ͬʱҲ���Է������ݡ�

       ����˴���

 



Java���� ���ƴ��� �ղش���
1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //Ϊ�˼���������е��쳣��Ϣ��������  
5.      int port = 8899;  
6.      //����һ��ServerSocket�����ڶ˿�8899��  
7.      ServerSocket server = new ServerSocket(port);  
8.      //server���Խ�������Socket����������server��accept����������ʽ��  
9.      Socket socket = server.accept();  
10.      //���ͻ��˽���������֮�����ǾͿ��Ի�ȡsocket��InputStream�������ж�ȡ�ͻ��˷���������Ϣ�ˡ�  
11.      Reader reader = new InputStreamReader(socket.getInputStream());  
12.      char chars[] = new char[64];  
13.      int len;  
14.      StringBuilder sb = new StringBuilder();  
15.      while ((len=reader.read(chars)) != -1) {  
16.         sb.append(new String(chars, 0, len));  
17.      }  
18.      System.out.println("from client: " + sb);  
19.      //�����дһ��  
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


        

�������������������Ǵ��������ж�ȡ�ͻ��˷��͹��������ݣ������������������������д�����ݸ��ͻ��ˣ��������رն�Ӧ����Դ�ļ�����ʵ��������������ܲ����ᰴ������Ԥ������ķ�ʽ���У���Ϊ���������ж�ȡ������һ������ʽ��������������whileѭ���е��������ݵ�ʱ��ͻ�ִ��ѭ���壬����ͻ����������������д��������Զ��ִ�в����ˡ����ǿͻ��˶�Ӧ��Socket�ر��������Ż�ֹͣ��whileѭ��Ҳ��������������ֿ�����Զ�޷�ִ����ȥ������Ľ��������whileѭ����Ҫ�����������������������ݹ��������룬�ڲ��ϱ仯��Ҳֻ��ȡ���ĳ���len�Ͷ����������ˣ�len�Ѿ��ǲ����õ��ˣ�Ψһ���õľ��Ƕ����������ˡ�������������ͨ�����Ƕ���Լ��һ��������ǣ����ͻ��˷��͹��������ݰ���ĳ���������ʱ��˵����ǰ�������Ѿ���������ˣ����ʱ�����ǾͿ��Խ���ѭ���������ˡ���ô�Ľ���Ĵ������������ӣ�


1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //Ϊ�˼���������е��쳣��Ϣ��������  
5.      int port = 8899;  
6.      //����һ��ServerSocket�����ڶ˿�8899��  
7.      ServerSocket server = new ServerSocket(port);  
8.      //server���Խ�������Socket����������server��accept����������ʽ��  
9.      Socket socket = server.accept();  
10.      //���ͻ��˽���������֮�����ǾͿ��Ի�ȡsocket��InputStream�������ж�ȡ�ͻ��˷���������Ϣ�ˡ�  
11.      Reader reader = new InputStreamReader(socket.getInputStream());  
12.      char chars[] = new char[64];  
13.      int len;  
14.      StringBuilder sb = new StringBuilder();  
15.      String temp;  
16.      int index;  
17.      while ((len=reader.read(chars)) != -1) {  
18.         temp = new String(chars, 0, len);  
19.         if ((index = temp.indexOf("eof")) != -1) {//����eofʱ�ͽ�������  
20.            sb.append(temp.substring(0, index));  
21.            break;  
22.         }  
23.         sb.append(temp);  
24.      }  
25.      System.out.println("from client: " + sb);  
26.      //�����дһ��  
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


       
�����������У�������˶�ȡ���ͻ��˷��͵Ľ�����ǣ�����eof��ʱ�ͻ�������ݵĽ��գ���ֹѭ�������������Ĵ����ֿ��Լ��������ˡ� 
 

       �ͻ��˴���

1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //Ϊ�˼���������е��쳣��ֱ��������  
5.     String host = "127.0.0.1";  //Ҫ���ӵķ����IP��ַ  
6.     int port = 8899;   //Ҫ���ӵķ���˶�Ӧ�ļ����˿�  
7.     //�����˽�������  
8.     Socket client = new Socket(host, port);  
9.      //�������Ӻ�Ϳ����������д������  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.flush();  
13.      //д���Ժ���ж�����  
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


       
�������������������Ǹ�����˷�����һ�����ݣ�֮���ȡ����˷����������ݣ���֮ǰ�ķ����һ���ڶ��Ĺ������п��ܵ��³���һֱ���������Զ������whileѭ������δ�����Ϸ���˵ĵ�һ�δ�������������Ƿ����������Զ������������ݣ���Զ������whileѭ����Ҳ��û��֮��ķ���˷������ݸ��ͻ��ˣ��ͻ���Ҳ�Ͳ����ܽ��յ�����˷��ص����ݡ�������������˵ڶ��δ�����ʾ���ڿͻ��˷���������Ϻ������������д�������Ǹ��߷���������Ѿ���������ˣ�ͬ������˷���������Ϻ�Ҳ��һ����Ǹ��߿ͻ��ˡ���ô�޸ĺ�Ŀͻ��˴����Ӧ����������ӣ� 


1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //Ϊ�˼���������е��쳣��ֱ��������  
5.     String host = "127.0.0.1";  //Ҫ���ӵķ����IP��ַ  
6.     int port = 8899;   //Ҫ���ӵķ���˶�Ӧ�ļ����˿�  
7.     //�����˽�������  
8.     Socket client = new Socket(host, port);  
9.      //�������Ӻ�Ϳ����������д������  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.write("eof");  
13.      writer.flush();  
14.      //д���Ժ���ж�����  
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


       
�����ճ�ʹ�õıȽ϶�Ķ������ֿͻ��˷������ݸ�����ˣ�����˽������ݺ��ٷ�����Ӧ�Ľ�����ͻ���������ʽ��ֻ�ǿͻ��˺ͷ����֮�䲻��������һ��һ�Ĺ�ϵ����������Ҫ�����Ķ���ͻ��˶�Ӧͬһ������˵������ 
      3������ͻ�������ͬһ�������

       ��ǰ�潲���������Ӷ��Ƿ���˽���һ���ͻ��˵�����֮��ͽ����ˣ������ٽ��������ͻ��˵������ˣ��������ǲ����������ǵ�Ҫ��ġ�ͨ�����ǻ���������




1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //Ϊ�˼���������е��쳣��Ϣ��������  
5.     int port = 8899;  
6.      //����һ��ServerSocket�����ڶ˿�8899��  
7.     ServerSocket server = new ServerSocket(port);  
8.      while (true) {  
9.         //server���Խ�������Socket����������server��accept����������ʽ��  
10.       Socket socket = server.accept();  
11.         //���ͻ��˽���������֮�����ǾͿ��Ի�ȡsocket��InputStream�������ж�ȡ�ͻ��˷���������Ϣ�ˡ�  
12.       Reader reader = new InputStreamReader(socket.getInputStream());  
13.         char chars[] = new char[64];  
14.         int len;  
15.         StringBuilder sb = new StringBuilder();  
16.         String temp;  
17.         int index;  
18.         while ((len=reader.read(chars)) != -1) {  
19.            temp = new String(chars, 0, len);  
20.            if ((index = temp.indexOf("eof")) != -1) {//����eofʱ�ͽ�������  
21.                sb.append(temp.substring(0, index));  
22.                break;  
23.            }  
24.            sb.append(temp);  
25.         }  
26.         System.out.println("from client: " + sb);  
27.         //�����дһ��  
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


       
�������������������һ����ѭ������ѭ��������ServerSocket������accept������ͼ�������Կͻ��˵��������󡣵�û�н��յ������ʱ�򣬳��������������ֱ�����յ����Կͻ��˵���������֮������ǰ���������ӵĿͻ��˽���ͨ�ţ����˺�����ִ��ѭ�����ٴγ��Խ����µ����������������ǵ�ServerSocket���ܽ����������пͻ��˵����������ˣ����������ǽ���ͨ���ˡ����ʵ����һ���򵥵�һ������������ͻ��˽���ͨ�ŵ�ģʽ�� 
       ������������Ȼʵ����һ������˸�����ͻ��˽���ͨ�ţ����ǻ�����һ�����⡣�����������У����ǵķ���˴���ͻ��˵�����������ͬ�����еģ�ÿ�ν��յ����Կͻ��˵���������󣬶�Ҫ�ȸ���ǰ�Ŀͻ���ͨ����֮������ٴ�����һ�������������ڲ����Ƚ϶������»�����Ӱ���������ܣ�Ϊ�ˣ����ǿ��԰�����Ϊ���������첽������ͻ���ͨ�ŵķ�ʽ��




1.public class Server {  
2.     
3.   public static void main(String args[]) throws IOException {  
4.      //Ϊ�˼���������е��쳣��Ϣ��������  
5.     int port = 8899;  
6.      //����һ��ServerSocket�����ڶ˿�8899��  
7.     ServerSocket server = new ServerSocket(port);  
8.      while (true) {  
9.         //server���Խ�������Socket����������server��accept����������ʽ��  
10.         Socket socket = server.accept();  
11.         //ÿ���յ�һ��Socket�ͽ���һ���µ��߳���������  
12.         new Thread(new Task(socket)).start();  
13.      }  
14.   }  
15.     
16.   /** 
17.    * ��������Socket����� 
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
38.       * ���ͻ���Socket����ͨ�� 
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
50.            if ((index = temp.indexOf("eof")) != -1) {//����eofʱ�ͽ�������  
51.             sb.append(temp.substring(0, index));  
52.                break;  
53.            }  
54.            sb.append(temp);  
55.         }  
56.         System.out.println("from client: " + sb);  
57.         //�����дһ��  
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


       
����������У�ÿ��ServerSocket���յ�һ���µ�Socket��������󶼻�����һ���߳�������ǰSocket����ͨ�ţ������ʹﵽ���첽������ͻ���Socket����ͨ�ŵ������ 
       �ڴ�Socket��InputStream�н�������ʱ������������һ���Ķ���̫�����ˣ���ʱ�����Ǿͻỻ��ʹ��BufferedReader��һ�ζ�һ�У��磺



Java���� ���ƴ��� �ղش���
1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //Ϊ�˼���������е��쳣��Ϣ��������  
5.     int port = 8899;  
6.      //����һ��ServerSocket�����ڶ˿�8899��  
7.     ServerSocket server = new ServerSocket(port);  
8.      while (true) {  
9.         //server���Խ�������Socket����������server��accept����������ʽ��  
10.         Socket socket = server.accept();  
11.         //ÿ���յ�һ��Socket�ͽ���һ���µ��߳���������  
12.         new Thread(new Task(socket)).start();  
13.      }  
14.   }  
15.     
16.   /** 
17.    * ��������Socket����� 
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
36.       * ���ͻ���Socket����ͨ�� 
37.      * @throws Exception 
38.       */  
39.      private void handleSocket() throws Exception {  
40.         BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
41.         StringBuilder sb = new StringBuilder();  
42.         String temp;  
43.         int index;  
44.         while ((temp=br.readLine()) != null) {  
45.            System.out.println(temp);  
46.            if ((index = temp.indexOf("eof")) != -1) {//����eofʱ�ͽ�������  
47.             sb.append(temp.substring(0, index));  
48.                break;  
49.            }  
50.            sb.append(temp);  
51.         }  
52.         System.out.println("from client: " + sb);  
53.         //�����дһ��  
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


       
���ʱ����Ҫע����ǣ�BufferedReader��readLine������һ�ζ�һ�еģ���������������ģ�ֱ����������һ������Ϊֹ����Ż��������ִ�У���ôreadLineʲôʱ��Ż����һ���أ�ֱ�����������˻��з������Ƕ�Ӧ���Ľ�����readLine�����Ż���Ϊ������һ�У��Ż�������������ó����������ִ�С�����������ʹ��BufferedReader��readLine��ȡ���ݵ�ʱ��һ��Ҫ�ǵ��ڶ�Ӧ�����������һ��Ҫд�뻻�з���������֮����Զ����Ϊ������readLine����ʶ�𣩣�д�뻻�з�֮��һ���ǵ����������������Ϲرյ�����¼ǵ�flushһ�£��������ݲŻ������Ĵӻ���������д�롣��Ӧ����Ĵ������ǵĿͻ��˳���Ӧ������д�� 


Java���� ���ƴ��� �ղش���
1.public class Client {  
2.  
3.   public static void main(String args[]) throws Exception {  
4.      //Ϊ�˼���������е��쳣��ֱ��������  
5.     String host = "127.0.0.1";  //Ҫ���ӵķ����IP��ַ  
6.     int port = 8899;   //Ҫ���ӵķ���˶�Ӧ�ļ����˿�  
7.     //�����˽�������  
8.     Socket client = new Socket(host, port);  
9.      //�������Ӻ�Ϳ����������д������  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.write("eof\n");  
13.      writer.flush();  
14.      //д���Ժ���ж�����  
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


  

      4�����ó�ʱʱ��

       ����������һ���������ǵĿͻ�����Ҫͨ��Socket�ӷ���˻�ȡ��XX��Ϣ��Ȼ����û�չʾ��ҳ���ϡ�����֪��Socket�ڶ����ݵ�ʱ��������ʽ�ģ����û�ж������ݳ����һֱ�����������ͬ�������ʱ�����ǿ϶��ǲ���������������������ģ������Ҫ����������ﵽһ����ʱ�������������жϣ��ó�����Լ������С�SocketΪ�����ṩ��һ��setSoTimeout()���������ý������ݵĳ�ʱʱ�䣬��λ�Ǻ��롣�����õĳ�ʱʱ�����0�����ҳ�������һʱ��Socket��û�н��յ����ص����ݵĻ���Socket�ͻ��׳�һ��SocketTimeoutException��

       ����������Ҫ�������ǵĿͻ����ڿ�ʼ��ȡ����10���û�ж������ݾ��ж������Ļ����ǿ�����������

 



Java���� ���ƴ��� �ղش���
1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //Ϊ�˼���������е��쳣��ֱ��������  
5.     String host = "127.0.0.1";  //Ҫ���ӵķ����IP��ַ  
6.     int port = 8899;   //Ҫ���ӵķ���˶�Ӧ�ļ����˿�  
7.     //�����˽�������  
8.     Socket client = new Socket(host, port);  
9.      //�������Ӻ�Ϳ����������д������  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream());  
11.      writer.write("Hello Server.");  
12.      writer.write("eof\n");  
13.      writer.flush();  
14.      //д���Ժ���ж�����  
15.     BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));  
16.      //���ó�ʱ��Ϊ10��  
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
30.         System.out.println("���ݶ�ȡ��ʱ��");  
31.      }  
32.      System.out.println("from server: " + sb);  
33.      writer.close();  
34.      br.close();  
35.      client.close();  
36.   }  
37.}  
38.  
39.   


       5��������������

       �������ַ���˻�ͻ��˽���������������ͨ������Ϊ���ݷ���ʱʹ�õı��������ʱ��ʹ�õı��벻һ�¡���������������һ�η���˴��룺



Java���� ���ƴ��� �ղش���
1.public class Server {  
2.   
3.   public static void main(String args[]) throws IOException {  
4.      //Ϊ�˼���������е��쳣��Ϣ��������  
5.      int port = 8899;  
6.      //����һ��ServerSocket�����ڶ˿�8899��  
7.      ServerSocket server = new ServerSocket(port);  
8.      while (true) {  
9.         //server���Խ�������Socket����������server��accept����������ʽ��  
10.         Socket socket = server.accept();  
11.         //ÿ���յ�һ��Socket�ͽ���һ���µ��߳���������  
12.         new Thread(new Task(socket)).start();  
13.      }  
14.   }  
15.     
16.   /** 
17.    * ��������Socket����� 
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
36.       * ���ͻ���Socket����ͨ�� 
37.      * @throws Exception 
38.       */  
39.      private void handleSocket() throws Exception {  
40.         BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));  
41.         StringBuilder sb = new StringBuilder();  
42.         String temp;  
43.         int index;  
44.         while ((temp=br.readLine()) != null) {  
45.            System.out.println(temp);  
46.            if ((index = temp.indexOf("eof")) != -1) {//����eofʱ�ͽ�������  
47.             sb.append(temp.substring(0, index));  
48.                break;  
49.            }  
50.            sb.append(temp);  
51.         }  
52.         System.out.println("�ͻ���: " + sb);  
53.         //�����дһ��  
54.       Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");  
55.         writer.write("��ã��ͻ��ˡ�");  
56.         writer.write("eof\n");  
57.         writer.flush();  
58.         writer.close();  
59.         br.close();  
60.         socket.close();  
61.      }  
62.   }  
63.}  


       
�������������Ҿ�Ū�Ļ�����һ�㡣���������˴����������ڶ�����������ʱ����ȷ������ʹ��GBK��������ȡ���ݣ����ڶ����������ʱ����ȷָ���˽�ʹ��UTF-8�������������ݡ�����ͻ����������ݵ�ʱ����GBK���������͵Ļ�����˽��յ����ݾͺ��п��ܻ����룻ͬ������ͻ��˽������ݵ�ʱ���Է���˷������ݵı��룬��UTF-8�������������ݵĻ�Ҳ���п��ܻ���������������������ԣ�������������˴��룬Ϊʹ���ǵĳ����ܹ���ȡ�Է����͹��������ݣ���������������������ǵĿͻ���Ӧ���������ģ� 


Java���� ���ƴ��� �ղش���
1.public class Client {  
2.   
3.   public static void main(String args[]) throws Exception {  
4.      //Ϊ�˼���������е��쳣��ֱ��������  
5.     String host = "127.0.0.1";  //Ҫ���ӵķ����IP��ַ  
6.     int port = 8899;   //Ҫ���ӵķ���˶�Ӧ�ļ����˿�  
7.     //�����˽�������  
8.     Socket client = new Socket(host, port);  
9.      //�������Ӻ�Ϳ����������д������  
10.     Writer writer = new OutputStreamWriter(client.getOutputStream(), "GBK");  
11.      writer.write("��ã�����ˡ�");  
12.      writer.write("eof\n");  
13.      writer.flush();  
14.      //д���Ժ���ж�����  
15.     BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));  
16.      //���ó�ʱ��Ϊ10��  
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
30.         System.out.println("���ݶ�ȡ��ʱ��");  
31.      }  
32.      System.out.println("�����: " + sb);  
33.      writer.close();  
34.      br.close();  
35.      client.close();  
36.   }  
37.} 



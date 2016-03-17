package com.itheim28.submitdata.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class NetUtils2 {

	private static final String TAG = "NetUtils";
	
	/**
	 * 使用post的方式登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String loginOfPost(String userName, String password) {
		HttpClient client = null;
		try {
			// 定义一个客户端
			client = new DefaultHttpClient();
			
			// 定义post方法
			HttpPost post = new HttpPost("http://10.0.2.2:8080/ServerItheima28/servlet/LoginServlet");
			
			// 定义post请求的参数
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("username", userName));
			parameters.add(new BasicNameValuePair("password", password));
			
			// 把post请求的参数包装了一层.
			
			// 不写编码名称服务器收数据时乱码. 需要指定字符集为utf-8
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
			// 设置参数.
			post.setEntity(entity);
			
			// 设置请求头消息
//			post.addHeader("Content-Length", "20");
			
			// 使用客户端执行post方法
			HttpResponse response = client.execute(post);	// 开始执行post请求, 会返回给我们一个HttpResponse对象
			
			// 使用响应对象, 获得状态码, 处理内容
			int statusCode = response.getStatusLine().getStatusCode();	// 获得状态码
			if(statusCode == 200) {
				// 使用响应对象获得实体, 获得输入流
				InputStream is = response.getEntity().getContent();
				String text = getStringFromInputStream(is);
				return text;
			} else {
				Log.i(TAG, "请求失败: " + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(client != null) {
				client.getConnectionManager().shutdown();	// 关闭连接和释放资源
			}
		}
		return null;
	}

	/**
	 * 使用get的方式登录
	 * @param userName
	 * @param password
	 * @return 登录的状态
	 */
	public static String loginOfGet(String userName, String password) {
		HttpClient client = null;
		try {
			// 定义一个客户端
			client = new DefaultHttpClient();
			
			// 定义一个get请求方法
			String data = "username=" + userName + "&password=" + password;
			HttpGet get = new HttpGet("http://10.0.2.2:8080/ServerItheima28/servlet/LoginServlet?" + data);
			
			// response 服务器相应对象, 其中包含了状态信息和服务器返回的数据
			HttpResponse response = client.execute(get);	// 开始执行get方法, 请求网络
			
			// 获得响应码
			int statusCode = response.getStatusLine().getStatusCode();
			
			if(statusCode == 200) {
				InputStream is = response.getEntity().getContent();
				String text = getStringFromInputStream(is);
				return text;
			} else {
				Log.i(TAG, "请求失败: " + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(client != null) {
				client.getConnectionManager().shutdown();	// 关闭连接, 和释放资源
			}
		}
		return null;
	}
	
	/**
	 * 根据流返回一个字符串信息
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	private static String getStringFromInputStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		
		while((len = is.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		is.close();
		
		String html = baos.toString();	// 把流中的数据转换成字符串, 采用的编码是: utf-8
		
//		String html = new String(baos.toByteArray(), "GBK");
		
		baos.close();
		return html;
	}
}

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

@ContentView(R.layout.activity_main)

public class MainActivity extends Activity {
	@ViewInject(R.id.add)
	private Button button;
	/*@ViewInject(R.id.sub)
	private Button sButton;*/
	@ViewInject(R.id.webview)
	private WebView webView;
	private String userName = "Myname";
	private String password = "做人真难";
	private HttpURLConnection conn = null;;
	private String appid= "20160119000009092";
	private String appKey = "55FuDvaSHXHA0McNJXNI";
	@OnClick(R.id.div)//百度翻译测试
	private void div(View view){
		final String data;
		new Thread(new Runnable() {
			String qString ="I like eat apple";
			int salt = 123456;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String one = appid + qString + salt + appKey;
				try {
					one = new String(one.getBytes("GBK"), "utf-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				URL url;
				try {
					String md5 = stringToMD5(one);
					System.out.println(md5 + "!!!!!!!!!!");
					url = new URL("http://api.fanyi.baidu.com/api/trans/vip/translate?" + 
							"q=" + qString + "&from=en&to=zh&appid=" + appid + "&salt=" +
							salt + "&sign=" + md5);
					System.out.println(url);
					Log.i("访问失败", "访问失败: 2");
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(10000); // 连接的超时时间
					conn.setReadTimeout(5000); // 读数据的超时时间
					int responseCode = conn.getResponseCode();
					System.out.println("返回码的值" + responseCode);
					if(responseCode == 200) {
						InputStream is = conn.getInputStream();
						String state = getStringFromInputStream(is);
						Log.e("服务器返回的结果", state);//{"from":"en","to":"zh","trans_result":[{"src":"apple","dst":"\u82f9\u679c"}]}
					} else {
						Log.i("访问失败", "访问失败: " + responseCode);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	@OnClick(R.id.sub)//减法计算的按钮，HttpURLConnection post方法实现客户端和服务器端通讯
	public void sub(View view){
		System.out.println("我被点击了");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				theWaypost();	
			}
		}).start();
	}
	//乘法计算的按钮，httpclient get方法获取数据
	@OnClick(R.id.mul)
	private void mul(View view) {
		// TODO Auto-generated method stub
		System.out.println("乘法计算错误日志");
		final String url = "http://172.168.9.26:8080/Mydemo/servlet/GetMyData";
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//get方法与服务器进行通讯
				/*HttpParams params = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(params, 5000);//连接超时时间
				HttpConnectionParams.setSoTimeout(params, 100000);//数据读取超时时间
				HttpClient httpClient = new DefaultHttpClient(params);
				String data = "username=" + userName + "&password=" + password;
				HttpGet get = new HttpGet(url + "?" data);
				try {
					HttpResponse response = httpClient.execute(get);
					System.out.println("返回码：" + response.getStatusLine().getStatusCode());
					if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						InputStream is = response.getEntity().getContent();
						System.out.println("服务器返回的信息输入流的显示方式" + is);
						String resString = getStringFromInputStream(is);
						System.out.println("服务器返回的信息字符串的显示方式" + resString);
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("httpclient get方法错误日志" + e);
				}*/
				//post方法与服务器进行通讯
				HttpParams params = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(params, 5000);//连接超时时间
				HttpConnectionParams.setSoTimeout(params, 100000);//数据读取超时时间
				HttpClient httpClient = new DefaultHttpClient(params);
				HttpPost post = new HttpPost(url);
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("username", "我叫梁文硕"));
				pairs.add(new BasicNameValuePair("password", "我喜欢奥黛丽赫本"));
				try {
					HttpEntity httpEntity = new UrlEncodedFormEntity(pairs);
					post.setEntity(httpEntity);
					HttpResponse response = httpClient.execute(post);
					if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						InputStream is = response.getEntity().getContent();
						System.out.println("服务器返回的信息输入流的显示方式" + is);
						String resString = getStringFromInputStream(is);
						System.out.println("服务器返回的信息字符串的显示方式" + resString);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("post请求错误日志" + e);
				}
			}
		}).start();
		
	}
	//post方法向服务器提交数据
    private void theWaypost() {
		// TODO Auto-generated method stub
		URL url;
		try {
			url = new URL("http://172.168.9.26:8080/Mydemo/servlet/GetMyData");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);
			conn.setDoOutput(true);
			System.out.println("11111111111111111");
			String data = "username=" + userName + "&password=" + password;
			OutputStream outStream = conn.getOutputStream();
			outStream.write(data.getBytes());
			outStream.flush();
			outStream.close();
			if(conn.getResponseCode() == 200){
				InputStream isInputStream = conn.getInputStream();
				final String string = getStringFromInputStream(isInputStream);
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						button.setText(string);
					}
				});
			}else {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						button.setText("Sorry");
					}
				});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("错误日志" + e);
			e.printStackTrace();
		}
	}
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
       /* sButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("我被点击了");
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("开启了新线程");
						theWaypost();	
					}
				});
			}
		});*/
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					
					@Override
					public void run() {//加法计算的按钮，HttpURLConnection get方法实现客户端与服务器端的通讯
						// TODO Auto-generated method stub
						Log.e("测试按钮", "点击事件添加");
						HttpURLConnection conn = null;
						try {
							Log.i("访问失败", "访问失败: 1");
							String data = "username=" + userName + "&password=" + password;
							URL url = new URL("http://172.168.9.26:8080/Mydemo/servlet/GetMyData?" + data);
							Log.i("访问失败", "访问失败: 2");
							conn = (HttpURLConnection) url.openConnection();
							conn.setRequestMethod("GET");
							conn.setConnectTimeout(10000); // 连接的超时时间
							conn.setReadTimeout(5000); // 读数据的超时时间
							
							int responseCode = conn.getResponseCode();
							System.out.println("返回码的值" + responseCode);
							if(responseCode == 200) {
								InputStream is = conn.getInputStream();
								String state = getStringFromInputStream(is);
								Log.e("服务器返回的结果", state);
							} else {
								Log.i("访问失败", "访问失败: " + responseCode);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("错误日志" + e);
						}
					}
				}).start();
			}
		});
    }
	//字节流转字符串
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
    //计算字符串的MD5值
    public static String stringToMD5(String string) {  
        byte[] hash;  
      
        try {  
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
      
        StringBuilder hex = new StringBuilder(hash.length * 2);  
        for (byte b : hash) {  
            if ((b & 0xFF) < 0x10)  
                hex.append("0");  
            hex.append(Integer.toHexString(b & 0xFF));  
        }  
      
        return hex.toString();  
    } 
    
}

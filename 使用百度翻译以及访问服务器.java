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
	private String password = "��������";
	private HttpURLConnection conn = null;;
	private String appid= "20160119000009092";
	private String appKey = "55FuDvaSHXHA0McNJXNI";
	@OnClick(R.id.div)//�ٶȷ������
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
					Log.i("����ʧ��", "����ʧ��: 2");
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(10000); // ���ӵĳ�ʱʱ��
					conn.setReadTimeout(5000); // �����ݵĳ�ʱʱ��
					int responseCode = conn.getResponseCode();
					System.out.println("�������ֵ" + responseCode);
					if(responseCode == 200) {
						InputStream is = conn.getInputStream();
						String state = getStringFromInputStream(is);
						Log.e("���������صĽ��", state);//{"from":"en","to":"zh","trans_result":[{"src":"apple","dst":"\u82f9\u679c"}]}
					} else {
						Log.i("����ʧ��", "����ʧ��: " + responseCode);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	@OnClick(R.id.sub)//��������İ�ť��HttpURLConnection post����ʵ�ֿͻ��˺ͷ�������ͨѶ
	public void sub(View view){
		System.out.println("�ұ������");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				theWaypost();	
			}
		}).start();
	}
	//�˷�����İ�ť��httpclient get������ȡ����
	@OnClick(R.id.mul)
	private void mul(View view) {
		// TODO Auto-generated method stub
		System.out.println("�˷����������־");
		final String url = "http://172.168.9.26:8080/Mydemo/servlet/GetMyData";
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//get���������������ͨѶ
				/*HttpParams params = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(params, 5000);//���ӳ�ʱʱ��
				HttpConnectionParams.setSoTimeout(params, 100000);//���ݶ�ȡ��ʱʱ��
				HttpClient httpClient = new DefaultHttpClient(params);
				String data = "username=" + userName + "&password=" + password;
				HttpGet get = new HttpGet(url + "?" data);
				try {
					HttpResponse response = httpClient.execute(get);
					System.out.println("�����룺" + response.getStatusLine().getStatusCode());
					if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						InputStream is = response.getEntity().getContent();
						System.out.println("���������ص���Ϣ����������ʾ��ʽ" + is);
						String resString = getStringFromInputStream(is);
						System.out.println("���������ص���Ϣ�ַ�������ʾ��ʽ" + resString);
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("httpclient get����������־" + e);
				}*/
				//post���������������ͨѶ
				HttpParams params = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(params, 5000);//���ӳ�ʱʱ��
				HttpConnectionParams.setSoTimeout(params, 100000);//���ݶ�ȡ��ʱʱ��
				HttpClient httpClient = new DefaultHttpClient(params);
				HttpPost post = new HttpPost(url);
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("username", "�ҽ�����˶"));
				pairs.add(new BasicNameValuePair("password", "��ϲ���������ձ�"));
				try {
					HttpEntity httpEntity = new UrlEncodedFormEntity(pairs);
					post.setEntity(httpEntity);
					HttpResponse response = httpClient.execute(post);
					if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						InputStream is = response.getEntity().getContent();
						System.out.println("���������ص���Ϣ����������ʾ��ʽ" + is);
						String resString = getStringFromInputStream(is);
						System.out.println("���������ص���Ϣ�ַ�������ʾ��ʽ" + resString);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("post���������־" + e);
				}
			}
		}).start();
		
	}
	//post������������ύ����
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
			System.out.println("������־" + e);
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
				System.out.println("�ұ������");
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("���������߳�");
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
					public void run() {//�ӷ�����İ�ť��HttpURLConnection get����ʵ�ֿͻ�����������˵�ͨѶ
						// TODO Auto-generated method stub
						Log.e("���԰�ť", "����¼����");
						HttpURLConnection conn = null;
						try {
							Log.i("����ʧ��", "����ʧ��: 1");
							String data = "username=" + userName + "&password=" + password;
							URL url = new URL("http://172.168.9.26:8080/Mydemo/servlet/GetMyData?" + data);
							Log.i("����ʧ��", "����ʧ��: 2");
							conn = (HttpURLConnection) url.openConnection();
							conn.setRequestMethod("GET");
							conn.setConnectTimeout(10000); // ���ӵĳ�ʱʱ��
							conn.setReadTimeout(5000); // �����ݵĳ�ʱʱ��
							
							int responseCode = conn.getResponseCode();
							System.out.println("�������ֵ" + responseCode);
							if(responseCode == 200) {
								InputStream is = conn.getInputStream();
								String state = getStringFromInputStream(is);
								Log.e("���������صĽ��", state);
							} else {
								Log.i("����ʧ��", "����ʧ��: " + responseCode);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("������־" + e);
						}
					}
				}).start();
			}
		});
    }
	//�ֽ���ת�ַ���
    private static String getStringFromInputStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		
		while((len = is.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		is.close();
		
		String html = baos.toString();	// �����е�����ת�����ַ���, ���õı�����: utf-8
		
//		String html = new String(baos.toByteArray(), "GBK");
		
		baos.close();
		return html;
	}
    //�����ַ�����MD5ֵ
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

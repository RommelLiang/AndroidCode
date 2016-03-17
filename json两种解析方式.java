package com.nazi.getjson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView one,two;
	private ArrayList<BookMassage> book = new ArrayList<BookMassage>();
	StringBuffer massageBuffer = new StringBuffer();
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				book = (ArrayList<BookMassage>) msg.obj;
//图书编号：xxx+" --- 书名："+xxx+" --- 价格："+xxx+" ---- 作者的详细信息：（姓名+年龄）"
				StringBuffer buffer = new StringBuffer();
				//拼接字符串
				for (int i = 0; i < book.size(); i++) {
					BookMassage b = book.get(i);
					auther a = b.getAuther();
					buffer.append("图书编号："+b.getBookid());
					buffer.append("\n");
					buffer.append("书名：" +b.getBookname());
					buffer.append("\n");
					buffer.append("价格："+b.getPrice());
					buffer.append("\n");
					buffer.append("作者详细信息："+a.getName()+ "+" + a.getAge());
					buffer.append("\n");
					buffer.append("-----------------");
					buffer.append("\n");
				}
				Log.e("更新成功1","更新成功1");
				one.setText(buffer);
				break;
			case 2:
				Toast.makeText(MainActivity.this, "cuowu", 0).show();
				break;
			default:
				break;
			}
		}
		
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        getHtml();
    }
    //链接网络
    public void getHtml() {
		final String url = "http://192.168.1.156/Android/json_data.json";
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String html = getHtmlFromInternet(url);
				
				if(!TextUtils.isEmpty(html)) {
					Message msg = new Message();
					msg.what = 1;
					msg.obj = getJson(html);
					Log.e("wallwe", getJson(html).toString());
					handler.sendMessage(msg);
					final ArrayList<BookMassage> booList = name(html);
					/*final ArrayList<auther> arrayList = getAuthers(html);*/
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							//拼接字符串
							for (int i = 0; i < booList.size(); i++) {
								StringBuffer buffera = new StringBuffer();
								BookMassage b = booList.get(i);
								auther a = b.getAuther();
								buffera.append("图书编号："+b.getBookid());
								buffera.append("\n");
								buffera.append("书名：" +b.getBookname());
								buffera.append("\n");
								buffera.append("价格："+b.getPrice());
								buffera.append("\n");
								//buffera.append("作者详细信息："+arrayList.get(i).getName()+ "+" + arrayList.get(i).getAge());
								buffera.append("作者详细信息："+a.getName()+ "+" + a.getAge());
								buffera.append("\n");
								buffera.append("-----------------");
								buffera.append("\n");
								massageBuffer.append(buffera);
							}
							two.setText(massageBuffer);
							Log.e("更新成功","更新成功");
						}
					});
				} else {
					Message msg = new Message();
					msg.what = 2;
					//handler.sendMessage(msg);
				}
			}
		}).start();
	}
 //获取网页信息
protected String getHtmlFromInternet(String url) {
		
		try {
			URL mURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) mURL.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(10000);//链接超时
			conn.setReadTimeout(5000);//读取超时
//			conn.connect();
			int responseCode = conn.getResponseCode();
			
			if(responseCode == 200) {
				InputStream is = conn.getInputStream();
				String html = getStringFromInputStream(is);
				return html;
			} else {
				Log.i("Main", "链接失败 " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//字符流转换为String
private String getStringFromInputStream(InputStream is) throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	byte[] buffer = new byte[1024];
	int len = -1;
	
	while((len = is.read(buffer)) != -1) {
		baos.write(buffer, 0, len);
	}
	is.close();
	
	String html = baos.toString();
	//解码与重编码
	String charset = "utf-8";
	/*if(html.contains("gbk") || html.contains("gb2312")
			|| html.contains("GBK") || html.contains("GB2312")) {		
		charset = "gbk";
	}*/
	html = new String(baos.toByteArray(),charset);	
	baos.close();
	return html;
}
//将图书信息封装入ArrayList
private ArrayList<BookMassage> getJson(String str){
	ArrayList<BookMassage> book = new ArrayList<BookMassage>();
	Log.e("getJson", "json");
		try {
			JSONArray arr = new JSONArray(str);
			for (int i = 0; i < arr.length(); i++) {
				BookMassage bookMassage = new BookMassage();
				JSONObject jsonObject = (JSONObject) arr.get(i);
				JSONObject au = jsonObject.getJSONObject("author");
				auther auther = new auther();
				Log.e("auther", au.toString());
				auther.setAge(au.getInt("age"));
				auther.setName(au.getString("name"));
				bookMassage.setBookid(jsonObject.getInt("bookid"));
				bookMassage.setBookname(jsonObject.getString("bookname"));
				bookMassage.setPrice(jsonObject.getDouble("price"));
				bookMassage.setAuther(auther);
			    book.add(bookMassage); 
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return book;
}
	//利用Gson获取
	private ArrayList<BookMassage> name(String str) {
		ArrayList<BookMassage> book = new ArrayList<BookMassage>();
		try {
			JSONArray arr = new JSONArray(str);
			for (int i = 0; i < arr.length(); i++) {
				Gson gson = new Gson();
				JSONObject jsonObject = (JSONObject) arr.get(i);
				//解析作者信息
				JSONObject au = jsonObject.getJSONObject("author");
				auther auther = gson.fromJson(au.toString(), auther.class);
				//获取书目信息
				BookMassage bookMassage = gson.fromJson(arr.get(i).toString(), BookMassage.class);
				//封装作者信息
				bookMassage.setAuther(auther);
				//封装书目信息
				book.add(bookMassage);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return book;
	}
	//解析作者信息
	/*private ArrayList<auther> getAuthers(String str) {
		ArrayList<auther> aList =new ArrayList<auther>();
		try {
			JSONArray arr = new JSONArray(str);
			for (int i = 0; i < arr.length(); i++) {
				Gson gson = new Gson();
				JSONObject jsonObject = (JSONObject) arr.get(i);
				JSONObject au = jsonObject.getJSONObject("author");
				auther auther = gson.fromJson(au.toString(), auther.class);
				aList.add(auther);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}*/
}

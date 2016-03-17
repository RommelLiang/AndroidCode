package com.itheima28.netphoto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "MainActivity";
	protected static final int ERROR = 1;
	private EditText etUrl;
	private ImageView ivIcon;
	private final int SUCCESS = 0;
	
	private Handler handler = new Handler() {

		/**
		 * 接收消息
		 */
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			Log.i(TAG, "what = " + msg.what);
			if(msg.what == SUCCESS) {	// 当前是访问网络, 去显示图片
				ivIcon.setImageBitmap((Bitmap) msg.obj);		// 设置imageView显示的图片
			} else if(msg.what == ERROR) {
				Toast.makeText(MainActivity.this, "抓去失败", 0).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ivIcon = (ImageView) findViewById(R.id.iv_icon);
		etUrl = (EditText) findViewById(R.id.et_url);
		
		findViewById(R.id.btn_submit).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		final String url = etUrl.getText().toString();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Bitmap bitmap = getImageFromNet(url);

//				ivIcon.setImageBitmap(bitmap);		// 设置imageView显示的图片
				if(bitmap != null) {
					Message msg = new Message();
					msg.what = SUCCESS;
					msg.obj = bitmap;
					handler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = ERROR;
					handler.sendMessage(msg);
				}
			}}).start();
		
	}

	/**
	 * 根据url连接取网络抓去图片返回
	 * @param url
	 * @return url对应的图片
	 */
	private Bitmap getImageFromNet(String url) {
		HttpURLConnection conn = null;
		try {
			URL mURL = new URL(url);	// 创建一个url对象
			
			// 得到http的连接对象
			conn = (HttpURLConnection) mURL.openConnection();
			
			conn.setRequestMethod("GET");		// 设置请求方法为Get
			conn.setConnectTimeout(10000);		// 设置连接服务器的超时时间, 如果超过10秒钟, 没有连接成功, 会抛异常
			conn.setReadTimeout(5000);		// 设置读取数据时超时时间, 如果超过5秒, 抛异常
			
			conn.connect();		// 开始链接
			
			int responseCode = conn.getResponseCode(); // 得到服务器的响应码
			if(responseCode == 200) {
				// 访问成功
				InputStream is = conn.getInputStream();	// 获得服务器返回的流数据
				
				Bitmap bitmap = BitmapFactory.decodeStream(is); // 根据 流数据 创建一个bitmap位图对象
				
				return bitmap;
			} else {
				Log.i(TAG, "访问失败: responseCode = " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();		// 断开连接
			}
		}
		return null;
	}
}

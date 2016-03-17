package com.itheima28.backupsms;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.itheima28.backupsms.entities.SmsInfo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	/**
	 * 备份短信
	 * @param v
	 */
	public void backupSms(View v) {
		// 1. 查出所有的短信
		Uri uri = Uri.parse("content://sms/");
		
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(uri, new String[]{"_id", "address", "date", "type", "body"}, null, null, null);
		
		if(cursor != null && cursor.getCount() > 0) {
			List<SmsInfo> smsList = new ArrayList<SmsInfo>();
			SmsInfo sms;
			
			while(cursor.moveToNext()) {	// 控制游标结果集的指针向下移一位, 当到最后一位, 停止.返回false
				sms = new SmsInfo();
				sms.setId(cursor.getInt(0));	// 设置短信的id
				sms.setAddress(cursor.getString(1));	// 设置短信的号码
				sms.setDate(cursor.getLong(2));	// 设置短信的日期
				sms.setType(cursor.getInt(3));	// 设置短信的类型, 接收1还是发送2
				sms.setBody(cursor.getString(4)); // 设置短信的内容
				smsList.add(sms);
			}
			cursor.close();
			
			// 2. 序列化到本地
			writeToLocal(smsList);
		}
	}
	
	/**
	 * 序列化到本地
	 */
	private void writeToLocal(List<SmsInfo> smsList) {
		
		try {
			XmlSerializer serializer = Xml.newSerializer();	// 得到序列化对象
			// 指定输出位置
			FileOutputStream fos = new FileOutputStream("/mnt/sdcard/sms.xml");
			serializer.setOutput(fos, "utf-8");
			
			serializer.startDocument("utf-8", true);
			
			serializer.startTag(null, "smss");
			
			for (SmsInfo smsInfo : smsList) {
				serializer.startTag(null, "sms");
				serializer.attribute(null, "id", String.valueOf(smsInfo.getId()));
				
				// 写号码
				serializer.startTag(null, "address");
				serializer.text(smsInfo.getAddress());
				serializer.endTag(null, "address");

				// 写时间
				serializer.startTag(null, "date");
				serializer.text(String.valueOf(smsInfo.getDate()));
				serializer.endTag(null, "date");
				
				//写类型
				serializer.startTag(null, "type");
				serializer.text(String.valueOf(smsInfo.getType()));
				serializer.endTag(null, "type");
				
				// 写内容
				serializer.startTag(null, "body");
				serializer.text(smsInfo.getBody());
				serializer.endTag(null, "body");
				
				serializer.endTag(null, "sms");
			}
			
			serializer.endTag(null, "smss");
			
			serializer.endDocument();
			
			Toast.makeText(this, "备份成功", 0).show();
		} catch (Exception e) {
			Toast.makeText(this, "备份失败", 0).show();
			e.printStackTrace();
		}
		
	}
	//短信javabean对象
	public class SmsInfo {

	private int id;
	private String address;
	private long date;
	private int type;
	private String body;
	public SmsInfo(int id, String address, long date, int type, String body) {
		super();
		this.id = id;
		this.address = address;
		this.date = date;
		this.type = type;
		this.body = body;
	}
	@Override
	public String toString() {
		return "SmsInfo [id=" + id + ", address=" + address + ", date=" + date
				+ ", type=" + type + ", body=" + body + "]";
	}
	public SmsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
}

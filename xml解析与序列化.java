package com.example.nazi.student;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Nzai on 2015/10/23.
 */
public class Data {
	private List<Person> ps;
	private XmlSerializer serializer;
	public void writeXml(List<Person> ps,Context context){
		this.ps=ps;
		File file = context.getFilesDir();
		File path = new File(file,"person.xml");
		try {
		FileOutputStream fos = new FileOutputStream(path);
		//获得序列化对象
		serializer= Xml.newSerializer();
		// 指定序列化对象输出的位置和编码
			serializer.setOutput(fos, "utf-8");
			serializer.startDocument("utf-8", true);	// 写开始 <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
			serializer.startTag(null, "persons");		// <persons>
			for (Person p : ps) {
				//开始写人
				serializer.startTag(null, "person");    // <person>
				// 写名字
				serializer.startTag(null, "name");        // <name>
				serializer.text(p.getName());
				serializer.endTag(null, "name");        // </name>
				//写性别
				serializer.startTag(null, "sex");        // <age>
				serializer.text(String.valueOf(p.getSex()));
				serializer.endTag(null, "sex");        // </age>
				// 写年龄
				serializer.startTag(null, "age");        // <age>
				serializer.text(String.valueOf(p.getAge()));
				serializer.endTag(null, "age");        // </age>
				//xml文件结束
				serializer.endTag(null, "person");    // </person>
			}
			serializer.endTag(null, "persons");            // </persons>
			serializer.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Person> parserXmlFromLocal(Context context) {
		File file = context.getFilesDir();
		File path = new File(file,"person.xml");
		try {
			FileInputStream fis = new FileInputStream(path);
			// 获得pull解析器对象
			XmlPullParser parser = Xml.newPullParser();
			// 指定解析的文件和编码格式
			parser.setInput(fis, "utf-8");
			int eventType = parser.getEventType();
			List<Person> personList = null;
			Person person = null;
			while (eventType != XmlPullParser.END_DOCUMENT){
				String tagName = parser.getName();// 获得当前节点的名称
				switch (eventType){
					case XmlPullParser.START_TAG:
						if("persons".equals(tagName)) {	// <persons>
							personList = new ArrayList<Person>();
						} else if("person".equals(tagName)){
							person = new Person();
						} else if("name".equals(tagName)){
							person.setName(parser.nextText());
						} else if("sex".equals(tagName)){
							person.setSex(parser.nextText());
						} else if("age".equals(tagName)){
							person.setAge(parser.nextText());
						}
						break;
					case XmlPullParser.END_TAG:
						if("person".equals(tagName)) {
							// 需要把上面设置好值的person对象添加到集合中
							personList.add(person);
						}
						break;
					default:
						break;
				}
				eventType = parser.next();		// 获得下一个事件类型
			}
			return personList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

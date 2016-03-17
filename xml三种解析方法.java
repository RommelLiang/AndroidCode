package com.zrrd.parsexml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	
	private Button btnParseByPull, btnParseBySax, btnParseByDom;
	private EditText etShowResult;
	
	//存放解析结果
	private StringBuilder strParseResult = new StringBuilder();//可变字符串
	//StringBuilder（线程不安全，多个线程可操控它，效率高）与StringBuffer（线程安全，在同一个时刻点只有单一的线程可访问它，效率低）的区别？

	//Activity入口
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化按钮监听
        initListener();
    }
    

	@Override
	public void onClick(View v) {
		switch (v.getId()) {//当下所点击按钮的id值
		case R.id.btnParseWithPull:
			strParseResult.append("Pull解析：\n");
			
	        try {
	        	//1、获取数据
	        	//读取assets文件夹中的文件？
	        		//java.io.FileNotFoundException: data.xml
	        		//openXmlResourceParser能打开的是已经编译好的xml文件
//				XmlResourceParser xmlResourceParser = getResources().getAssets().openXmlResourceParser("data.xml");
	        	InputStream is = getResources().getAssets().open("data.xml");
	        	
	        	//2、开始解析
	        	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//单例模式（Java开发模式?懒汉+饿汉，在全局范围内只有一个实例存在，节省资源，安全（synchronized同步锁））
	        	XmlPullParser xmlPullParser = factory.newPullParser();
	        	xmlPullParser.setInput(is, "UTF-8");//将XML数据交给Pull解析器,进行解析
	        	
	        	
	        	/**
	        	 * 从解析器里获取具体的解析结果
	        	 * */
	        	int eventType = xmlPullParser.getEventType();//解析器执行进度，根据不同的进读情况做不同的事（取，存）
				
				//存储解析得到的数据
				String id=null, name=null, version=null;
				
				//1、解析工作未完成
				while(eventType != XmlPullParser.END_DOCUMENT){//while循环的次数，由根节点包含的平级子节点的个数决定
					
					//获取节点名称
					String nodeName = xmlPullParser.getName();
					
					switch (eventType) {
					//2、开始解析某个节点
					case XmlResourceParser.START_TAG:
						if("id".equals(nodeName)){
							id = xmlPullParser.nextText();
						}else if("name".equals(nodeName)){
							name = xmlPullParser.nextText();
						}else if("version".equals(nodeName)){
							version = xmlPullParser.nextText();
						}
						break;
					
					//3、完成解析某个节点
					case XmlResourceParser.END_TAG:
						if("app".equals(nodeName)){//明确是<app>标签的结尾
							//将解析结果收集在StringBuilder对象里
							strParseResult.append("id = "+id+" --- name = "+name+" --- version = "+version+"\n");
						}
						break;

					default:
						break;
					}
					//实时更新事件
					eventType = xmlPullParser.next();
				}
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.btnParseWithSax:
			strParseResult.append("Sax解析：\n");
			try {
				//1、获取数据源
				//R.java：是对res文件夹下所有资源文件，所在的地址统计
				//读取raw文件夹中的文件
				InputStream is = getResources().openRawResource(R.raw.data);
				
				//2、实例化解析器实例
				SAXParserFactory factory = SAXParserFactory.newInstance();
				XMLReader xmlReader = factory.newSAXParser().getXMLReader();
				//明确解析器按什么样的规则解析？
				xmlReader.setContentHandler(new ContentHandler());
				//3、将数据源交给解析器，开始解析
				xmlReader.parse(new InputSource(is));
				
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.btnParseWithDom:
			strParseResult.append("Dom解析：\n");
			//如何给Android虚拟机的SD卡导入文件？
			//如何读取SD卡中的文件？（权限）
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//判断SD卡是否挂载
				//1、获取数据源
				File file = new File(Environment.getExternalStorageDirectory(), "data.xml");
				try {
					//2、构建解析
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					//先把整个的xml文件载入（读取到内存中）
					Document document = builder.parse(file);//<apps>文档根节点
					
					//3、获取解析内容 By Document
					NodeList appList = document.getElementsByTagName("app");
					for(int i=0; i <appList.getLength(); i++){
						Node app =  appList.item(i);
						NodeList childNodeList = app.getChildNodes();
						//3（id, name, version）
						for(int j=0; j<childNodeList.getLength(); j++){
							Node idNameVersion = childNodeList.item(j);
							if(idNameVersion instanceof Element){//去掉#text(乱码)
								strParseResult.append(idNameVersion.getNodeName()+" = "+idNameVersion.getTextContent()+"---");
							}
						}
						strParseResult.replace(strParseResult.length()-3, strParseResult.length(), "");
						strParseResult.append("\r\n");
					}
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				Toast.makeText(MainActivity.this, "SD卡不可用！", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		//将解析结果设置给EditText
		etShowResult.setText(strParseResult.toString());
	}
    
    private void initListener(){
    		btnParseByPull.setOnClickListener(this);
    		btnParseBySax.setOnClickListener(this);
    		btnParseByDom.setOnClickListener(this);
    }
    
    private void initView(){
    		btnParseByPull = (Button) findViewById(R.id.btnParseWithPull);
    		btnParseBySax = (Button) findViewById(R.id.btnParseWithSax);
    		btnParseByDom = (Button) findViewById(R.id.btnParseWithDom);
    		etShowResult = (EditText) findViewById(R.id.etShowParseResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    //SAX解析必备子类(接口回调)，解析进度通知，
    class ContentHandler extends DefaultHandler{
    		private String nodeName;
    		private StringBuilder id, name, version;
    		
    		// 开始解析XML文件时调用（初始化变量容器，以便下面存储数据）
    		@Override
    		public void startDocument() throws SAXException {
    			id = new StringBuilder();
    			name = new StringBuilder();
    			version = new StringBuilder();
    		}
    		
    		//开始解析某个节点时调用
    		@Override
    		public void startElement(String uri, String localName, String qName,
    			Attributes attributes) throws SAXException {
    			//记录当前节点名
    			nodeName = localName;
    		}
    		
    		//获取节点中的内容时调用（会被调用多次！）(读取字节流)
    		@Override
    		public void characters(char[] ch, int start, int length)
    			throws SAXException {
    			//根据当前的节点名判断将内容添加到哪一个StringBuilder对象中
    			if("id".equals(nodeName)){
    				id.append(ch, start, length);//追加
    			} else if("name".equals(nodeName)){
    				name.append(ch, start, length);
    			} else if("version".equals(nodeName)){
    				version.append(ch, start, length);
    			}
    		}
    		
    		//完成解析某个节点时调用
    		@Override
    		public void endElement(String uri, String localName, String qName)
    			throws SAXException {
    			if("app".equals(localName)){
    				strParseResult.append("id = "+id.toString().trim()+" --- name = "+name.toString().trim()+" --- version = "+version.toString().trim()+" \n");
    				//最后将StringBuilder清空掉
    				id.setLength(0);
    				name.setLength(0);
    				version.setLength(0);
    			}
    		}
    		
    		//完成整个xml解析时调用（标记解析工作结束）
    		@Override
    		public void endDocument() throws SAXException {
    		// TODO Auto-generated method stub
    		super.endDocument();
    		}
    }

}

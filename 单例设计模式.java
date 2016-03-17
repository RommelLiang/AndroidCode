package com.heima.thread;

public class Demo1_Singleton {

	/**
	 * @param args
	 * * �������ģʽ����֤�����ڴ���ֻ��һ������
	 */
	public static void main(String[] args) {
		//Singleton s1 = new Singleton();
		
		Singleton s1 = Singleton.s;				//��Ա������˽��,����ͨ������.����
		//Singleton.s = null;
		Singleton s2 = Singleton.s;
		
		System.out.println(s1 == s2);
		
	/*	Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		
		System.out.println(s1 == s2);*/
	}

}

/*
 * ����ʽ
 
class Singleton {
	//1,˽�й��췽��,�����಻�ܷ��ʸù��췽����
	private Singleton(){}
	//2,�����������
	private static Singleton s = new Singleton();
	//3,�����ṩ�����ķ��ʷ���
	public static Singleton getInstance() {				//��ȡʵ��
		return s;
	}
}*/
/*
 * ����ʽ,�������ӳټ���ģʽ
 */
/*class Singleton {
	//1,˽�й��췽��,�����಻�ܷ��ʸù��췽����
	private Singleton(){}
	//2,����һ������
	private static Singleton s ;
	//3,�����ṩ�����ķ��ʷ���
	public static Singleton getInstance() {				//��ȡʵ��
		if(s==null){
            synchronized(Singleton.class){
                if(s==null){
                    s=new Singleton();
                }
            }
        }

		
		return s;
	}
}*/
/*
public class SingletonClass{
    private static SingletonClass instance=null;
    public static��synchronized��SingletonClass getInstance()
    {
        if(instance==null)
        {
               instance=new SingletonClass();
        }
        return instance;
    }
    private SingletonClass(){
    }
}
 */
/*
 * ����ʽ������ʽ������
 * 1,����ʽ�ǿռ任ʱ��,����ʽ��ʱ�任�ռ�
 * 2,�ڶ��̷߳���ʱ,����ʽ���ᴴ���������,������ʽ�п��ܻᴴ���������
 */
 ��һ����ʽ:����ʽ��Ҳ�ǳ��õ���ʽ��

public class SingletonClass{
    private static SingletonClass instance=null;
    public static��synchronized��SingletonClass getInstance()
    {
        if(instance==null)
        {
               instance=new SingletonClass();
        }
        return instance;
    }
    private SingletonClass(){
    }
}
 

�ڶ�����ʽ:����ʽ

 
//�Ե�һ��static��һЩ����
// java����������һ�������涨�徲̬�ࡣ�����ڲ��ࣨnested class����
//��nested class�������������ⲿ�ࡣ
//��java�У����ǲ�����static���ζ����ࣨtop level class����
//ֻ���ڲ������Ϊstatic��
public static class Singleton{
    //���Լ��ڲ������Լ���һ��ʵ����ֻ���ڲ�����
    private static final Singleton instance = new Singleton();
    private Singleton(){
        //do something
    }
    //�����ṩ��һ�����ⲿ���ʱ�class�ľ�̬����������ֱ�ӷ���
    public static Singleton getInstance(){
        return instance;
    }
}
 

��������ʽ: ˫��������ʽ��

 
public static class Singleton{
    private static Singleton instance=null;
    private Singleton(){
        //do something
    }
    public static Singleton getInstance(){
        if(instance==null){
            synchronized(Singleton.class){
                if(null==instance){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
}
 


class Singleton {
	//1,˽�й��췽��,�����಻�ܷ��ʸù��췽����
	private Singleton(){}
	//2,����һ������
	public static final Singleton s = new Singleton();
	
}

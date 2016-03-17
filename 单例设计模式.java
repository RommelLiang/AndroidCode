package com.heima.thread;

public class Demo1_Singleton {

	/**
	 * @param args
	 * * 单例设计模式：保证类在内存中只有一个对象。
	 */
	public static void main(String[] args) {
		//Singleton s1 = new Singleton();
		
		Singleton s1 = Singleton.s;				//成员变量被私有,不能通过类名.调用
		//Singleton.s = null;
		Singleton s2 = Singleton.s;
		
		System.out.println(s1 == s2);
		
	/*	Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		
		System.out.println(s1 == s2);*/
	}

}

/*
 * 饿汉式
 
class Singleton {
	//1,私有构造方法,其他类不能访问该构造方法了
	private Singleton(){}
	//2,创建本类对象
	private static Singleton s = new Singleton();
	//3,对外提供公共的访问方法
	public static Singleton getInstance() {				//获取实例
		return s;
	}
}*/
/*
 * 懒汉式,单例的延迟加载模式
 */
/*class Singleton {
	//1,私有构造方法,其他类不能访问该构造方法了
	private Singleton(){}
	//2,声明一个引用
	private static Singleton s ;
	//3,对外提供公共的访问方法
	public static Singleton getInstance() {				//获取实例
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
    public static　synchronized　SingletonClass getInstance()
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
 * 饿汉式和懒汉式的区别
 * 1,饿汉式是空间换时间,懒汉式是时间换空间
 * 2,在多线程访问时,饿汉式不会创建多个对象,而懒汉式有可能会创建多个对象
 */
 第一种形式:懒汉式，也是常用的形式。

public class SingletonClass{
    private static SingletonClass instance=null;
    public static　synchronized　SingletonClass getInstance()
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
 

第二种形式:饿汉式

 
//对第一行static的一些解释
// java允许我们在一个类里面定义静态类。比如内部类（nested class）。
//把nested class封闭起来的类叫外部类。
//在java中，我们不能用static修饰顶级类（top level class）。
//只有内部类可以为static。
public static class Singleton{
    //在自己内部定义自己的一个实例，只供内部调用
    private static final Singleton instance = new Singleton();
    private Singleton(){
        //do something
    }
    //这里提供了一个供外部访问本class的静态方法，可以直接访问
    public static Singleton getInstance(){
        return instance;
    }
}
 

第三种形式: 双重锁的形式。

 
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
	//1,私有构造方法,其他类不能访问该构造方法了
	private Singleton(){}
	//2,声明一个引用
	public static final Singleton s = new Singleton();
	
}

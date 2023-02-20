package com.yanhao.jvm23;

/**
 * javax开始的就是java 拓展包
 * <p>
 * abstract抽象类只能被 继承；不能被 实现
 * <p>
 * 虚拟机自带的类加载器：
 * 1>启动类加载器：BootStrap
 * 2>扩展类加载器：Extension
 * 3>应用程序类加载器：AppClassLoader
 * <p>
 * 用户自定义加载器：
 * java.lang.ClassLoader的子类
 */
public class MyObject {
    public static void main(String[] args) {
        Object o = new Object();
        //null
        System.out.println(o.getClass().getClassLoader());


        MyObject myObject = new MyObject();
        //bootStrap--->null
        System.out.println("parent.parent :" + myObject.getClass().getClassLoader().getParent().getParent());
        //Extension--->sun.misc.Launcher$ExtClassLoader@4361bd48
        System.out.println("parent:" + myObject.getClass().getClassLoader().getParent());
        //AppClassLoader--->sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(myObject.getClass().getClassLoader());

        System.out.println(myObject.getClass());
        System.out.println(myObject.getClass().getName());
    }
}

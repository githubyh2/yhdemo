package com.yanhao.juc23;

import java.util.concurrent.TimeUnit;


/**
 * 注意：
 * 1、普通同步方法：锁是当前实例对象
 * 2、静态同步方法：锁是当前类的class对象
 * <p>
 * 只要在一个资源类中，不管有多少个synchroniezd同步方法，
 * 只要一个线程，先访问了资源类中的任何一个同步方法，它锁的不是这一个方法，而是该方法所在的这个资源类，【是锁的这个对象】
 * <p>
 * 总结：
 * 一个对象【资源类】里面，如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了，
 * 其他的线程只能等待，换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronized方法
 * 1~4锁：锁的是当前对象 this，被锁定后，其他的线程都不能进入到当前对象的其他的synchronized方法 【俗称对象锁】
 * 多个this来自于同一个Class
 * <p>
 * 加个普通方法后，发现和同步锁无关
 * 换成两个对象后，不是同一把锁了，情况立刻变化。
 * <p>
 * 5，6锁： 两个静态同步方法 static synchronized 【全局锁】
 * static属于整个类的，不是指当前对象this
 * <p>
 * Synchronized实现同步的基础：java中的每一个对象都可以作为锁，
 * 具体表现为一下 3 种形式：
 * 普通同步方法，锁是当前实例对象 ---> 即 new 出来的具体内容。
 * 静态同步方法，锁是当前类的Class对象
 * 同步方法块，锁是Synchronized括号里面配置的对象
 *
 * 注意：
 * 普通同步方法：
 * public synchronized void test1(){
 *
 * }
 *
 * 同步方法块：
 * public void test2(){
 *     synchronized (this){
 *
 *     }
 * }
 * <p>
 * 当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时候，必须释放锁。
 * 也就是说，如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁之后，才能获取锁。
 * 但是别的实例对象的非静态同步方法，因为跟该实例对象的非静态同步方法用的是不同的锁，
 * 所以无须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 * <p>
 * 所有的静态同步方法static synchronized用的也是同一把锁---Class类对象本身，
 * 这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有静态条件的。
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
 * 不管是同一个实例对象的静态同步方法之间，
 * 还是不同的实例对象的静态同步方法之间，只要他们同一个类的实例对象！
 * <p>
 * java中的反射 Class.forName();
 * 对象锁 和 全局锁 的区别：
 */

//资源类
class Phone {

    //静态同步方法 在synchroniezd 前添加static
    public static synchronized void sendEmail() throws Exception {

        //注意TimeUnit是juc中的
        TimeUnit.SECONDS.sleep(4);
        System.out.println("使用juc中的TimeUnit-->延时4秒。。。。");
        System.out.println("****** sendEmail 发送邮件");
    }

    //普通同步方法
    public static synchronized void sendSMS() throws Exception {
        System.out.println("****** sendSMS 发送短信");
    }

    //普通方法
    public void sayHello() throws Exception {
        System.out.println("****** sayHello");
    }
}

/**
 * 8种 Lock 情况
 * 1、标准访问 ，请问先打印邮件还是短信 -->先邮件 后短信
 * 2、暂停4秒钟在邮件方法，请问先打印邮件还是短信 -->先邮件 后短信
 * 3、新增普通sayHello 方法，请问先打印邮件还是hello -->先hello 后短信
 * 4、两部手机，请问先打印邮件还是短信 -->先短信 后邮件
 * 5、两个静态同步方法，1部手机，请问先打印邮件还是短信 -->先邮件 后短信
 * 6、两个静态同步方法，2部手机，请问先打印邮件还是短信 -->先邮件 后短信
 * 7、一个静态同步方法，一个普通同步方法，1部手机，请问先打印邮件还是短信 -->先短信 后邮件
 * 8、一个静态同步方法，一个普通同步方法，2部手机，请问先打印邮件还是短信 -->先短信 后邮件
 */
public class Lock8Demo05 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendSMS();
                //phone.sayHello();
                //phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

    }
}

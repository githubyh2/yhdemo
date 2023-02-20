package com.yanhao.juc23;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket //资源类= 实例变量+实例方法
{
    //number相当于有 30 张票
    private int number = 30;

    //左边是接口    右边是实现类  （java中的多态）
    //注意接口是特殊的类，接口是可以new的
    //List list = new ArrayList();

    /**
     * java.util.concurrent.locks中的接口有：
     * Condition 、Lock 、ReadWriteLock
     *
     * Interface Lock 接口lock中的所有实现类：
     * All Known Implementing Classes:
     * ReentrantLock, ReentrantReadWriteLock.ReadLock, ReentrantReadWriteLock.WriteLock
     *
     * 样例代码：
     * Lock l = ...;
     *  l.lock();
     *  try {
     *    // access the resource protected by this lock 访问受此锁保护的资源
     *  } finally {
     *    l.unlock();
     *  }
     *
     */

    //可重复锁 Re entrant Lock
    Lock lock = new ReentrantLock();

    //实现类方法
    public void sale() {
        //上锁
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t 还剩下："+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放锁
            lock.unlock();
        }

    }

}

/**
 * 编写企业级别的多线程
 * 三个卖票员 ， 卖出30张票 ，通过多线程方式实现
 *
 * 固定的编程套路+模板是什么？
 * 1、高内聚低耦合的前提下：线程  操作  资源类
 * 1.1先创建一个资源类
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) //主线程，一切程序的入口
    {
        Ticket ticket = new Ticket(); //资源类
        //线程
//        Thread t1 = new Thread();
//        Thread t2 = new Thread();
//        Thread t3 = new Thread();

        //一个线程.start()之后不是立刻执行；多线程的调度是和底层的操作系统和cpu有关系的
        //什么时候被调用是不确定的。

        /**
         * Thread.State中可以查看Thread的状态，多线程一共有6中状态
         * NEW：新建
         * RUNNABLE：运行
         * BLOCKED：阻塞，等待锁
         * WAITING：等待，等待其它线程（不见不散，傻傻的等）
         * TIMED_WAITING：等待，定时等待其它线程（过时不候，过了时间就撤了）
         * TERMINATED：执行完毕，已死亡
         */
        //使用lambda表达式实现
        new Thread(()->{for (int i = 0; i <= 40; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 0; i <= 40; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 0; i <= 40; i++) ticket.sale();},"C").start();

        //匿名内部类实现
        //Thread(Runnable target, String name)  Allocates a new Thread object.
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        },"").start();
         */

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();
         */


    }
}

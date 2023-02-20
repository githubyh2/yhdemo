package com.yanhao.juc23;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 现在有两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对变量+1，一个线程对该变量-1，
 * 实现交替，来10轮，变量初始值为零。
 * <p>
 * 注意：多线程操作资源类需要加锁
 * <p>
 * 1、高内聚低耦合前提下，线程操作资源类 【卖票】需要上锁
 * 2、判断/干活/通知
 * 3、防止多线程的虚假唤醒【判断的时候使用while】
 * <p>
 * 注意：多线程编程套路+while判断+新版写法
 */

//空调类[资源类]
class Aircondition {
    private int number = 0;

    //ReentrantLock可重复的非公平锁
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //判断
            while (number != 0) {
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            //判断
            while (number == 0) {
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

/*

    public synchronized void increment() throws Exception {
        //多线程的调度，交互判断，必须使用while
        //while的本质是：循环+判断
        //1、判断
        while (number != 0) {
            this.wait();
        }
        //2、干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3、通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception {
        //1、判断
        while (number == 0) {
            this.wait();
        }
        //2、干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //3、通知
        this.notifyAll();
    }
*/

}

public class ProdConsumerDemo04 {
    public static void main(String[] args) throws Exception {
        Aircondition aircondition = new Aircondition();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(200);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(300);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(400);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();


    }
}

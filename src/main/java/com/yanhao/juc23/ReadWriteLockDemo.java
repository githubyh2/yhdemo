package com.yanhao.juc23;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁）
 * 共享锁（读锁）
 * ReadWriteLock
 * 读-读 可以共存
 * 读-写 不可以共存
 * 写-写 不可以共存
 */

/**
 * 加锁的缓存
 */
class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    //读写锁：更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //存放= 写入：只希望同时只有一个线程写
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t---开始写入" + key);
            //暂停一会儿
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t---写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    //取=  读取：所有人都可以读
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t开始读取");
            //暂停一会儿
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取成功" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }


    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCacheLock = new MyCacheLock();

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

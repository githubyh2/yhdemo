package com.yanhao.juc23;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//信号量semaphore
public class SemaphoreDemo {
    public static void main(String[] args) {

        //模拟资源类，有3个空车位
        Semaphore semaphore = new Semaphore(3);


        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢占到了车位");
                    //线程暂停4秒
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(Thread.currentThread().getName() + "\t离开了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}

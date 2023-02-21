package com.yanhao.juc23;

import java.util.concurrent.CountDownLatch;

/**
 * 控制线程顺序
 * CountDownLatch减法计数器：
 * CountDownLatch:主要有两个方法，当一个或多个线程调用await() 方法的时候，这些线程会阻塞；
 * 其他线程调用countDown()方法会将计数器减1（调用countDown()方法的线程不会阻塞），
 * 当计数器的值变为0时，因await()方法阻塞的线程会被唤醒，继续执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {

        countDownLatchCloseDoor();

    }

    private static void countDownLatchCloseDoor() {
        //CountDownLatch 使用一个计数器进行实现。计数器初始值为线程的数量
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " go out");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "Close door");
    }

    //closeDoor()该方法会出现主线程先关闭，子线程后运行的情况
    private static void closeDoor() {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " go out");
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "Close door");
    }
}

package com.yanhao.juc23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过 Executors 创建的线程池
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) {
        //newFixedThreadPool(3);创建⼀个固定⼤⼩的线程池，可控制并发的线程数，超出的线程会在队列中等待；
//        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //创建单个线程数的线程池，它可以保证先进先出的执⾏顺序；
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //创建⼀个可缓存的线程池，若线程数超过处理所需，缓存⼀段时间后会回收，若线程数不够，则新建线程；【一池N个工作线程】
        ExecutorService executorService = Executors.newCachedThreadPool();

        //模拟有10个人在银行办理业务
        for (int i = 1; i <= 10; i++) {
            /*try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 办理业务");
            });
        }
        executorService.shutdown();

    }
}

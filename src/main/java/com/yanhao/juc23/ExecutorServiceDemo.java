package com.yanhao.juc23;

import java.util.concurrent.*;

/**
 * 通过 Executors 创建的线程池
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) {

        System.out.println("逻辑处理器数:" + Runtime.getRuntime().availableProcessors());

        final Integer corePoolSize = 2;
        final Integer maximumPoolSize = 5;
        final Long keepAliveTime = 1L;

        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                /**
                 * 线程池的拒绝策略有4种：
                 * ThreadPoolExecutor.AbortPolicy()默认拒绝策略【触发条件是，请求的线程大于 阻塞队列大小 + 最大线程数】：java.util.concurrent.RejectedExecutionException 直接阻止系统正常运行
                 * CallerRunsPolicy():"调用者运行"一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
                 * DiscardOldestPolicy():抛弃队列种等待最久的任务，然后把当前任务加入到队列种，尝试再次提交当前任务
                 * DiscardPolicy():该策略默默的丢弃无法处理的任务，不予任何处理也不抛异常。如果允许任务丢失，这是最好的一种策略
                 */
                new ThreadPoolExecutor.AbortPolicy());


        for (int i = 1; i <= 8; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 办理业务");
            });
        }

        executorService.shutdown();


    }

    private static void initPool() {
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

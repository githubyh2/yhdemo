package com.yanhao.juc23;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//加法计数器
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        cyclicbarrieruse();


    }

    private static void cyclicbarrieruse() {
        //CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println(Thread.currentThread().getName() + "----aaaa");
        });

        for (int i = 1; i <= 7; i++) {

            //Lambad Express 变量需要被 final 修饰
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "---------" + temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }
}

package com.yanhao.javaStuday;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerDemo {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        //schedule(TimerTask task,long delay)
        // 安排在指定延迟后执行指定的任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("OK");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, 5000);


        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            //暂停1s
            TimeUnit.SECONDS.sleep(1);
        }


    }
}

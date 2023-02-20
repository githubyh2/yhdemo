package com.yanhao.juc23;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Runnable 和 Callable 接口的区别：
 * 方法不同：Runnable接口中是run() 方法；Callable接口中是 call() 方法
 * 是否抛异常：Runnable接口 不抛异常 ； Callable接口 会有异常抛出
 * 是否有返回值：Runnable接口 没有返回值； Callable接口 有返回值
 */

//有返回值的线程接口Callable
//Callable接口的返回值是可定义的，泛型是什么类型，返回的就是什么类型
class Mythread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("*******come in method()");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * java.lang.Object
         * java.util.concurrent.FutureTask<V>
         *
         * All Implemented Interfaces:
         * Runnable, Future<V>, RunnableFuture<V>
         */
        //FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask(new Mythread());

        new Thread(futureTask, "A").start();

        //从FutureTask中获取返回值
        Integer result = futureTask.get();

        System.out.println(result);


    }
}

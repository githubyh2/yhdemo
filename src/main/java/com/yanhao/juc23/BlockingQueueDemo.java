package com.yanhao.juc23;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列：
 * 队列空的时候，消费被阻塞
 * 队列满的时候，生产被阻塞
 * <p>
 * 抛出异常：
 * 1、当阻塞队列满时，再往队列中add(E e) 插入元素会抛出异常：java.lang.IllegalStateException: Queue full
 * 2、当阻塞队列空时，再往队列中remove() 移除元素会抛出异常：java.util.NoSuchElementException
 * <p>
 * 特殊值：
 * 1、插入方法offer(e)：成功显示true，失败显示false
 * 2、取出方法poll()：成功返回队列中的元素，队列中没有元素，则返回 null
 * <p>
 * 一直阻塞：
 * 1、当阻塞队列满时，生产者线程继续往队列里 put(e) 元素，队列会一直阻塞生产者线程直到put 数据 or 响应中断退出
 * 2、当阻塞队列空时，消费者线程试图从队列里 take() 元素，队列会一直阻塞消费者线程 直到队列可用
 * <p>
 * 超时退出：
 * 插入：offer(e, time, unit)  取出：poll(time, unit)
 * 当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        //ArrayBlockingQueue:由数组结构组成的有界队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //插入数据超过 数组的容量的时候，超时时间是3s，过了3s就会结束
        System.out.println(blockingQueue.offer("d", 3L, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(4L, TimeUnit.SECONDS));


    }
}

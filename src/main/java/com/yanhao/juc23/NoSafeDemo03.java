package com.yanhao.juc23;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ArrayList线程不安全
 * Vector是线程安全的
 * <p>
 * 1、故障现象
 * java.util.ConcurrentModificationException 并发修改异常
 *
 * <p>
 * <p>
 * 2、导致原因
 * 多线程并发争抢同一个资源类
 * <p>
 * <p>
 * 3、解决方案
 * 3.1 new Vector<>();
 * 3.2 使用集合的Collections工具类：Collections.synchronizedList(new ArrayList<>());
 * 3.3 new CopyOnWriteArrayList<>();
 * <p>
 * <p>
 * 4、优化建议
 */
public class NoSafeDemo03 {
    public static void main(String[] args) {

        //idea中进行方法提取的快捷键是 ： Ctrl + Alt + M

//        listNotSafe();

//        setNotSafe();

        hashMapNotSafe();

    }

    public static void hashMapNotSafe() {
        //HashMap是线程不安全的，多个线程操作的时候，会出现： java.util.ConcurrentModificationException
        //Map<String, String> map = new HashMap<>();

        Map<String, String> map = new ConcurrentHashMap<>();

        /*for (int i = 1; i <= 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(map);
                }
            }, String.valueOf(i)).start();
        }*/

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }).start();
        }
    }

    public static void setNotSafe() {
        /**
         * HashSet 底层数据结构是：HashMap
         *
         * 源码显示如下：
         *      public HashSet() {
         *         map = new HashMap<>();
         *     }
         */
        //HashSet是线程不安全的，也会出现：java.util.ConcurrentModificationException
        //Concurrent Modification Exception / Concurrent Modification Exception
        //Set<String> set = new HashSet<>();

        Set<String> set = new CopyOnWriteArraySet<>();
        /*for (int i = 1; i <=30 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(set);
                }
            },String.valueOf(i)).start();
        }*/
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    public static void listNotSafe() {
        //1、ArrayList是线程不安全的，多个线程并发操作的时候，容易出现 java.util.ConcurrentModificationException 并发修改异常
//        List<String> list = new ArrayList<>();

        //2、Vector是线程安全的；但是在添加元素的时候是 加锁 的
//        List<String> list = new Vector<>();

        //3、使用 Collections
        // Collections 是集合接口的工具类  是一个集合框架的帮助类，里面包含一些对集合的排序，搜索以及序列化的操作
        // Collection  是集合类的上层接口，【java.util中的Collection接口】本身是一个Interface，里面包含了一些集合的基本操作
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        //4、CopyOnWriteArrayList  copy on write ,在写的时候进行复制，写时复制【读写分离思想，读和写不同的容器】
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 写时复制 Copy on Write
 * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器 Object[] 添加，而是先将当前容器 Object[] 进行 Copy
 * 复制出一个新的容器 Object[] newElements， 然后新的容器 Object[] newElements 中添加元素，添加完成之后，
 * 再将原容器的引用指向新的容器 setArray(newElements); 这样做的好处是可以对 CopyOnWrite容器 进行并发读，
 * 而不需要加锁，因为当前容器不会添加任何元素。 所以 CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 * <p>
 * <p>
 * public boolean add(E e) {
 * final ReentrantLock lock = this.lock;
 * lock.lock();
 * try {
 * Object[] elements = getArray();
 * int len = elements.length;
 * Object[] newElements = Arrays.copyOf(elements, len + 1);
 * newElements[len] = e;
 * setArray(newElements);
 * return true;
 * } finally {
 * lock.unlock();
 * }
 * }
 */

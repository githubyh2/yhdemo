package com.yanhao.jvm23.gc;

import java.util.Random;

/**
 * 查看默认的垃圾回收机制是哪一个命令：
 * java -XX:+PrintCommandLineFlags -version
 * java8：默认使用的是 -XX:+UseParallelGC
 * <p>
 * 默认的垃圾回收机制有 7 种：
 * <p>
 * 新生代和老年代分别的垃圾回收器：[G1垃圾回收机制不再区分年轻代和老年代]
 * young Gen(使用复制算法)：Serial Copying[UseSerialGC], Parallel Scavenge[UseParallelGC], ParNew[UseParNewGC]
 * old Gen(标记清楚算法):Serial MSC(Serial Old) , Parallel Compacting(Parallel Old)[UseParallelOldGC] , CMS[UseConcMarkSweepGC]
 * <p>
 * 1、 Serial 串行收集器（一对一）：
 * -XX:+PrintGCDetails 打印GC的详情  -XX:+PrintCommandLineFlags 查看默认使用的垃圾回收机制
 * 注意：如果Server端配置的是UseSerialGC 则默认的老年代使用的是UseSerialOldGC
 * 使用参数配置：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC  （DefNew+Tenured）
 * <p>
 * 2、 ParNew 并行收集器（多对一）：其实就是Serial收集器新生代的【并行多线程版本】，老年代 还是使用的 【单个GC Thread进行垃圾回收】
 * 注意：a、>如果Server端 配置的是UseParNewGC【启用ParNew收集器，只影响新生代，不影响老年代】
 * b、>Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 * 使用参数配置：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC    （ParNew+Tenured）
 * 【备注】：-XX:ParallelGCThreads ：限制线程数量。默认开启和cpu数目相同的线程数
 * <p>
 * 3、 并行收集器（多对多）：新生代和老年代的并行化
 * 使用参数配置：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC  （PSYoungGen+ParOldGen）
 * <p>
 * 4、
 * 4.1
 * 使用参数配置：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC  （PSYoungGen+ParOldGen）
 * 4.2 不加默认使用的是  -XX:+UseParallelGC
 * 使用参数配置：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags                        （PSYoungGen+ParOldGen）
 * <p>
 * 5、并发标记清除：并发收集低停顿，并发指的是与用户线程一起执行
 * 注意：使用-XX:+UseConcMarkSweepGC (OldGen区)开启老年代的并发标记清除， 会自动将-XX:+UseParNewGC （youngGen区）打开
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC  （ParNew+CMS）
 *
 * 6、使用G1垃圾回收机制
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        int num = runtime.availableProcessors();
        System.out.println("******helloGC   " + "处理器的个数为：" + num);
        //Thread.sleep(Integer.MAX_VALUE);

        try {
            String str = "yanhao";
            while (true) {
                str += str + new Random().nextInt(77777777) + new Random().nextInt(88888888);
                str.intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}

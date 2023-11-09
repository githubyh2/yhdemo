package com.yanhao.jvm23.oom;

import java.util.Random;

/**
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 堆内存异常的原因：对象过大
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {

        System.out.println("当前最大可用内存是多少M：" + Runtime.getRuntime().maxMemory() / 1024 / 1024);

        /**
         * idea中进行vm option配置：-Xms10m -Xmx10m
         */
        //堆内存异常：Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        //1、通过创建一个大对象
        //byte[] bytes = new byte[80 * 1024 * 1024];

        //2、通过循环创建新对象
        String str = "yanhao";
        while (true) {
            str += str + new Random().nextInt(11111111) + new Random().nextInt(22222222);
            str.intern();
        }
    }
}

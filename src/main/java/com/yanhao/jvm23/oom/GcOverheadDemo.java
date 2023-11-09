package com.yanhao.jvm23.oom;

import java.util.ArrayList;

/**
 * jvm参数演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */

public class GcOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        ArrayList<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(i++));
            }

        } catch (Throwable e) {
            System.out.println("***********i:" + i);
            e.printStackTrace();
            throw e;
        }


    }
}

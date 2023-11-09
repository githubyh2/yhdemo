package com.yanhao.jvm23.oom;

/**
 * 栈溢出:方法的深度调用会出现栈溢出
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        /**
         * java.lang.StackOverflowError是java虚拟机的Error错误
         */
        //Exception in thread "main" java.lang.StackOverflowError
        stackOverflowError();
    }
}

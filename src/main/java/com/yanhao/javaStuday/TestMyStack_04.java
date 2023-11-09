package com.yanhao.javaStuday;

import com.yanhao.util.MyStackUtil;

public class TestMyStack_04 {
    public static void main(String[] args) {

        MyStackUtil stackUtil = new MyStackUtil();

        stackUtil.push(9);
        stackUtil.push(8);
        stackUtil.push(7);
        stackUtil.push(11);

        System.out.println("获取栈顶元素是：" + stackUtil.peek());
        System.out.println("数组长度为：" + stackUtil.getLength());
        stackUtil.pop();
        System.out.println("获取栈顶元素是：" + stackUtil.peek());
        System.out.println("数组长度为：" + stackUtil.getLength());


    }
}

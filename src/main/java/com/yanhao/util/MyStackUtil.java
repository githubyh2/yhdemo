package com.yanhao.util;

// 栈 的 使用
public class MyStackUtil {
    // 创建一个存放元素的数组
    private int[] elements;

    //
    public MyStackUtil() {
        elements = new int[0];
    }

    // 压栈push
    public void push(int element) {

        // 创建一个新数组，是原数组的长度 + 1
        int[] newArr = new int[elements.length + 1];
        // 将原数组的数，放入新数组中
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        // 把新加的数据添加到数组 末尾
        newArr[elements.length] = element;
        // 使用新数组 替换 旧数组
        elements = newArr;
    }

    // 出栈pop
    public int pop() {
        // 如果栈中没有元素
        if (elements.length == 0) {
            throw new RuntimeException("stack is empty");
        }

        // 取出最后一个元素
        int element = elements[elements.length - 1];
        // 创建新数组 , 数组长度是原数组长度 - 1
        int[] newArr = new int[elements.length - 1];

        //
        for (int i = 0; i < elements.length - 1; i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;

        return element;
    }

    // 查看栈顶元素
    public int peek() {
        return elements[elements.length - 1];
    }

    // 查看数组长度
    public int getLength() {
        return elements.length;
    }


}

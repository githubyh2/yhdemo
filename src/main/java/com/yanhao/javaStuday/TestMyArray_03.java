package com.yanhao.javaStuday;

import com.yanhao.util.MyArrayUtil;

public class TestMyArray_03 {
    public static void main(String[] args) {
        // 创建一个可变的数组
        MyArrayUtil myArray = new MyArrayUtil();
        // 获取长度
        int size = myArray.size();

        System.out.println("数组的长度是：" + size);

        myArray.showElement();

        // 往数组中添加元素
        myArray.addElement(99);
        myArray.addElement(98);
        myArray.addElement(97);

        // 显示可变数组中的元素到控制台
        myArray.showElement();
        System.out.println("添加元素后的数组长度是：" + myArray.size());

//        myArray.deleteElement(2);
//        myArray.showElement();

        System.out.println("==========");
        myArray.addElement(96);
        myArray.addElement(95);
        myArray.addElement(94);
        myArray.showElement();

        myArray.insertElement(6, 93);

        myArray.showElement();
        System.out.println("插入元素到指定位置后，数组的长度：" + myArray.size());

        // 替换指定位置元素
        myArray.setElements(3, 100);
        myArray.showElement();

        System.out.println("获取指定索引位置的元素：" + myArray.getElement(5));

        int i = myArray.ArrayLinearSearch(88);
        System.out.println("线性查找-----获取到指定元素的索引位置是：" + i);

        int ii = myArray.ArrayBinarySearch(99);
        System.out.println("二分查找-----获取到指定元素的索引位置是：" + ii);

    }
}

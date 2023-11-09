package com.yanhao.util;

import java.util.Arrays;

// 面向对象的数组 开发
public class MyArrayUtil {

    // 用于存储数据的数组[目标数组]
    private int[] elements;

    public MyArrayUtil() {
        elements = new int[0];
    }

    // 1、获取数组长度的方法
    public int size() {
        return elements.length;
    }

    // 2、往数组的末尾添加一个元素
    public void addElement(int element) {
        // 创建一个新的数组
        int[] newArr = new int[elements.length + 1];
        // 遍历旧数组，将旧数组中的数据添加到新数组中
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        // 将添加的元素添加到新数组中
        newArr[elements.length] = element;

        // 使用新数组替换旧数组
        elements = newArr;
    }

    // 3、显示数组中的元素到控制台
    public void showElement() {
        System.out.println(Arrays.toString(elements));
    }

    // 4、删除数组中指定索引位置的元素
    public void deleteElement(int index) {
        // 判断数组索引越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("下标索引越界。。。。");
        }

        // 创建一个新数组
        int[] newArr = new int[elements.length - 1];

        // 复制原有数组中的元素到新数组中
        for (int i = 0; i < newArr.length; i++) {
            //
            if (index < i) {
                newArr[i] = elements[i];
            } else {
                newArr[i] = elements[i + 1];
            }
        }

        // 将新数组替换旧数组
        elements = newArr;
    }

    // 5、取出指定位置的元素
    public int getElement(int index) {
        // 判断数组索引是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("下标索引越界。。。。");
        }
        return elements[index];
    }

    // 6、插入元素到指定位置
    public void insertElement(int index, int element) {
        // 创建一个新数组
        int[] newArr = new int[elements.length + 1];
        for (int i = 0; i < elements.length; i++) {
            if (i < index) {
                newArr[i] = elements[i];
            } else {
                newArr[i + 1] = elements[i];
            }
        }
        newArr[index] = element;
        // 新数组替换旧数组
        elements = newArr;
    }

    // 7、替换指定位置的元素
    public void setElements(int index, int element) {
        // 判断数组索引越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("下标索引越界。。。。");
        }
        elements[index] = element;
    }

    // 数组中的线性查找
    public int ArrayLinearSearch(int target) {
        if (elements != null && elements.length > 0) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == target) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 数组中的二分查找
    public int ArrayBinarySearch(int target) {
        if (elements != null && elements.length > 0) {
            Arrays.sort(elements);
            System.out.println("排序后的数组是：" + Arrays.toString(elements));
            int left = 0;
            int right = elements.length - 1;
            int mid;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (elements[mid] == target) {
                    return mid;
                } else if (elements[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

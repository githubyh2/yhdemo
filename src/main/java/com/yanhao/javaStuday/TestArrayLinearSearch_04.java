package com.yanhao.javaStuday;

/**
 * 数组 中 的线性查找：
 * 实现： 数组中元素查找 ：找到返回对应的元素 下标索引，找不到，返回 -1
 */
public class TestArrayLinearSearch_04 {
    public static void main(String[] args) {
        // 目标数组
        int[] arr = new int[]{2, 4, 56, 7, 86, 9, 10};

        // 查找的元素
        int target = 11;

        // 索引
        int index = -1;

        // 遍历数组 ,如果查找到元素，返回对应 元素 的 下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                index = i;
                break;
            }
        }

        System.out.println("线性查找到的元素索引index: " + index);

    }
}

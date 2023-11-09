package com.yanhao.javaStuday;

import java.util.Arrays;

public class TestOpArray_02 {
    public static void main(String[] args) {
        // 目标数组
        int[] arr = new int[]{9, 8, 7, 6, 5, 4};

        System.out.println("元数组长度：" + arr.length + ",删数据 之前 的原数组：" + Arrays.toString(arr));
        // 需要删除数组元素的下标
        int dst = 5;

        // 创建一个新数组,长度为原数组长度 - 1
        int[] newArr = new int[arr.length - 1];

        // 将元素组中的数据放入新数组中
        for (int i = 0; i < newArr.length; i++) {
            // 如果是下标 之前的数
            if (i < dst) {
                newArr[i] = arr[i];
            } else {
                newArr[i] = arr[i + 1];
            }
        }

        // 将新数组替换为旧数组
        arr = newArr;
        System.out.println("删数据 之后 的原数组：" + Arrays.toString(arr));

    }
}

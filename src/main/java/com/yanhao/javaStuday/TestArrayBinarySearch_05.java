package com.yanhao.javaStuday;

import java.util.Arrays;

/**
 * 数组中的二分查找：
 * 前提： 数组是有序的数组
 */
public class TestArrayBinarySearch_05 {
    public static void main(String[] args) {
        // 目标数组
        int[] arr = new int[]{1, 3, 5, 7, 9, 500, 1000, 11, 23, 34, 45, 56};

        System.out.println("数组的长度是：" + arr.length);
        System.out.println("数组中的元素是：" + Arrays.toString(arr));

        int target = 0;

        int index = search(arr, target);
        System.out.println("查询目标元素所在的下标索引为：" + index);
    }

    public static int search(int[] arr, int target) {
        if (arr != null && arr.length > 0) {
            // 进行数组中元素排序
            Arrays.sort(arr);
            System.out.println("排序后的数组元素是：" + Arrays.toString(arr));
            int left = 0;
            int right = arr.length - 1;
            int mid;
            while (left <= right) {
                /**
                 * 细节：mid 按照如下的操作，避免超出int的长度
                 * int mid = left + (right - left) / 2;
                 */
                mid = left + (right - left) / 2;
                if (arr[mid] < target) {
                    left = mid + 1;
                } else if (arr[mid] > target) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }
}

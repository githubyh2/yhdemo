package com.yanhao.javaStuday;

import java.util.Arrays;

/**
 * 数组元素的添加
 */
public class TestOpArray_01 {
    public static void main(String[] args) {
/*
        int count = 0;
        int n[] = {1, 3, 2, 3, 5, 1, 1, 1};
        for (int i : n) {
            if (i == 3) count++;
        }
        System.out.println(count);
        */

/*
        int count = 0;
        while (true) {
            System.out.println("请输入一个数：");
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();
            if (number == 3) {
                count++;
            }
            System.out.println(count);
        }
*/

        // 解决数组长度不可变的问题
        int[] arr = new int[]{9, 8, 7};

        // 查看数组中的元素
        System.out.println("使用 Arrays.toString(arr) 查看数组中的元素：" + Arrays.toString(arr));

        // 需要加入的元素
        int dst = 6;

        // 创建一个新的数组，数组长度是原数组长度+1
        int[] newArr = new int[arr.length + 1];

        System.out.println("新数组的长度是：" + newArr.length);
        // 拷贝原数组中的数据到新数组中
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        // 将需要添加的数据放到新数组最后一位

        newArr[arr.length] = dst;
        // 将旧数组的指向，指向到新数组
        arr = newArr;
        System.out.println("新增数据后的 原数组 中数据显示：" + Arrays.toString(arr));

    }

}

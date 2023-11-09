package com.yanhao.javaStuday;

/**
 * 类似数学中的等差数列计算实现
 */
public class SumCalculator {
    public static void main(String[] args) {

        /**
         int[] numbers = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100}; // 输入的数字序列
         int sum = 0; // 初始累加和为0

         for (int i = 0; i < numbers.length; i++) {
         sum += numbers[i]; // 累加每个数字到sum变量中
         }

         System.out.println("累加和为：" + sum);
         */

        // 初始累加和为0
        int sum = 0;

        for (int i = 1; i <= 365; i++) {

            sum += (5 * i);
            if (i == 365) {
                System.out.println("累加和为：" + sum);
            }
        }
    }

}

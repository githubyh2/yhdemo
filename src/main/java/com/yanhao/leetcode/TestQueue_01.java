package com.yanhao.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanhao
 * @data 2023/10/23
 */
public class TestQueue_01 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        //to implement Queue
        //Adding In Queue  using `add()` method
        queue.add("Bill Gates");  //enqueue
        queue.add("Mark  Mark Zuckerberg"); // enqueue
        queue.add("Elon Musk"); //enqueue
        queue.add("Jeff Bezos");
        // offer() 方法是 add() 的替代方法
        queue.offer("Donald Trump");   // alter natice enqueue
        System.out.println("Queue : " + queue); //queue print
        System.out.println("Queue size : " + queue.size());

        String name = "张三";
        String city = "深圳";
        int age = 18;
        String sex = "男";
        String format = String.format("%s用户来自%s，年龄 %d，性别 %s.....", name, city, age, sex);

        System.out.println("======" + format);
    }
}

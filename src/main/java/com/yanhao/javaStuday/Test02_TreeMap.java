package com.yanhao.javaStuday;

import java.util.Map;
import java.util.TreeMap;

/*
    1、HashMap无序，TreeMap有序。
    2、HashMap覆盖了equals（）方法和hashcode（）方法，这使得HashMap中两个相等的映射返回相同的哈希值；
        TreeMap则是实现了SortedMap接口，使其有序。
    3、HashMap的工作效率更高，而TreeMap则是基于树的增删查改。更推荐使用HashMap。
    4、HashMap基于数组+链表+红黑树（jdk1.8之后）实现，TreeMap是基于红黑树实现。
    5、两者都不是线性安全的。
 */
public class Test02_TreeMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();

        map.put("blili", 1234);
        map.put("alili", 2345);
        map.put("blili", 5467);
        map.put("clili", 5678);
        map.put("dlili", 2345);
        System.out.println(map.size());
        System.out.println(map);
    }
}

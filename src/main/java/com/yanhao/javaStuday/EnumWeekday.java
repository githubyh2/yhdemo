package com.yanhao.javaStuday;

public class EnumWeekday {
    public static void main(String[] args) {
        for (Weekday weekday : Weekday.values()) {
//            System.out.println(weekday.ordinal());
            System.out.println(weekday.name());
        }
    }
}

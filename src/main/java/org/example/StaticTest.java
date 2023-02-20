package org.example;

public class StaticTest {

    //属性
    int id;
    static int sid;

    public void a() {
        System.out.println(id);
        System.out.println(sid);
        System.out.println("------a");
    }

    //1.static和public都是修饰符，并列的没有先后顺序，先写谁后写谁都行
    static public void b() {
        //System.out.println(this.id);//4.在静态方法中不能使用this关键字
        //a();//3.在静态方法中不能访问非静态的方法
        //System.out.println(id);//2.在静态方法中不能访问非静态的属性
        System.out.println(sid);
        System.out.println("------b");
    }

    //这是main方法，程序的入口
    public static void main(String[] args) {
        //创建一个类的具体对象
        StaticTest t1 = new StaticTest();
        t1.id = 10;
        t1.sid = 10;

        StaticTest t2 = new StaticTest();
        t2.id = 20;
        t2.sid = 20;

        StaticTest t3 = new StaticTest();
        t3.id = 30;
        t3.sid = 30;

        //读取属性的值：
        System.out.println("t1.id-->" + t1.id);
        System.out.println("t2.id-->" + t2.id);
        System.out.println("t3.id-->" + t3.id);

        System.out.println(t1.sid);
        System.out.println(t2.sid);
        System.out.println(t3.sid);

        System.out.println("=======================");


        //5.非静态的方法可以用对象名.方法名去调用
        StaticTest d = new StaticTest();

        d.a();
        //6.静态的方法可以用   对象名.方法名去调用  也可以 用  类名.方法名 （推荐）
        StaticTest.b();
        d.b();


    }


}

package com.yanhao.juc23;

/**
 * java 面向接口编程---》函数式接口:函数式接口中有且只有一个方法
 * <p>
 * lambda Expression表达式 ：
 * 1、拷贝小括号 ， 写死右箭头 ， 落地大括号
 * 2、语法现象：@FunctionalInterface
 * 3、default 方法可以有多个
 * 4、static 方法可以有多个
 * <p>
 * 函数式编程
 * int age = 23;
 * <p>
 * y = kx + 1
 * <p>
 * f(x) = kx + 1
 */
//接口中只有方法的声明,接口是可以进行new的，接口是特殊的类；
//接口中可以定义多个方法
@FunctionalInterface
interface Foo {
//    public void sayHello();

    public int add(int x, int y);

    //完成的定义实现，default 方法可以有多个
    public default int mul(int a, int b) {
        return a * b;
    }

    ;

    public default int mul2(int a, int b) {
        return a * b;
    }

    ;

    public static int div(int a, int b) {
        return a / b;
    }

    ;

    public static int div2(int a, int b) {
        return a / b;
    }

    ;

}

public class LambdaExpressionDemo02 {
    public static void main(String[] args) {
        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("*** 2023");
            }
        };

        foo.sayHello();*/

        //lambda表达式写法，解决了匿名内部类代码冗余的写法
        /*Foo foo = () -> {
            System.out.println("*** 2023 *** lambda Expression");
        };
        foo.sayHello();*/

        //
        Foo foo = (int x, int y) -> {
            System.out.println("*** come in add method *** lambda Expression");
            return x + y;
        };
        System.out.println(foo.add(3, 5));

        System.out.println(foo.mul(3, 5));

        System.out.println(Foo.div(10, 2));

    }
}

package com.yanhao.fileStuday;

/*
File类的深入了解,操作系统以文件为单位管理磁盘中的数据
java中最典型的特点就是面向对象，Java程序擅长的就是操作对象，盘符上的文件/目录，将它的各种信息进行封装，封装为一个对象，这个对象就属于File类。

盘符上的文件，封装为对象，对象属于File类的对象，通过这个对象获取文件的各种信息，还可以对文件进行创建 ，删除
 */

import java.io.*;

public class Test01_File {
    //这是一个main方法，是程序的入口：
    public static void main(String[] args) throws IOException {

        //将文件封装成一个File对象；
        File f = new File("D:\\FileTestStudy\\test.txt");
        System.out.println("f:" + f);


        File f2 = new File("d:/test.txt");

        //File.separator属性帮我们获取当前操作系统的路径拼接符号   separator 分离器[ˈsepəreɪtər]
        //在windows，dos下，系统默认用“\”作为路径分隔符 ，在unix，url中，使用“/”作为路径分隔符。
        //其实 File.separator 的作用相当于 ' \  '
        //在 windows 中 文件文件分隔符 用 ' \ ' 或者 ' / ' 都可以
        //但是在 Linux 中，是不识别 ' \ '  的，而 File.separator 是系统默认的文件分隔符号，在 UNIX 系统上，此字段的值为 ' / '
        //在 Microsoft Windows 系统上，它为 ' \ ' 屏蔽了这些系统的区别。
        //File.separator 保证了在任何系统下不会出错。


        File f3 = new File("d:" + File.separator + "FileTestStudy" + File.separator + "test.txt");
        System.out.println("f3:" + f3);

        //常用方法
        System.out.println("文件是否可读：" + f.canRead());
        System.out.println("文件是否可写：" + f.canWrite());
        System.out.println("文件的名字：" + f.getName());
        System.out.println("上级目录：" + f.getParent());
        System.out.println("是否是一个目录：" + f.isDirectory());
        System.out.println("是否是一个文件：" + f.isFile());
        System.out.println("是否隐藏：" + f.isHidden());
        System.out.println("文件的大小：" + f.length());
        System.out.println("是否存在：" + f.exists());


         /*
         if(f.exists()){//如果文件存在，将文件删除操作
            f.delete();
        }else{//如果不存在，就创建这个文件
            f.createNewFile();
        }
        */

        //比较两个对象的地址
        System.out.println(f == f3);

        //比较两个对象对应的文件的路径
        System.out.println(f.equals(f3));

        //跟路径相关的
        System.out.println("绝对路径：" + f.getAbsolutePath());
        System.out.println("相对路径：" + f.getPath());
        System.out.println("toString：" + f.toString());

        System.out.println("----------------------");
        File f5 = new File("demo.txt");
        if (!f5.exists()) {
            f5.createNewFile();
        }
        //绝对路径指的就是：真实的一个精准的，完整的路径
        System.out.println("绝对路径：" + f5.getAbsolutePath());
        //相对路径：有一个参照物，相对这个参照物的路径。
        //在main方法中，相对位置指的就是：D:\software\ideaProject\yhdemo
        //在junit的测试方法中，相对路径指的就是模块位置
        System.out.println("相对路径：" + f5.getPath());
        //toString的效果永远是  相对路径
        System.out.println("toString:" + f5.toString());

        File f6 = new File("a/b/c/demo.txt");
        if (!f5.exists()) {
            f5.createNewFile();
        }
        System.out.println("绝对路径：" + f6.getAbsolutePath());
        System.out.println("相对路径：" + f6.getPath());


        //将目录封装为File类的对象：
        File f7 = new File("D:\\software\\ideaProject");
        //查看：
        String[] list = f7.list();//文件夹下目录/文件对应的名字的数组
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("=========================");

        File[] files = f7.listFiles();
        for (File file : files) {
            System.out.println(file.getName() + "," + file.getAbsolutePath());
        }

//        String filepath = "D:\\FileTestStudy\\test.txt";

        /**
         * 读取文件中的内容：
         * 字节流>字符流>字符串
         * 使用File,FileInputStream，InputStreamReader，BufferedReader
         * 使用FileInputStream，InputStreamReader，BufferedReader
         * @throws IOException
         */

        //FileInputStream（字节流） 实现了InputStream接口，用来读取文件中的字节流，参数是文件或者文件路径+文件名称
        FileInputStream fileInputStream = new FileInputStream(f);

        System.out.println("返回读到的字节本身："+fileInputStream.read());

        //将 fileInputStream（字节流） 流作为参数，转为InputStreamReader（字符流）
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

        //将 字符流（参数）转为字符串流，带缓冲的流读取，默认缓冲区8k
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        fileInputStream.close();
        bufferedReader.close();

        

        

    }
}

package com.yanhao.wc;

import java.util.Scanner;

public class TableSqlGenerator {

    public static void main(String[] args) {
        //创建Scanner 对象，用于读取标准输入
        Scanner scanner = new Scanner(System.in);

        //读取输入的SQL 语句
        StringBuilder sqlBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().equals(";")) {//当遇到分号时停止读取
                break;
            }
            sqlBuilder.append(line).append("\n ");
        }

        //生成表格形式的SQL 语句
        String sql = sqlBuilder.toString();
        String tableSQL = generateTableSQL(sql);

        //输出生成的表格形式的SQL 语句
        System.out.println(tableSQL);

        //关闭Scanner 对象
        scanner.close();
    }

    public static String generateTableSQL(String sql) {
        //去掉换行符和多余的空格
        sql = sql.replaceAll("\n ", "").replaceAll("\\s +", "").trim();

        System.out.println("-----" + sql);
        //将CREATE 语句中的字段列表改为逗号分隔的表格形式
        int startIndex = sql.indexOf("(");
        int endIndex = sql.lastIndexOf(")");
        String columns = sql.substring(startIndex + 1, endIndex).trim();
        System.out.println(">>>>>>" + columns);

        String[] columnArray = columns.split(",");
        System.out.println("有 " + columnArray.length + " 列");


        StringBuilder tableColumns = new StringBuilder();
        for (int i = 0; i < columnArray.length; i++) {
            String column = columnArray[i].trim();


            if (column.contains("PRIMARY") || column.contains("UNIQUE")) {
                return null;
            }
            System.out.println("?????" + column);


            int columnOrd = i + 1;
            System.out.println("===列顺序===：" + columnOrd);
//            if (column.startsWith("`")) {
//                column = column.substring(0, column.length());

            String[] s = column.split(" ");
            String ss = s[0];
            System.out.println("===名字===：" + ss);

            String bb = s[1];
            System.out.println("===类型==：" + bb);
//            }
//            tableColumns.append("\n |").append(column);

//            tableColumns.append(",");
            if (column.contains("COMMENT")) {
//                int start = column.indexOf("'");
                int start = column.lastIndexOf("COMMENT");
//                String comment = column.substring(start + 7).trim().replaceAll("'", "");
                String comment = column.substring(start + 7);
                System.out.println("comment:" + comment);
                System.out.println("\n");
            }
        }
        //生成表格形式的SQL 语句
//        String tableName = sql.substring(sql.indexOf("TABLE ") + 5, sql.indexOf("(")).trim();
//        String tableSQL = "CREATE TABLE " + tableName + "(" + tableColumns.toString() + "\n );";
//        return tableSQL;
        return null;
    }
}


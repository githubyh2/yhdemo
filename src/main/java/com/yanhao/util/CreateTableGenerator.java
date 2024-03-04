package com.yanhao.util;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanhao
 * @data 2024/3/1
 */
public class CreateTableGenerator {
    public static void main(String[] args) {
        /*String dbName = "yhTest";
        String tableName = "employee";
        String[] columnNames = {"id", "name", "age"};
        String[] columnTypes = {"INT", "VARCHAR(50)", "INT"};

        StringBuilder createTableStatement = new StringBuilder();
        createTableStatement.append("CREATE TABLE ").append(dbName+".").append(tableName).append(" (");

        for (int i = 0; i < columnNames.length; i++) {
            createTableStatement.append("\n    ").append(columnNames[i]).append(" ").append(columnTypes[i]);
            if (i < columnNames.length - 1) {
                createTableStatement.append(",");
            }
        }

        createTableStatement.append("\n);");

        System.out.println(createTableStatement);*/

        String tableName = "your_table_name";
     /*   List<Column> columns = new ArrayList<>();
        columns.add(new Column("id", "INT", ""));
        columns.add(new Column("name", "VARCHAR(50)", "Name of the person"));
        columns.add(new Column("age", "INT", "Age of the person"));
        String ddl = generateCreateTableDDL(tableName, columns);
        System.out.println(ddl);*/

        List<Triplet> tps = new ArrayList<>();
//        Triplet<String, String, String> triplet = new Triplet<>("id", "INT", "");
//        new Triplet<>("name", "VARCHAR(50)", "Name of the person");
        tps.add(new Triplet<>("id", "INT", ""));
        tps.add(new Triplet<>("name", "VARCHAR(50)", "Name of the person"));
        tps.add(new Triplet<>("age", "Int"," "));

//        System.out.println(generateCreateTableDDL2(tableName, tps));
        System.out.println(hutoolDDl(tps));

    }

    public static String generateCreateTableDDL2(String tableName, List<Triplet> tps) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(tableName).append(" (\n");

        for (int i = 0; i < tps.size(); i++) {
            Triplet tp = tps.get(i);
            sb.append("    ").append(tp.first).append(" ").append(tp.second);
            if (tp.third.toString() != null && !tp.third.toString().isEmpty()) {
                sb.append(" COMMENT '").append(tp.third).append("'");
            }
            if (i < tps.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append(");");
        return sb.toString();
    }


    public static String generateCreateTableDDL(String tableName, List<Column> columns) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(tableName).append(" (\n");

        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            sb.append("    ").append(column.getName()).append(" ").append(column.getType());

            if (column.getComment() != null && !column.getComment().isEmpty()) {
                sb.append(" COMMENT '").append(column.getComment()).append("'");
            }
            if (i < columns.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append(");");
        return sb.toString();
    }

    static class Column {
        private String name;
        private String type;
        private String comment;

        public Column(String name, String type, String comment) {
            this.name = name;
            this.type = type;
            this.comment = comment;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getComment() {
            return comment;
        }
    }

    static class Triplet<A, B, C> {
        private final A first;
        private final B second;
        private final C third;

        public Triplet(A first, B second, C third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

        public C getThird() {
            return third;
        }
    }




    public static String hutoolDDl(List<Triplet> tps){

        String cols = CollUtil.join(tps, ",\n", t -> " " + t.first + " " + t.second);
        return String.format("CREATE TABLE (\n%s\n);", cols);
    }

}

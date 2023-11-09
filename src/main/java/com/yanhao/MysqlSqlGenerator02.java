package com.yanhao;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.db2.parser.DB2StatementParser;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;

import java.util.*;

public class MysqlSqlGenerator02 {

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
//        String tableSQL = generateTableSQL(sql);

        String tableSQL = generateDB2TableSQL(sql);
//        String tableSQL = generateJdbcSQL(sql);
//        String tableSQL = generateJdbcSQLbyRegular(sql);

        //输出生成的表格形式的SQL 语句
        System.out.println(tableSQL);

        //关闭Scanner 对象
        scanner.close();
    }

    public static String generateTableSQL(String sql) {
        //去掉换行符和多余的空格
        sql = sql.replaceAll("\n ", "").replaceAll("\\s +", "").trim();

        System.out.println("-----" + sql);

        String s1 = SQLUtils.formatMySql(sql);

        System.out.println("格式化：" + s1);

        //将CREATE 语句中的字段列表改为逗号分隔的表格形式
        int startIndex = s1.indexOf("(");
        int endIndex = s1.lastIndexOf(")");
        String columns = s1.substring(startIndex + 1, endIndex).trim();
        System.out.println(">>>>>>" + columns);

        String[] columnArray = columns.split("\n");
        System.out.println("有 " + columnArray.length + " 列");

        //
//        StringBuilder tableColumn = new StringBuilder();

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
                String comment = column.substring(start + 7, column.length() - 1).replaceAll("'", "").trim();
//                String comment = column.substring(start + 7);
                System.out.println("comment:" + comment);
                System.out.println("\n");
            }
        }

        return s1;
    }


    public static String generateDB2TableSQL(String sql) {
        //去掉换行符和多余的空格
        sql = sql.replaceAll("\n ", "").replaceAll("\\s +", "").trim();

        System.out.println("传入的sql是：" + sql);

        String[] formatSql = sql.split(";");

        System.out.println("formatsql数组长度：" + formatSql.length);

        String tableName = null;
        Map<String, String> map = new HashMap<String, String>();
        for (int j = 0; j < formatSql.length; j++) {

            System.out.println("您好：" + j);
            String db2comment = formatSql[j];
            System.out.println("DB2 建表语句中 COMMENT信息:" + db2comment);

            if (db2comment.contains("CREATE")) {
                String createSql = SQLUtils.formatMySql(db2comment);

                System.out.println("DB2 建表语句中 字段和类型：\n" + createSql);

                int startIndex = createSql.indexOf("(");
                int endIndex = createSql.lastIndexOf(")");

                // 获取表名
                int tblIndex = createSql.indexOf("TABLE");
                tableName = createSql.substring(tblIndex + 5, startIndex).trim();

                System.out.println("===表名==：" + tableName);

                String columns = createSql.substring(startIndex + 1, endIndex).trim();
                System.out.println(">>>>>>" + columns);

                String[] columnArray = columns.split("\n");
                System.out.println("有 " + columnArray.length + " 列");

                for (int i = 0; i < columnArray.length; i++) {
                    String column = columnArray[i].trim();

                    if (column.contains("PRIMARY") || column.contains("UNIQUE")) {
                        break;
                    }
                    System.out.println("?????" + column);

                    int columnOrd = i + 1;
                    System.out.println("===列顺序===：" + columnOrd);

                    String[] s = column.split(" ");
                    String columnName = s[0];
                    System.out.println("===名字===：" + columnName);

                    String columnType = s[1];
                    System.out.println("===类型==：" + columnType);

                    map.put(columnName, columnType);

                }

            }
            if (db2comment.contains("COMMENT")) {

                // 获取表的 COMMENT
                if (db2comment.contains("TABLE") && db2comment.contains(tableName)) {
                    System.out.println("db2comment 备注 11111111:" + db2comment);
                    int index = db2comment.lastIndexOf("IS");
                    String trim = db2comment.substring(index + 3, db2comment.length() - 1).replaceAll("'", "").trim();
                    System.out.println("table name:" + tableName + ",table desc:" + trim + "\n");

                }
                if (db2comment.contains("COLUMN")) {
                    Set<Map.Entry<String, String>> entries = map.entrySet();
                    for (Map.Entry<String, String> entry : entries) {
                        String key = entry.getKey();

                        if (db2comment.contains(tableName + "." + key)) {
                            int index = db2comment.lastIndexOf("IS");
                            String trim = db2comment.substring(index + 3, db2comment.length() - 1).replaceAll("'", "").trim();
                            System.out.println("colunm name:" + key + ",colunm desc:" + trim + "\n");
                        }
                    }

                }
            }

        }
/*
        //将CREATE 语句中的字段列表改为逗号分隔的表格形式

        String s1 = formatsql[0];

        String createsql = SQLUtils.formatMySql(s1);

        System.out.println("DB2 建表语句中 字段和类型：\n" + createsql);

        int startIndex = createsql.indexOf("(");
        int endIndex = createsql.lastIndexOf(")");
        String columns = createsql.substring(startIndex + 1, endIndex).trim();
        System.out.println(">>>>>>" + columns);

        String[] columnArray = columns.split("\n");
        System.out.println("有 " + columnArray.length + " 列");

        //
//        StringBuilder tableColumn = new StringBuilder();

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
        }
*/

        return "-----";
    }

    public static String generateJdbcSQL(String sql) {

        DbType db2Type = DbType.db2;
        String formatSql = SQLUtils.format(sql, db2Type);

        System.out.println("格式化输出：\n" + formatSql);

        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, db2Type);
        System.out.println("size is:" + stmtList.size());

        DB2StatementParser statementParser = new DB2StatementParser(formatSql);

        SQLStatement create = statementParser.parseCreate();

        System.out.println("解析：\n" + create);
        List<SQLObject> children = create.getChildren();
        for (SQLObject child : children) {
            System.out.println("---" + child);
        }
        SchemaStatVisitor visitor = SQLUtils.createSchemaStatVisitor(db2Type);
        for (SQLStatement sqlStatement : stmtList) {
            sqlStatement.accept(visitor);
        }

        Map<TableStat.Name, TableStat> tables = visitor.getTables();
        for (Map.Entry<TableStat.Name, TableStat> nameTableStatEntry : tables.entrySet()) {
            TableStat.Name key = nameTableStatEntry.getKey();
            System.out.println("table name:" + key);
        }

        Collection<TableStat.Column> columns = visitor.getColumns();
        for (TableStat.Column column : columns) {
            System.out.println("column name:" + column);
        }

//        SQLStatement sqlStatement = statementParser.parseStatement();
//        System.out.println("$$$$$$" + sqlStatement);


        return "";
    }

    public static String generateJdbcSQLbyRegular(String sql) {
        // 去掉换行符和多余的空格
        sql = sql.replaceAll("\n ", "")
                .replaceAll("\\s +", "")
                .replaceAll("`", "")
                .trim();

        System.out.println("传入的sql是：" + sql);

        // 进行sql 切分
        String[] formatSql = sql.split(";");

        String columnsSql = formatSql[0];

        String createSql = SQLUtils.formatMySql(columnsSql);

        System.out.println("格式化后的sql是：" + createSql);

        //将CREATE 语句中的字段列表改为逗号分隔的表格形式
        int startIndex = createSql.indexOf("(");
        int endIndex = createSql.lastIndexOf(")");
        String columns = createSql.substring(startIndex + 1, endIndex).trim();
        System.out.println(">>>>>>" + columns);


        return "使用 正则 匹配 解析sql：generateJdbcSQLbyRegular";
    }
}


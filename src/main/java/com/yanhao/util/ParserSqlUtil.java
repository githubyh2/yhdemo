package com.yanhao.util;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import java.util.List;

/**
 * @author yanhao
 * @data 2024/2/29
 */
public class ParserSqlUtil {
    public static void main(String[] args) {
        String sql = "CREATE TABLE `columns_v2` (\n" +
                "  `CD_ID` bigint NOT NULL COMMENT '字段信息ID',\n" +
                "  `COMMENT` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '字段注释',\n" +
                "  `COLUMN_NAME` varchar(767) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字段名',\n" +
                "  `TYPE_NAME` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '字段类型',\n" +
                "  `INTEGER_IDX` int NOT NULL COMMENT '字段顺序'\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        try {
            Statement statement = CCJSqlParserUtil.parse(sql);

            if (statement instanceof CreateTable) {
                CreateTable createTable = (CreateTable) statement;
                System.out.println("Table Name: " + createTable.getTable().getName());


                List<ColumnDefinition> columnDefinitions = createTable.getColumnDefinitions();
                System.out.println("Columns: " + columnDefinitions);

                ColumnDefinition columnDefinition = columnDefinitions.get(0);
                System.out.println(columnDefinition);


            } else {
                System.out.println("Not a CREATE TABLE statement");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

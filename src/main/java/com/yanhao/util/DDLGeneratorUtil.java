package com.yanhao.util;

import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import java.util.Arrays;

/**
 * @author yanhao
 * @data 2024/2/29
 */
public class DDLGeneratorUtil {
    public static void main(String[] args) {
        CreateTable createTable = new CreateTable();
        createTable.setTable(new net.sf.jsqlparser.schema.Table("my_table"));

        ColumnDefinition columnDefinition1 = new ColumnDefinition();
        columnDefinition1.setColumnName("id");
        columnDefinition1.setColDataType(new ColDataType("INT"));

        ColumnDefinition columnDefinition2 = new ColumnDefinition();
        columnDefinition2.setColumnName("name");
        columnDefinition2.setColDataType(new ColDataType("VARCHAR(255)"));

        ColumnDefinition columnDefinition3 = new ColumnDefinition();
        columnDefinition3.setColumnName("CD_ID");
        columnDefinition3.setColDataType(new ColDataType("bigint"));
        columnDefinition3.setColumnSpecs(Arrays.asList("NOT NULL", "COMMENT '字段信息ID'"));

        createTable.setColumnDefinitions(Arrays.asList(columnDefinition1, columnDefinition2, columnDefinition3));

        String ddlStatement = createTable.toString();
        System.out.println(ddlStatement);
    }
}

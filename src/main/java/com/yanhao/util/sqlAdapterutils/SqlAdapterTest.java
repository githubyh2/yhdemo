package com.yanhao.util.sqlAdapterutils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanhao
 * @data 2024/3/4
 */
public class SqlAdapterTest {
    public static void main(String[] args) {
        SqlAdapter mySqlAdapter = new MySqlAdapter();
        SqlAdapter oracleAdapter = new OracleAdapter();

        Map<String, String> columns = new HashMap<>();
        columns.put("id", "INT");
        columns.put("name", "VARCHAR");
        columns.put("age", "INT");

        String mySqlDdl = mySqlAdapter.generateCreateTableSql("my_table", columns);
        String oracleDdl = oracleAdapter.generateCreateTableSql("my_table", columns);

        System.out.println("MySQL DDL:");
        System.out.println(mySqlDdl);
        System.out.println();
        System.out.println("Oracle DDL:");
        System.out.println(oracleDdl);


        System.out.println(mySqlAdapter.javaToSQLType(String.class));
    }
}

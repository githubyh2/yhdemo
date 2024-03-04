package com.yanhao.util.sqlAdapterutils;

import java.util.Map;

/**
 * sql适配器
 * @author yanhao
 * @data 2024/3/4
 */
public interface SqlAdapter {
    String generateCreateTableSql(String tableName, Map<String, String> columns);

    String javaToSQLType(Class<?> javaType);
}

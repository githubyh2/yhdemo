package com.yanhao.util.sqlAdapterutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yanhao
 * @data 2024/3/4
 */
public class OracleAdapter implements SqlAdapter {
    private static final Map<String, String> dataTypes = new HashMap<>();

    static {
        dataTypes.put("INT", "NUMBER");
        dataTypes.put("VARCHAR", "VARCHAR2(255)");
        // 添加其他数据类型映射
    }

    @Override
    public String generateCreateTableSql(String tableName, Map<String, String> columns) {
        StringBuilder ddl = new StringBuilder();
        ddl.append("CREATE TABLE ").append(tableName).append(" (\n");

        Stream<String> stringStream = columns.entrySet().stream().map(entry -> entry.getKey() + " " + dataTypes.get(entry.getValue()));

        List<String> columnDefinitions = stringStream.collect(Collectors.toList());

        ddl.append(String.join(",\n", columnDefinitions));
        ddl.append("\n)");
        return ddl.toString();
    }

    @Override
    public String javaToSQLType(Class<?> javaType) {
        if (javaType == Integer.class) {
            return "NUMBER";
        } else if (javaType == String.class) {
            return "VARCHAR2(255)";
        } else if (javaType == Double.class) {
            return "FLOAT";
        }
        // 可以根据需要添加更多数据类型的转换规则
        return "UNKNOWN";
    }
}

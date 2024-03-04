package com.yanhao.util.sqlAdapterutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yanhao
 * @data 2024/3/4
 */
public class MySqlAdapter implements SqlAdapter {
    private static final Map<String, String> dataTypes = new HashMap<>();

    static {
        dataTypes.put("INT", "INT");
        dataTypes.put("VARCHAR", "VARCHAR(255)");
        // 添加其他数据类型映射
    }

    @Override
    public String generateCreateTableSql(String tableName, Map<String, String> columns) {
        StringBuilder ddl = new StringBuilder();
        ddl.append("CREATE TABLE ").append(tableName).append(" (\n");

        List<String> columnDefinitions = columns.entrySet().stream()
                .map(entry -> entry.getKey() + " " + dataTypes.get(entry.getValue())).collect(Collectors.toList());

        ddl.append(String.join(",\n", columnDefinitions));
        ddl.append("\n);");
        return ddl.toString();
    }

    @Override
    public String javaToSQLType(Class<?> javaType) {
        if (javaType == Integer.class) {
            return "INT";
        } else if (javaType == String.class) {
            return "VARCHAR(255)";
        } else if (javaType == Double.class) {
            return "DOUBLE";
        }
        // 可以根据需要添加更多数据类型的转换规则
        return "UNKNOWN";

    }
}

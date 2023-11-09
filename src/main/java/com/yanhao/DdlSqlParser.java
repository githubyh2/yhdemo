package com.yanhao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DdlSqlParser {
    private static final Pattern TABLE_PATTERN = Pattern.compile("(?i)\\b(CREATE\\s+TABLE)\\b\\s+(\\w+)\\s*(.*)");
    private static final Pattern COLUMN_PATTERN = Pattern.compile("(?i)\\b(.*?)\b\\s+(.*?)\b\\s+(.*?);");

    public static void main(String[] args) {
        String ddl = "CREATE TABLE employees ("
                + "id INT(11) NOT NULL AUTO_INCREMENT, "
                + "first_name VARCHAR(14) NOT NULL, "
                + "last_name VARCHAR(16) NOT NULL, "
                + "birth_date DATE NOT NULL, "
                + "gender ENUM('M','F') NOT NULL, "
                + "hire_date DATE NOT NULL, "
                + "PRIMARY KEY (id));";

        Matcher tableMatcher = TABLE_PATTERN.matcher(ddl);
        if (tableMatcher.find()) {
            String tableName = tableMatcher.group(2);
            System.out.println("Table Name: " + tableName);

            Matcher columnMatcher = COLUMN_PATTERN.matcher(tableMatcher.group(3));
            while (columnMatcher.find()) {
                String columnName = columnMatcher.group(2);
                String columnType = columnMatcher.group(1);
                System.out.println("Column Name: " + columnName + ", Column Type: " + columnType);
            }
        }

    }
}

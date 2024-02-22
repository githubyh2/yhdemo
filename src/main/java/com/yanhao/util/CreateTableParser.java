package com.yanhao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanhao
 * @data 2024/02/05
 * unicode转中文字符串
 * 测试git使用
 */
public class CreateTableParser {
    public static void main(String[] args) {
        String createTableStatement = "CREATE TABLE hive3.ods.s002_d_chnl_da (                                       \n" +
                "    chnl_name varchar COMMENT U&'A\\FF1A\\6E202\\FF1A\\9053\\540D\\FF08\\79F0\\FF09'                        \n" +
                " )                                                                             \n" +
                " COMMENT U&'\\8BB0\\5F55\\6E20\\9053\\4FE1\\606F'                                    \n" +
                " WITH (                                                                        \n" +
                "    format = 'TEXTFILE',                                                       \n" +
                "    textfile_field_separator = U&'\\001F'                                       \n" +
                " )   ";

        // 正确调用
        String s = convertCommentToChinese(createTableStatement);
        // 输出语句
        System.out.println("===" + s);
    }

    public static String convertCommentToChinese(String input) {
        // 使用正则表达式匹配COMMENT U&'...'部分
        String pattern = "COMMENT U&'((?:\\\\[0-9A-Fa-f]{4}|[^\\\\'])+)'";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        // 将匹配到的COMMENT内容中的Unicode转义字符替换为中文字符
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String unicodeSequence = matcher.group(1);
            String chineseComment = convertUnicodeSequenceToChinese(unicodeSequence);
            matcher.appendReplacement(result, "COMMENT '" + chineseComment + "'");
        }

        matcher.appendTail(result);

        return result.toString().replace("U&", "");
//        return result.toString();
    }

    private static String convertFieldSeparator(String input) {
        // 使用正则表达式匹配U&'\001F'和U&'\0001'
        String separatorPattern = "U&'\\\\([0-9A-Fa-f]{4})'";
        Pattern separatorRegex = Pattern.compile(separatorPattern);
        Matcher matcher = separatorRegex.matcher(input);

        // 将匹配到的U&'\001F'和U&'\0001'替换为实际字符
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String unicodeSequence = matcher.group(1);
            char actualChar = (char) Integer.parseInt(unicodeSequence, 16);
            matcher.appendReplacement(result, String.valueOf(actualChar));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    private static String convertUnicodeSequenceToChinese(String unicodeSequence) {
        StringBuilder result = new StringBuilder();
        String[] hexValues = unicodeSequence.split("\\\\");
        for (int i = 0; i < hexValues.length; i++) {
            String hex = hexValues[i];
            if (!hex.isEmpty()) {
                if (hex.startsWith("U&")) {
                    hex = hex.substring(2);  // 去掉开头的U&
                }

                if (i == 0) {
                    result.append(hex);
                } else if (i > 0) {
                    if (hex.length() == 4) {
                        int unicodeValue = Integer.parseInt(hex, 16);
                        result.append((char) unicodeValue);
                    } else {
                        int unicodeValue = Integer.parseInt(hex.substring(0, 4), 16);
                        result.append((char) unicodeValue + hex.substring(4));
                    }
                }
            }
        }

//        for (String hex : hexValues) {
//            if (!hex.isEmpty()) {
//                if (hex.startsWith("U&")) {
//                    hex = hex.substring(2);  // 去掉开头的U&
//                }
//                // Character.isAlphabetic(hex.charAt(0))
//                //  [a-zA-Z].*
//
////                if (Character.isAlphabetic(hex.charAt(0)) || hex.matches("^[_\\-\\&\\W].*")
////                        || (hex.trim().length() < 4 && hex.matches("^\\d+.*"))) {
////                    result.append(hex);
////                } else if (hex.length() == 4) {
////                    int unicodeValue = Integer.parseInt(hex, 16);
////                    result.append((char) unicodeValue);
////                } else if (hex.length() > 4) {
////                    int unicodeValue = Integer.parseInt(hex.substring(0, 4), 16);
////                    result.append((char) unicodeValue + hex.substring(4));
////                }
//
//                if (hex.length() == 4) {
//                    int unicodeValue = Integer.parseInt(hex, 16);
//                    result.append((char) unicodeValue);
//                } else {
//                    int unicodeValue = Integer.parseInt(hex.substring(0, 4), 16);
//                    result.append((char) unicodeValue + hex.substring(4));
//                }
//            }
//        }
        return result.toString();
    }
}

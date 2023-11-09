package com.yanhao;

import java.util.HashMap;
import java.util.Map;

public class TestParser {

    //1、sql语句按分号切割，获取到一个数组formatsql[]
    //2、取出formatsql[0]，解析获取列名和描述数组columnArray[]
    //3、解析formatsql[1]到剩下的放到一个map集合中，Map<key,value>,key存放表名或者字段名，value存放注释内容
    //4、循环columnArray[],获取到单独的列名和描述，然后匹配Map中的key获取到value


    public static void main(String[] args) {
        String sql ="CREATE TABLE DWS_STUDENT_ISINNER_CNT_RT (\n" +
                " DATE_I VARCHAR ( 255 ) NOT NULL,\n" +
                " ISINNER_CD VARCHAR ( 255 ),\n" +
                " ISINNER_NAME VARCHAR ( 255 ) NOT NULL,\n" +
                " STUD_CLICKS BIGINT,\n" +
                " STUD_CNT BIGINT,\n" +
                " UPDATE_TIME VARCHAR ( 255 ),\n" +
                "PRIMARY KEY ( DATE_I, ISINNER_NAME ));\n" +
                "COMMENT ON TABLE DWS_STUDENT_ISINNER_CNT_RT IS '当日学员内勤分类人数表';\n" +
                "COMMENT ON COLUMN DWS_STUDENT_ISINNER_CNT_RT.DATE_I IS '日期,主键';\n" +
                "COMMENT ON COLUMN DWS_STUDENT_ISINNER_CNT_RT.ISINNER_CD IS '0,1,2';\n" +
                "COMMENT ON COLUMN DWS_STUDENT_ISINNER_CNT_RT.ISINNER_NAME IS '0-个险内勤，1-内勤，2-个险增员';\n" +
                "COMMENT ON COLUMN DWS_STUDENT_ISINNER_CNT_RT.STUD_CLICKS IS '学员签到人次';\n" +
                "COMMENT ON COLUMN DWS_STUDENT_ISINNER_CNT_RT.STUD_CNT IS '学员人数';\n" +
                "COMMENT ON COLUMN DWS_STUDENT_ISINNER_CNT_RT.UPDATE_TIME IS '更新时间';";

        sql = sql.replaceAll("\n ", "").replaceAll("\\s +", "").trim();

        System.out.println("传入的sql是：" + sql);

        String[] formatsql = sql.split(";");

        //System.out.println("formatsql数组长度：" + formatsql.length);




        // comment
        Map<String,String> map = new HashMap<String,String>();

        for (int i = 1; i < formatsql.length ; i++) {
            String[] comments = formatsql[i].split(" ");
            String column = comments[3];
            if(comments[3].contains(".")){
                column = comments[3].substring(comments[3].lastIndexOf(".")+1);
            }
            String comment = comments[5];
            //System.out.println(column);
            //System.out.println(comment);


            map.put(column,comment);

        }

        //column
        String columns = formatsql[0];
        int startIndex = columns.indexOf("(");
        int endIndex = columns.lastIndexOf(")");
        columns = columns.substring(startIndex + 1, endIndex).trim();
        String[] columnArray = columns.split(",");
        System.out.println("有 " + columnArray.length + " 列");
        for (int i = 0; i < columnArray.length-2; i++) {
            String s = columnArray[i];
           // System.out.println(s);
            String[] c = s.split(" ");
            //System.out.println(c[0]);
            //System.out.println(c[1]);


            String value ="";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key.equals(c[0])){
                    value = entry.getValue();
                }
            }
            System.out.println(c[0]+"---"+c[1]+"---"+value);

        }

        


    }







}
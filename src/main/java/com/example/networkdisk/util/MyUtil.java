package com.example.networkdisk.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 自己定义的工具类
 */
public class MyUtil {
    /**
     * 将java.util.Date类型转换为java.sql.Timestamp类型
     * 对应MySQL中的datetime类型
     */
    public static Timestamp toTimeStamp(Date date) {
        // 将日期时间转换为数据库中的timestamp类型
        return new Timestamp(date.getTime());
    }

    /**
     * 将java.sql.Timestamp类型转换为java.util.Date类型
     * 将从MySQL中的datetime类型读出来的数据转换为java.util.Date类型
     */
    public static Date toDate(Timestamp timestamp) {

        return new Date(timestamp.getTime());
    }

    /**
     * 将java.util.Date转换为指定格式的String
     */
    public static String toString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将Timestamp转换为指定格式的String
     * 用于数据库中字段类型为datetime
     */
    public static String toString(Timestamp timestamp) {
        return MyUtil.toString(MyUtil.toDate(timestamp));
    }

    /**
     * 将实际的文件名重命名
     */
    public static String getNewFileName(String oldFileName) {
        int lastIndex = oldFileName.lastIndexOf(".");
        String fileType = oldFileName.substring(lastIndex);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmssSSS");
        String time = sdf.format(now);
        return time + fileType;
    }

    /**
     * 生成随机长度为11为由数字和小写字母混合组成的字符串
     */
    public static String createFileId(){
        // 生成随机数字作为数字的位数
        StringBuilder result = new StringBuilder();
        Random rd = new Random();
        int num_count = rd.nextInt(1,10);
        int letter_count = 11 - num_count;

        // 生成数字字符
        int i;
        for(i = 0;i < num_count; i++){
            // 生成0~9整数随机数，并添加到结果里
            result.append(rd.nextInt(9) + "");
        }
        for(i = 0;i < letter_count; i++){
            // 生成a~z随机字母，添加到结果里
            result.append((char)rd.nextInt(97,122));
        }
        // 返回打乱顺序的结果
        return MyUtil.upset(result.toString());
    }

    /**
     *  将字符串内容打乱的方法
     */
    public static String upset(String str){
        char[] arr = str.toCharArray();
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {   //从0索引开始打乱数组
            int index = r.nextInt(arr.length);  //形成随机索引
            char temp = arr[i];                 //中间变量
            arr[i] = arr[index];                //随机索引的数组赋值给当前循环的数组
            arr[index] = temp;                  //值互换
        }
        str = new String(arr);                  //把数组转换成字符串
        return  str;                            //返回字符串
    }

    /**
     * 检查bUser实体类的数据基本错误
     */
    public static String bUserBaseError(BUser bUser){
        String message = "";

        if(bUser.getEmail().isBlank()){
            message += "邮箱必须输入！";
        }
        if(!bUser.getEmail().matches(".+@.+\\..+")){
            message += "邮箱格式不正确！";
        }
        if(bUser.getPwd().length()>20 && bUser.getPwd().length()<6){
            message += "密码长度在6到20之间！";
        }
        if(!bUser.getPwd().matches("[0-9a-zA-Z]{1,}")){
            message += "密码仅限字母和数字，字母不区分大小写";
        }
        if(bUser.getValidateCode().isBlank()){
            message += "验证码必须输入！";
        }
        return message;
    }

    /**
     * 随机生成11位由数字组成的字符串
     */
    public static String createUserId(){
        // 生成随机数字作为数字的位数
        StringBuilder result = new StringBuilder();
        Random rd = new Random();

        // 生成数字字符
        int i;
        for(i = 0;i < 11; i++){
            // 生成0~9整数随机数，并添加到结果里
            result.append(rd.nextInt(9) + "");
        }
        // 返回打乱顺序的结果
        return MyUtil.upset(result.toString());
    }

    /**
     * 随机生成11位由小写字母组成的字符串
     */
    public static String createShareId(){
        // 生成随机数字作为数字的位数
        StringBuilder result = new StringBuilder();
        Random rd = new Random();

        // 生成数字字符
        int i;
        for(i = 0;i < 11; i++){
            // 生成0~9整数随机数，并添加到结果里
            result.append((char)rd.nextInt(97,122));
        }
        // 返回打乱顺序的结果
        return MyUtil.upset(result.toString());
    }

    public static Timestamp getAfterSevenDays(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date date = calendar.getTime();
        return MyUtil.toTimeStamp(date);
    }
}

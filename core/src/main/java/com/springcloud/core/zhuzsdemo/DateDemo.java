package com.springcloud.core.zhuzsdemo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime 格式化常见问题
 *
 * @author zhu_zishuang
 * @date 2020-12-30
 */
public class DateDemo {
    public static void main(String[] args) {

        /**
         * 1.LocalDateTime与String互转
         */
        //字符串转时间
        String dateTimeStr = "2020-12-20 19:46:15";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, df);
        System.out.println("dateTime: " + dateTime.toString());

        // 时间转字符串
        //时间转字符串格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String dateTimeString = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        System.out.println("dateTimeString: " + dateTimeString);


        /**
         * 2.yyyy-MM-dd 和 YYYY-MM-dd 的区别：YYYY-MM-dd，若本周跨年（给定日期所在周跨年），则返回下一年
         */
        LocalDateTime time = LocalDateTime.now();
        System.out.println("time: " + time);

        // 获取秒数
        System.out.println("toEpochSecond: " + time.toEpochSecond(ZoneOffset.of("+8")));

        // 获取毫秒数
        System.out.println("toEpochMilli: " + time.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        LocalDate date = LocalDate.parse("2020-12-30");
        System.out.println("date: " + date);

        DateTimeFormatter yyyyFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("yyyy-MM-dd: " + yyyyFormat.format(date));

        DateTimeFormatter YYYYFormat = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        System.out.println("YYYY-MM-dd: " + YYYYFormat.format(date));


        /**
         * 3.DD 和 dd 的 区别：DD代表的是处于这一年中那一天，不是处于这个月的那一天
         */
        LocalDate date2 = LocalDate.of(2020, 10, 31);
        System.out.println("date2: " + date2);

        DateTimeFormatter yyyyFormat2 = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        System.out.println("YYYY-MM-dd: " + yyyyFormat2.format(date2));

        DateTimeFormatter YYYYFormat2 = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        System.out.println("YYYY-MM-DD: " + YYYYFormat2.format(date2));

        /**
         * 4.Date 和 LocalDateTime 互转
         * Date：toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
         *       from(localDateTime.toInstant(ZoneOffset.of("+8"))
         */

    }

}


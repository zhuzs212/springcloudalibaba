package com.springcloud.core.zhuzsdemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhu_zishuang
 * @date 2021/10/19
 */
public class demo {
    public static void main(String[] args) {
        List list  = new ArrayList(10);
        list.add(1);
        list.add("字符串");
        list.add(new ArrayList<>());
        list.add(new Date());
        System.out.println(list.toString());

        System.out.println(list.indexOf("23"));
    }
}

package com.springcloud.core.util;

import com.google.common.collect.Lists;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author zhuzs
 * @date 2023/5/5 16:31
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        System.out.println("DLT:");
        for (int i = 0; i < 5; i++) {
            List<Integer> dlt = DLT();
            System.out.println(dlt);
        }

        System.out.println("SSQ:");
        for (int i = 0; i < 5; i++) {
            List<Integer> ssq = SSQ();
            System.out.println(ssq);
        }

    }

    // 随机
    public static List<Integer> DLT() {
        // 1~35
        int[] qu = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35
        };
        // 1~12
        int[] hq = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        };

        Set<Integer> resultQ = new TreeSet<>();
        Set<Integer> resultH = new TreeSet<>();
        while (resultQ.size() < 5) {
            int index = RandomUtil.randomInt(0, 35);
            resultQ.add(qu[index]);
        }
//        log.info("resultQ: {}", resultQ);

        while (resultH.size() < 2) {
            int index = RandomUtil.randomInt(0, 12);
            resultH.add(hq[index]);
        }
//        log.info("resultH: {}", resultH);

        List<Integer> result = new ArrayList<>(7);
        result.addAll(resultQ);
//        log.info("result: {}", result);
        result.addAll(resultH);
//        log.info("result: {}", result);

        return result;
    }

    // 随机
    public static List<Integer> SSQ() {
        List<Integer> integerlist = Lists.newArrayList();
        // 1~33
        int[] qu = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33
        };
        // 1~16
        int[] hq = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
        };

        Set<Integer> resultQ = new TreeSet<>();
        while (resultQ.size() < 6) {
            int index = RandomUtil.randomInt(0, 33);
            resultQ.add(qu[index]);
        }
//        log.info("resultQ: {}", resultQ);

        int index = RandomUtil.randomInt(0, 16);
        int h = hq[index];

        List<Integer> result = new ArrayList<>(7);
        result.addAll(resultQ);
//        log.info("result: {}", result);
        result.add(h);
//        log.info("result: {}", result);

        return result;
    }

}

package com.springcloud.core.zhuzsdemo;

import java.util.Arrays;

/**
 * 数组拷贝
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
public class ArrayDemo {

    public static void main(String[] args) {

        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6};
        int[] arr2 = new int[7];
        // 数组拷贝1
        sysArrayCopy(arr1, arr2);

        // 数组拷贝2
        arrCopyOf(arr1, arr2);
    }

    /**
     * 该方法可能存在数组下表越界问题
     * 注：
     * 复制的长度，应小于源数组长度及目标数组长度
     *
     * @param arr1 源数组
     * @param arr2 目标数组
     * @author zhu_zishuang
     * @date 2021-03-03
     */
    public static void sysArrayCopy(int[] arr1, int[] arr2) {
        System.arraycopy(arr1, 1, arr2, 0, arr2.length);
        System.out.println(" sysArrayCopy 后 arr2 :" + Arrays.toString(arr2));
    }

    /**
     * 该方法常用于数组的扩容
     *
     * @param arr1 源数组
     * @param arr2 目标数组
     * @author zhu_zishuang
     * @date 2021-03-03
     */
    public static void arrCopyOf(int[] arr1, int[] arr2) {
        arr2 = Arrays.copyOf(arr1, arr1.length * 2);
        System.out.println(" arrCopyOf 后 arr2 :" + Arrays.toString(arr2));
    }
}


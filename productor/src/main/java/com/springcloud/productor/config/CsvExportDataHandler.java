package com.springcloud.productor.config;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.springcloud.productor.domain.TChagRec;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 自定义字段处理
 * Created by macro on 2021/10/13.
 */
@Slf4j
public class CsvExportDataHandler extends ExcelDataHandlerDefaultImpl<TChagRec> {
    /**
     *
     * @param chagRec 当前对象
     * @param name 当前字段名称（文件列名）
     * @param value 当前值
     * @return
     */
    @Override
    public Object exportHandler(TChagRec chagRec, String name, Object value) {
        log.info("value: {}", value);
        /**
         * 字典值翻译
         */
        // 订单状态
        if ("订单状态".equals(name)) {
            String emptyValue = "未支付";
            if (null == value) {
                return super.exportHandler(chagRec, name, emptyValue);
            }
            if (value.equals(2)) {
                return super.exportHandler(chagRec, name, "已支付");
            }
            return super.exportHandler(chagRec, name, emptyValue);
        }
        // 支付方式
        if ("支付方式".equals(name)) {
            // 0：未开票；1：已开票
            String emptyValue = "充电卡";
            if (null == value) {
                return super.exportHandler(chagRec, name, emptyValue);
            }
            if (value.equals("2")) {
                return super.exportHandler(chagRec, name, "微信");
            }
            return super.exportHandler(chagRec, name, emptyValue);
        }

        /**
         * BigDecimal 类型数值写入文件显示科学技术法的问题解决
         * - b.toPlainString()
         */
        // 支付方式
        if ("充电电费(元)".equals(name)
                || "服务费(元)".equals(name)
                || "总费用(元)".equals(name)
                || "尖金额(元)".equals(name)
                || "峰金额(元)".equals(name)
                || "平金额(元)".equals(name)
                || "谷金额(元)".equals(name)
                || "结算金额(元)".equals(name)) {
            // 0：未开票；1：已开票
            String emptyValue = "";
            if (null == value) {
                return super.exportHandler(chagRec, name, emptyValue);
            }
            if (value instanceof BigDecimal) {
                BigDecimal b = (BigDecimal) value;
                return super.exportHandler(chagRec, name, b.toPlainString());
            }
            return super.exportHandler(chagRec, name, value);
        }

        return super.exportHandler(chagRec, name, value);
    }

    @Override
    public Object importHandler(TChagRec chagRec, String name, Object value) {
        return super.importHandler(chagRec, name, value);
    }
}

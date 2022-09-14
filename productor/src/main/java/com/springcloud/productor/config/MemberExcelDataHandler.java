package com.springcloud.productor.config;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.springcloud.productor.domain.TChagRec;

/**
 * 自定义字段处理
 * Created by macro on 2021/10/13.
 */
public class MemberExcelDataHandler extends ExcelDataHandlerDefaultImpl<TChagRec> {

    @Override
    public Object exportHandler(TChagRec tChagRec, String name, Object value) {
        /**
         * 字典值翻译
         */
        if ("订单状态".equals(name)) {
            Integer emptyValue = 0;
            if (null == value) {
                return super.exportHandler(tChagRec, name, emptyValue);
            }
            if (value.equals("已支付")) {
                return super.exportHandler(tChagRec, name, 1);
            }
            return super.exportHandler(tChagRec, name, emptyValue);

        }

        if ("支付方式".equals(name)) {
            // 1：充电卡；2：微信；3：支付宝
            Integer emptyValue = 1;
            if (null == value) {
                return super.exportHandler(tChagRec, name, emptyValue);
            }
            if (value.equals("微信支付")) {
                return super.exportHandler(tChagRec, name, 2);
            }
            if (value.equals("支付宝")) {
                return super.exportHandler(tChagRec, name, 3);
            }
            return super.exportHandler(tChagRec, name, emptyValue);
        }

        return super.exportHandler(tChagRec, name, value);
    }

    @Override
    public Object importHandler(TChagRec tChagRec, String name, Object value) {
        return super.importHandler(tChagRec, name, value);
    }
}

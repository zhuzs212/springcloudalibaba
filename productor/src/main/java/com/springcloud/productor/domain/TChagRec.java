package com.springcloud.productor.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 车联网订单 映射实体
 *
 * @TableName t_chag_rec
 */
@Data
@Accessors(chain = false)
@ExcelTarget("tChagRec")
public class TChagRec implements Serializable {

    /**
     * 厂家标识
     */
    @Excel(name = "商户编号")
    private String factId;

    /**
     * 充电用户标识
     */
    @Excel(name = "用户id")
    private String userId;

    /**
     * 充电卡号
     */
    @Excel(name = "卡号")
    private String cardNo;

    /**
     * 充电起始时间
     */
    @Excel(name = "开始时间", importFormat = "yyyy-MM-dd HH:mm:ss")
    private String chagStartDate;

    /**
     * 充电结束时间
     */
    @Excel(name = "结束时间", importFormat = "yyyy-MM-dd HH:mm:ss")
    private String chagEndDate;

    /**
     * 充电电量
     */
    @Excel(name = "交易电量/kwh")
    private String chagEq;

    /**
     * 充电费用
     */
    @Excel(name = "电费/元")
    private String chagCost;

    /**
     * 尖电量
     */
    @Excel(name = "尖电量")
    private String sharpEq;

    /**
     * 峰电量
     */
    @Excel(name = "峰电量")
    private String peakEq;

    /**
     * 平电量
     */
    @Excel(name = "平电量")
    private String platEq;

    /**
     * 谷电量
     */
    @Excel(name = "谷电量")
    private String valleyEq;

    /**
     * 服务费用
     */
    @Excel(name = "服务费/元")
    private String svrCost;

    /**
     * 总金额（电费+服务费+其他费用）
     */
    @Excel(name = "交易金额/元")
    private String totalMoney;

    /**
     * 结算费用（支付费用）
     */
    @Excel(name = "优惠后实扣金额/元")
    private String closingCost;

    /**
     * 支付时间
     */
    @Excel(name = "支付时间", importFormat = "yyyy-MM-dd HH:mm:ss")
    private String payDate;

    /**
     * 支付方式:字典类型301
     */
    @Excel(name = "支付方式")
    private String payWay;

    /**
     * 发票是否打印
     */
    @Excel(name = "是否开具发票")
    private String invoiceFlag;

    /**
     * 生成时间
     */
    @Excel(name = "订单创建时间", importFormat = "yyyy-MM-dd HH:mm:ss")
    private String genTime;

    /**
     * 流水号对方运营商交易编号
     */
    @Excel(name = "第三方流水账号")
    private String tradeFlowNo;

    /**
     * 卡前金额
     */
    @Excel(name = "钱包扣款前余额/元")
    private String walletBalance;

    /**
     * 卡后金额
     */
    @Excel(name = "钱包扣款后余额/元")
    private String electWalbalan;

//    /**
//     * 车辆VIN码
//     */
//    @Excel(name = "VIN码")
//    private String vin;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String evNum;

    /**
     * 电表初始读数
     */
    @Excel(name = "电表总起值")
    private String beginMeterVal;

    /**
     * 电表截止读数
     */
    @Excel(name = "电表总止值")
    private String endMeterVal;

    /**
     * 发票号
     */
    @Excel(name = "发票ID")
    private String invoiceNo;

    /**
     * 停止原因：字典713
     */
    @Excel(name = "交易结束原因")
    private String stopReason;
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        String str = "123456";
        Object obj = "123456";
        System.out.println(obj instanceof Number);
    }
}
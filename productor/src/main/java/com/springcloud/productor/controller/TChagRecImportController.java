package com.springcloud.productor.controller;

import cn.afterturn.easypoi.csv.CsvExportUtil;
import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvExportParams;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.handler.inter.IWriter;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.json.JSONObject;
import com.springcloud.productor.config.MemberExcelDataHandler;
import com.springcloud.productor.domain.TChagRec;
import com.springcloud.productor.service.TChagRecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车联网订单 历史数据导入
 *
 * @author zhuzs
 * @date 2022/8/23 16:26
 */
@RestController
@RequestMapping("tChagRecImport")
@Slf4j
public class TChagRecImportController {

    @Autowired
    private TChagRecService recService;

    /**
     * CSV 文件导入
     *
     * @param file
     * @throws Exception
     */
    @RequestMapping("/importCsv")
    public void importCsv(@RequestParam(value = "file") MultipartFile file) throws Exception {

        CsvImportParams params = new CsvImportParams();
        MemberExcelDataHandler handle = new MemberExcelDataHandler();
//        handle.setNeedHandlerFields(new String[]{"金额(元)", "商品"});
//        params.setVerifyHandler(verifyHandler);
        params.setHeadRows(1);
        params.setDataHandler(handle);

        long start = System.currentTimeMillis();
        //使用api获取到的List数据
        List<TChagRec> list = CsvImportUtil.importCsv(file.getInputStream(), TChagRec.class, params);
        long end = System.currentTimeMillis();
        log.info("解析结束，数据大小，size: {}, 耗时: {}", list.size(), end - start);

        if (!list.isEmpty()) {
            //保存数据，设置每次批量保存的条数
            log.info("开始写库操作...");
            recService.saveBatch(list, 10000);
            list.clear();
            log.info("写库结束, 耗时: {}", end - start);
        }
    }

    /**
     * 导出csv文件
     *
     * @return
     * @date 2021/11/4 21:12
     * @author zk_yjl
     */
    @GetMapping("/exportCsv")
    @ResponseBody
    public void exportCsv(HttpServletResponse response) throws IOException {
        //1. 通过传入的参数，和相关的业务代码逻辑处理，获取相应的数据.
        //2. 将数据进行转换，转换成 List<User> 的形式.
        List<TChagRec> dataList = recService.list();
        log.info("dataList: {}", dataList);
        //将数据进行下载.
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            //响应类型
//            response.setContentType("multipart/form-data");
//            response.setCharacterEncoding("utf-8");
            //进行下载
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试中文文件名", "UTF-8").replaceAll("\\+", "%20");
            //响应的是  .csv 文件的后缀
//            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".csv");
            // 这里需要设置不关闭流
            String filePath = "/Users/zhuzs/Downloads/temporary/" + File.separator + fileName + ".csv";
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            //将数据，写入到 文件里面。 主要是这一行代码逻辑
            CsvUtil.getWriter(file, Charset.forName("UTF-8")).write(dataList).close();
//            downloadFile(response,file);
            //将该文件删除
//            file.delete();
        } catch (Exception e) {
            // 重置response
//            response.reset();
//            response.setContentType("application/json");
//            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            JSONObject jsonObject = new JSONObject();
//            response.getWriter().println(jsonObject);
        }
    }

    /**
     * 导出csv文件
     *
     * @return
     * @date 2021/11/4 21:12
     * @author zk_yjl
     */
    @GetMapping("/exportCsv1")
    @ResponseBody
    public void exportCsv1(HttpServletResponse response) throws IOException {
        // TODO 文件名：当前日期时间戳 yyyyMMddHHmmss
        String fileName = "订单数据" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        // TODO 文件存放相对路径
        String filePath = "/Users/zhuzs/Downloads/temporary/" + fileName + ".csv";
        File file = new File(filePath);

        // TODO 动态生成导出列
        List<String> headers = new ArrayList<>();
        headers.add("枪ID");
        headers.add("卡号");
        headers.add("车牌号");
        headers.add("开始充电时间");
        headers.add("支付方式");
        headers.add("订单状态");
        List<String> values = new ArrayList<>();
        values.add("devId");
        values.add("cardNo");
        values.add("evNum");
        values.add("chagStartDate");
        values.add("payWay");
        values.add("deductStatus");

        CsvExportParams params = new CsvExportParams();
        List<ExcelExportEntity> colList = new ArrayList<>();
        MemberExcelDataHandler handle = new MemberExcelDataHandler();
        // TODO 特殊处理的字段
        handle.setNeedHandlerFields(new String[]{"订单状态", "支付方式"});
        params.setDataHandler(handle);
        for (int i = 0; i < headers.size(); i++) {
            ExcelExportEntity colEntity = new ExcelExportEntity(headers.get(i), values.get(i));
            colList.add(colEntity);
        }
        OutputStream os = Files.newOutputStream(file.toPath());

        long start = System.currentTimeMillis();
        // TODO 查询条件
        List<TChagRec> list = recService.list();
        IWriter<Void> voidIWriter = CsvExportUtil.exportCsv(params, colList, os);
        voidIWriter.write(list);
        voidIWriter.close();
        long end = System.currentTimeMillis();

        // TODO 维护订单导出信息，表结构待定/分页查询接口
        log.info("导出完成, 耗时: {}", end - start);
    }

    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        System.out.println(URLEncoder.encode("订单数据", "UTF-8").replaceAll("\\+", "%20"));
        //第五种
        ApplicationHome h = new ApplicationHome(TChagRecImportController.class);
        String jarF = h.getSource().getParentFile().toString();
        System.out.println(jarF);
    }
}

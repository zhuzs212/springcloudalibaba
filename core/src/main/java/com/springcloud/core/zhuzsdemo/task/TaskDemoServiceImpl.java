package com.springcloud.core.zhuzsdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;

/**
 * 定时任务DEMO接口实现
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
@Service
@Slf4j
public class TaskDemoServiceImpl implements TaskDemoService {
    @Override
    public void dailyWriting() {
        // 文件开始位置
        long begin = 0L;
        try (
                /*
                 * 创建source表示要复制的文件
                 * 源文件只需要有读权限
                 */
                RandomAccessFile source = new RandomAccessFile("/Users/zhuzs/zhuzs/1.代码评审记录.txt", "r");
                /*
                 * 创建dest表示复制后的文件
                 * 要写入的文件需要读写权限
                 */
                RandomAccessFile dest = new RandomAccessFile("/Users/zhuzs/zhuzs/1.代码评审记录" + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + ".txt ", "rw");
        ) {

            source.seek(begin);
            dest.seek(begin);
            /*
             * source.read()等于-1时表示读到文件末尾
             */
            int d = -1;
            while ((d = source.read()) != -1) {
                begin += d;
                dest.write(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


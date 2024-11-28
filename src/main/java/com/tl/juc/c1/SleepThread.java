package com.tl.juc.c1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SleepThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->
        {
            try {
                // 单位毫秒  完成延迟任务 ,用的少， 更多自己测试  模拟业务执行
                // Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("睡眠完毕");

        } );
        t1.start();
        // 线程中断
        t1.interrupt();
    }
}

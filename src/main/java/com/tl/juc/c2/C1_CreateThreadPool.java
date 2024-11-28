package com.tl.juc.c2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
@Slf4j
public class C1_CreateThreadPool {
    public static void main(String[] args) {
        class Task implements Runnable {
            @Override
            public void run() {
                log.info("\t 办理业务");
            }
        }
        Task task = new Task();

        // 固定大小的线程池
         ExecutorService threadPool = Executors.newFixedThreadPool(5); //一个饭店，5张桌子

        // ExecutorService threadPool2 = Executors.newSingleThreadExecutor(); //一个饭店，1张桌子
        //ExecutorService threadPool3 = Executors.newCachedThreadPool(); //一个饭店，可以动态加桌子
        //ScheduledExecutorService sthreadPool = Executors.newScheduledThreadPool(5);


        //10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(task);
                //sthreadPool.schedule(task, 5, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池 ， 问题： 线程池中线程一直占用系统资源， 内存泄露， 主线程只会不会退出
            // 不会立马停止正在执行的线程，会等待所有的任务执行完后才彻底关闭
            // threadPool.shutdown();

            // 不会立马停止正在执行的线程，只会等待正在执行的线程执行完后才彻底关闭
            threadPool.shutdownNow();
            try {

                // 等待线程池关闭， 等待线程池中所有的线程执行完。
                threadPool.awaitTermination(Long.MAX_VALUE,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 判断线程池是否真正“终止”了， 并且代表线程也已经执行完毕
            System.out.println(threadPool.isTerminated());
        }

    }
}

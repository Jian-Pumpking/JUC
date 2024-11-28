package com.tl.juc.c4;


import com.tl.juc.c3.C0_ThreadNoSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * ReentrantLock 更灵活 ， 手动显示上锁和解锁  java实现，  锁粒度更细
 *      一定解锁， 最好解锁前lock.isHeldByCurrentThread()。  适用动态解锁场景
 * synchronized          隐式上锁和解锁   jvm实现 c++
 */
public class C6_ReentrantLock {

    private static Integer stock=100000;

    // 1. 创建ReentrantLock对象
    private static ReentrantLock lock=new ReentrantLock();

    static class StockRunnable implements Runnable {
        @Override
        public /*synchronized*/ void run() {

            try {

                lock.lock();

                stock--;
            } finally {
                // 当前锁是否在当前线程加锁
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        StockRunnable task = new StockRunnable();

        try {
            for (int i = 0; i < 100000; i++) {
                threadPool.execute(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
            // 等待关闭
            threadPool.awaitTermination(1000, TimeUnit.SECONDS);
        }



        System.out.println("剩余库存："+stock);


    }
}

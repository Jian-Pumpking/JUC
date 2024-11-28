package com.tl.juc.Q.SynchronizedUsage;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * 加锁 this 时，      表示用当前的对象进行加锁，每个对象都对应了一把锁；
 * 加锁 xxx.class 时，表示用某个类（而非类实例）来加锁，它是应用程序级别的，是全局生效的
 */
public class UsageBlock {
        public static void main(String[] args) throws InterruptedException {
            // 创建线程池同时执行任务
            ExecutorService threadPool = Executors.newFixedThreadPool(10);

            // 执行两次 synchronized(this)
            threadPool.execute(() -> {
                UsageBlock usage = new UsageBlock();
                usage.thisMethod();
            });
            threadPool.execute(() -> {
                UsageBlock usage2 = new UsageBlock();
                usage2.thisMethod();
            });

            // 执行两次 synchronized(xxx.class)
            threadPool.execute(() -> {
                UsageBlock usage3 = new UsageBlock();
                usage3.classMethod();
            });
            threadPool.execute(() -> {
                UsageBlock usage4 = new UsageBlock();
                usage4.classMethod();
            });
        }

        /**
         * synchronized(this) 加锁
         * 本方法的执行需要 3s（因为有 3s 的休眠时间）
         */
        public void thisMethod() {
            synchronized (this) {
                System.out.println("synchronized(this) 加锁：" + LocalDateTime.now());
                try {
                    // 休眠 3s
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * synchronized(xxx.class) 加锁
         * 本方法的执行需要 3s（因为有 3s 的休眠时间）
         */
        public void classMethod() {
            synchronized (UsageBlock.class) {
                System.out.println("synchronized(xxx.class) 加锁：" + LocalDateTime.now());
                try {
                    // 休眠 3s
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


}

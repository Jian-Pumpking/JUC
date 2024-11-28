package com.tl.juc.Q.SynchronizedUsage;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * 加锁 非静态对象 时，  同一个厕所只能一个人上（独享）
 * 加锁 静态对象 时，    整个世界厕所只能一个人上（因为是共享的）
 */
public class UsageFiled {
        public static void main(String[] args) throws InterruptedException {
            // 创建线程池同时执行任务
            ExecutorService threadPool = Executors.newFixedThreadPool(10);

            UsageFiled usage = new UsageFiled();
            // 执行两次 synchronized(非静态)
            threadPool.execute(() -> {
                usage.thisMethod();
            });
            threadPool.execute(() -> {
                usage.thisMethod();
            });

            // 执行两次 synchronized(静态)
            threadPool.execute(() -> {
                UsageFiled usage3 = new UsageFiled();
                usage3.classMethod();
            });
            threadPool.execute(() -> {
                UsageFiled usage4 = new UsageFiled();
                usage4.classMethod();
            });
        }


        private Object value=new Object();
        private static Object staticValue=new Object();

        /**
         * synchronized(this) 加锁
         * 本方法的执行需要 3s（因为有 3s 的休眠时间）
         */
        public void thisMethod() {
            synchronized (value) {
                System.out.println("synchronized(value) 加锁：" + LocalDateTime.now());
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
            synchronized (staticValue) {
                System.out.println("synchronized(staticValue) 加锁：" + LocalDateTime.now());
                try {
                    // 休眠 3s
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


}

package com.tl.juc.Q.SynchronizedUsage;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * 当 synchronized 修饰普通方法时，被修饰的方法被称为同步方法，其作用范围是整个方法，作用的对象是调用这个方法的对象。
 *
 *
 * 当 synchronized 修饰静态方法时，其作用范围是整个程序，这个锁对于所有调用这个锁的对象都是互斥的。
 */
public class UsageMethod {


    public static void main(String[] args) throws InterruptedException {
        // 创建线程池同时执行任务
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // 执行两次静态方法
        threadPool.execute(() -> {
            UsageMethod.staticMethod();
        });
        threadPool.execute(() -> {
            UsageMethod.staticMethod();
        });

        UsageMethod usage = new UsageMethod();
        // 执行两次普通方法
        threadPool.execute(() -> {
            usage.method();
        });
        threadPool.execute(() -> {
            usage.method();
        });
    }

    /**
     * synchronized 修饰普通方法
     * 本方法的执行需要 3s（因为有 3s 的休眠时间）
     */
    public synchronized void method() {
        System.out.println("普通方法执行时间：" + LocalDateTime.now());
        try {
            // 休眠 3s
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * synchronized 修饰静态方法
     * 本方法的执行需要 3s（因为有 3s 的休眠时间）
     */
    public static synchronized void staticMethod() {
        System.out.println("静态方法执行时间：" + LocalDateTime.now());
        try {
            // 休眠 3s
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


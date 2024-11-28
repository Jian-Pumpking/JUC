package com.tl.juc.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C4_InheritableThreadLocal_3 {
    public static void main(String[] args) throws InterruptedException {
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("test");

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            // 在第11个任务的是后重新赋值
            if (i == 10) {
                threadLocal.set("test11");
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String value = threadLocal.get();
                    System.out.println("value:" + value);
                }
            });
        }

        Thread.sleep(10000);
    }
}

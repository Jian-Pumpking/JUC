package com.tl.juc.c2;

import java.util.concurrent.*;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C2_CustomeThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                10,
                20,
                0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //10个顾客请求


        class MyTask implements Runnable {
            int i = 0;

            public MyTask(int i) {
                this.i = i;
            }

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "程序员做第" + i + "个项目");
                try {
                    Thread.sleep(3000L);//业务逻辑
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            for (int i = 1; i <= 100; i++) {
                MyTask task=new MyTask(i);
                threadPool.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}

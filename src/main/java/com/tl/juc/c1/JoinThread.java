package com.tl.juc.c1;

import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class JoinThread {

    static  int value=1;
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() ->
        {
            // todo .. 业务
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value=10;
            System.out.println("线程Runnable");
        } );
        t1.start();  // 异步
        System.out.println(t1.isAlive());
        t1.join();  // 异步变成同步
        System.out.println(t1.isAlive());
        System.out.println("主线程:"+value);
    }
}

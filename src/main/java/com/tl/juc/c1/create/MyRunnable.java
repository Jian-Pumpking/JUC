package com.tl.juc.c1.create;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // 线程执行的代码...

        System.out.println("线程Runnable");
    }
}
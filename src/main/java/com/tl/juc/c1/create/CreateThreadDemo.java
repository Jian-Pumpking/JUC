package com.tl.juc.c1.create;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class CreateThreadDemo {

        public static void main(String[] args) {
            // 通过继承Thread
            MyThread myThread = new MyThread();
            myThread.start();  // 开启异步线程

            // 通过实现Runnable接口
            Thread thread = new Thread(new MyRunnable());
            thread.start();

            System.out.println("主线程");
        }



}

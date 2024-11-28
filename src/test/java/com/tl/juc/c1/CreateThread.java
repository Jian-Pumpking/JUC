package com.tl.juc.c1;

import org.junit.Test;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class CreateThread {

    public static void main(String[] args) throws InterruptedException {

    }
    @Test
    public void testRunnable(){
        // new Runnable匿名内部类
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("发送短信");
                }
            }
        });
        t1.start();
         
    }

    @Test
    public void testThread(){

        // new Thread 匿名内部类
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("发送短信");
            }
        };
        t2.start();
    }
}

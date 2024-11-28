package com.tl.juc.c4;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C4_SleepAndWait {

    public static  final  Object monitor=new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {

            System.out.println(mainThread.getState());
        });


        thread.start();
        synchronized (monitor) {

            monitor.wait(2000);
        }
    }
}

package com.tl.juc.c1;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class YieldThread {

    public static void main(String[] args) {

        class Task1 implements Runnable{
            @Override
            public void run() {
                for (int i = 0;i < 200;i++){
                    System.out.println("A:"+i);
                }
            }
        }
        class Task2 implements Runnable{
            @Override
            public void run() {
                for (int i = 0;i < 10;i++){
                    //Thread.yield();
                    System.out.println("B:"+i);
                }
            }
        }
        Thread thread1 = new Thread(new Task2());
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        Thread thread2 = new Thread(new Task1());
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.start();
    }
}

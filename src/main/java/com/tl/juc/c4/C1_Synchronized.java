package com.tl.juc.c4;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * 悲观锁
 */
public class C1_Synchronized {

    private  static  Object lock=new Object();
    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {

            synchronized (lock){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程执行...");
            }
        }).start();


        Thread.sleep(1000);
        synchronized (lock) {
            System.out.println("主线程执行...");
        }
    }
}

package com.tl.juc.c1;


/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class CreateThread {

    public static void main(String[] args) throws InterruptedException {

        createRunnable();
        createThread();

    }
    public static void createRunnable(){
        // new Runnable匿名内部类
        Thread t1 = new Thread(() ->
        // =run
        {
            System.out.println("线程Runnable");
        } );
        t1.start();
        t1.interrupt(); 
    }

    public static void createThread(){

        // new Thread 匿名内部类
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("线程Thread");
            }
        };
        t2.start();
    }

}

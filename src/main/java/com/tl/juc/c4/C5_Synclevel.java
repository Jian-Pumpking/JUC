package com.tl.juc.c4;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C5_Synclevel {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(4100);
        class T{
            Integer age;
        }

         T o = new T();
        // System.out.println(ClassLayout.parseInstance(o).toPrintable());


       // 偏向锁
      /* synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }*/

       //  没有线程竞争的条件下 不会升级
       /* synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }*/



        /*
        轻量级锁*
        *
         synchronized (o){
            System.out.println("Main = "+ClassLayout.parseInstance(o).toPrintable());
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    System.out.println("Thread = "+ClassLayout.parseInstance(o).toPrintable());
                }
            }
        });
        thread.start();
        thread.join();
        System.out.println("End = "+ClassLayout.parseInstance(o).toPrintable());
 */



        /*重量锁*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    System.out.println("Thread1 = "+ClassLayout.parseInstance(o).toPrintable());
                    try{
                        TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    System.out.println("Thread2 = "+ClassLayout.parseInstance(o).toPrintable());
                    try{
                        TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try{
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("main:"+ClassLayout.parseInstance(o).toPrintable());


    }
}

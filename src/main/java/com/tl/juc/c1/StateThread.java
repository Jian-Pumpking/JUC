package com.tl.juc.c1;

import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class StateThread {

    public void test1() { //NEW
        Thread t1 = new Thread(() -> {
            System.out.println("Thread1~~~~");
        });
        System.out.println(t1.getState());
    }

    public void test2() throws InterruptedException { //TERMINATED
        Thread t1 = new Thread(() -> {
            System.out.println("线程开始执行");
            System.out.println("线程结束执行");
        });
        t1.start();
        Thread.sleep(3000);
        System.out.println(t1.getState());
    }

    public void test3() { //RUNNABLE
        Thread t1 = new Thread(() -> {

        });
        t1.start();
        System.out.println(t1.getState());
    }

    public static void test4() throws InterruptedException { //BLOCKED
        class Table {
            public synchronized void use() {
                System.out.println(Thread.currentThread().getName() + "-使用桌子");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "就餐结束");
            }
        }


        System.out.println(Thread.currentThread().getName());
        Table table = new Table();
        Thread student1 = new Thread(() -> {
            table.use();
        }, "s1");
        Thread student2 = new Thread(() -> {
            table.use();
        }, "s2");
        student1.start();
        Thread.sleep(1000);
        student2.start();
        Thread.sleep(500);
        System.out.println(student2.getState());
    }


    // WAITING/TIMED_WAITING
    public static void test5() throws InterruptedException {

        class Table1 {
            public synchronized void use() throws Exception {
                System.out.println(Thread.currentThread().getName() + "-使用桌子");
                //忘记点餐了
                System.out.println("忘记点餐了");
                wait(3000);
                System.out.println(Thread.currentThread().getName() + "就餐结束");
            }
        }
        Table1 table = new Table1();
        Thread student1 = new Thread(() -> {
            try {
                table.use();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "s1");
        student1.start();
        Thread.sleep(100);
        System.out.println(student1.getState());
    }


    public static void main(String[] args) throws InterruptedException {
        test5();
    }



}
package com.tl.juc.c4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C7_ReentrantLock_Interrupt {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("线程 1 获取了锁");
                    Thread.sleep(5000); // hold the lock for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println("线程 2 获取了锁");
                } catch (InterruptedException e) {
                    System.out.println("线程 2异常");
                    e.printStackTrace();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        });

        thread1.start();
        Thread.sleep(1000); // ensure thread1 acquires the lock first
        thread2.start();
        Thread.sleep(1000); // ensure thread2 starts to wait for the lock
        thread2.interrupt(); // interrupt thread2
    }
}
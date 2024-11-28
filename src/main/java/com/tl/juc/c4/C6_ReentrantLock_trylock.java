package com.tl.juc.c4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * ReentrantLock  提供 尝试获取锁的方法， 并且可以设置等待时间
 *
 */
public class C6_ReentrantLock_trylock {

    private static ReentrantLock lock1 = new ReentrantLock(false);
    public static class T extends Thread {
        public T(String name) {
            super(name);
        }
        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis() + ":" + this.getName() + "开始获取锁!");
                // tryLock 尝试获取锁， 如果锁被占用，返回false。   没有占用返回true  . 指定获取锁的等待时间
                if (lock1.tryLock(2,TimeUnit.SECONDS)) {  // lock()
                    System.out.println(System.currentTimeMillis() + ":" + this.getName() + "获取到了锁!");
                    //获取到锁之后，休眠5秒
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println(System.currentTimeMillis() + ":" + this.getName() + "未能获取到锁!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        T t2 = new T("t2");
        t1.start();
        t2.start();
    }
}

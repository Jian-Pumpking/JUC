package com.tl.juc.c1;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * 打断线程
 */
public class InterruptThread {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            while (true){

                // 每隔1s 将时间片清除
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 当出现InterruptedException  会清除中断标记      false
                    e.printStackTrace();
                    // 再次加上中断标记
                    Thread.currentThread().interrupt();      // true
                }
                //  如果中断的标记为true
                // 获取线程中断标记，  并且会清除标记
                System.out.println(Thread.currentThread().isInterrupted());
                if(Thread.interrupted()){
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }

                // 定时监控系统...
                System.out.println("定时监控...");
            }
        });
        thread.start();
        // 只是通知线程需要中断， 线程不会立马中断， 只是给线程做个标记   ,  给线程打了中断标记=true
        thread.interrupt();

    }
}

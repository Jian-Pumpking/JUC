package com.tl.juc.c4;

import com.tl.juc.c3.C0_ThreadNoSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C2_CAS {


    // 库存
    public static AtomicInteger stock =new AtomicInteger(1000000);
    static class StockRunnable implements Runnable {

        @Override
        public  void run() {
            int oldValue;
            int newValue;
            do {
                oldValue = stock.get();
                newValue = oldValue -1;
            } while (!stock.compareAndSet(oldValue, newValue));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        C2_CAS.StockRunnable task=new C2_CAS.StockRunnable();


        try {
            for (int i = 0; i < 1000000; i++) {
                threadPool.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
            // 等待关闭
            threadPool.awaitTermination(1000, TimeUnit.SECONDS);
        }
        System.out.println("剩余库存："+stock);
    }


}

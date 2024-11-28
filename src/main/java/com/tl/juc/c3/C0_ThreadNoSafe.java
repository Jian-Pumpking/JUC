package com.tl.juc.c3;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C0_ThreadNoSafe {
    // 库存
    public static AtomicInteger stock =new AtomicInteger(1000000);
    static class StockRunnable implements Runnable {

        @Override
        public /*synchronized*/ void run() {

            if(stock.get()>0){
                // 购买.. stock=stock-1； 1000000-1
                //stock--;
                stock.decrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        StockRunnable task=new StockRunnable();


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

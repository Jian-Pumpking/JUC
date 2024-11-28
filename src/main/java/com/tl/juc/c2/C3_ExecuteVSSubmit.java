package com.tl.juc.c2;

import java.util.concurrent.*;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C3_ExecuteVSSubmit {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5); //一个饭店，5张桌子

        // execute方法和submit有什么区别
        // 1.参数
        // execute Runnable.run
        // submit callable
        // 2.返回值
        // execute void
        // submit Future
        // 3. 异常
        // execute 会在子线程中抛出异常，在主线程捕捉不到
        // submit 不会字码抛出异常， 而是会讲一次暂时存起来，等Future.get()方法的时候
        // ，才会抛出, 可以在主线程捕捉， 处理异常更方便

        // 为什么execute也可以执行带返回值的线程
            Future<?> future = threadPool.submit(() -> {
                System.out.println("执行");
                int a = 1 / 0;
            });
            Object o = future.get();


        /*Future<Integer> future = threadPool.submit(() -> {
            System.out.println("执行...");
            return 5;
        });
        Integer integer = future.get();
        System.out.println(integer);*/
    }
}

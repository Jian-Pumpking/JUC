package com.tl.juc.c4;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C4_SharedQueue {
    // 声明队列最大长度
    private int queueSize = 10;

    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);


    public static void main(String[] args) {

        C4_SharedQueue test = new C4_SharedQueue();

        // 消费者持续运行
        Consumer consumer = test.new Consumer();
        consumer.start();


        // 生产10条消息
        for (int i = 0; i <10; i++) {
            // 创建10个生产者线程
            Producter producter = test.new Producter();
            producter.start();
        }

    }

    // 生产者
    class Producter extends Thread {

        @Override
        public void run() {

            // 保证生产者在整个过程中是线程安全的
            synchronized (queue) {

                // 1、 判断当前队列长度是否小于最大长度
                if(queue.size()<queueSize){
                    // 2、如果小于 生产者就可以生产消息了

                    // 2.1 往队列queue添加一条信息
                    queue.add(queue.size()+1);


                    System.out.println("生产者往队列中加入消息，队列当前长度："+queue.size());

                    // 2.2 唤醒消费，有活了
                    queue.notify();

                    try {
                        // 模拟业务处理
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                else{

                    // 3. 如果大于 生产者停止工作， 稍微歇一歇
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        queue.notify();
                    }
                }




            }
        }
    }



    // 消费者
    class Consumer extends Thread {

        @Override
        public void run() {
            // 消费者需要重复的工作
            while (true) {
                // 保证整个消费的过程是线程安全
                synchronized (queue) {

                    // 如果队列为空， 消费者睡眠
                    if (queue.isEmpty()) {
                        System.out.println("当前队列为空...");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            // 一旦出现异常，手动唤醒
                            queue.notify();
                        }
                    }
                    else{
                        // 队列不为空， 消费者需要进行消费

                        // 消费头部的信息
                        Integer value = queue.poll();

                        System.out.println("消费者从队列中消费了信息:"+value+"，队列当前长度："+queue.size());


                        // 唤醒生产者， 可以继续工作了
                        queue.notify();

                        try {
                            // 模拟业务处理
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }
    }
}


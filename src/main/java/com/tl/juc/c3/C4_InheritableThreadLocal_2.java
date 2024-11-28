package com.tl.juc.c3;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C4_InheritableThreadLocal_2 {
    public static void main(String[] args) throws InterruptedException {
        // 替换成 InheritableThreadLocal
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("test");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String value = threadLocal.get();
                System.out.println("value:" + value);
            }
        });
        thread.start();
        Thread.sleep(10000);
    }
}

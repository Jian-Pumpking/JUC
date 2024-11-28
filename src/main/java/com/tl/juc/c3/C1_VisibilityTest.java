package com.tl.juc.c3;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * 可见性
 *
 *
// 对一个变量解锁之前，必须先把此变量同步回主存中。这样解锁后，线程就可以访问到被修改后的值。
// 所以Synchronized锁住的对象，其值具有可见性。
 */
public class C1_VisibilityTest {

    static volatile Boolean always = true;

    public static void main(String[] args) throws InterruptedException {
        // 线程1
        new Thread(() -> {
            //
            while (always) {
               // System.out.println("执行...");
//                synchronized (always) {
//
//                }
            }

        }).start();


        Thread.sleep(2000);

        // 线程2

        always = false;

    }


}

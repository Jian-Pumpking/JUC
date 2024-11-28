package com.tl.juc.c3;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * 有序性（指令重排)
 */
public class C1_SerialTest {
    static   C1_SerialTest serialTest;
    static  volatile   Boolean isInit=false;

    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 1000000; i++) {
                isInit = false;
                serialTest = null;

            // 线程1
            Thread thread1 = new Thread(() -> {
                synchronized (isInit){
                    isInit = true;              // 语句2
                    serialTest = new C1_SerialTest(); //  语句1
                }
            });


            // 线程2
            Thread thread2 = new Thread(() -> {
                synchronized (isInit) {
                    if (isInit) {
                        serialTest.doSomething();
                    }
                }
            });
            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
        }
    }

    private void doSomething() {
        System.out.println("doSomething...");

    }
}

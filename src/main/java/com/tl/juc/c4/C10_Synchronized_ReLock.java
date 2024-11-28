package com.tl.juc.c4;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 *
 * 重入锁
 */
public class C10_Synchronized_ReLock {

    private static final Object objectLockA = new Object();
        public static void main(String[] args){

            new Thread(() -> {
                relock();
            },"a").start();
        }

        private static Integer i=0;

        public static void relock(){

            synchronized (objectLockA){
                if(i==3){
                    return;
                }else{
                    i++;
                    System.out.println(i+"调用");
                    relock();
                }
            }
        }
}

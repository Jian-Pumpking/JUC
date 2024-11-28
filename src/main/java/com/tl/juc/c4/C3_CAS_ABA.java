package com.tl.juc.c4;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C3_CAS_ABA {


    public static void main(String[] args) throws InterruptedException {

        // 张三余额
        AtomicStampedReference<Integer> balance = new AtomicStampedReference(1000,0);

        System.out.println("（A）张三余额:"+ balance.getReference());
        
        // 财务发3000工资
        balance.compareAndSet(balance.getReference(),4000,balance.getStamp(),balance.getStamp()+1);
        System.out.println("（B）财务发3000工资:"+ balance.getReference());



        // 老婆取3000工资
        balance.compareAndSet(balance.getReference(),1000,balance.getStamp(),balance.getStamp()+1);

        System.out.println("（A）老婆取3000工资:"+ balance.getReference());


        // 张三查工资
        if(balance.getReference()>3000){

            System.out.println("张三美滋滋"+ balance.getReference());
        }else{
            if(balance.getStamp()==1){
                System.out.println("张三找财务麻烦:");
            }
            else{
                System.out.println("张三找老婆麻烦:");
            }

        }
    }


}

package com.tl.juc.c3;

import java.util.concurrent.atomic.*;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class C5_Atomic {

    public static void main(String[] args) {
        ia();
    }

    // AtomicInteger
    public  static  void ai(){
        AtomicInteger ai = new AtomicInteger(1);
        // 获取值
        System.out.println("ai.get() = " + ai.get());

        // 增加指定值并且获取
        System.out.println("ai.addAndGet(5) = " + ai.addAndGet(5));
        System.out.println("ai.get() = " + ai.get());
        // 比较并且设置，     1.预期值  2.新值   会将预期值跟当前比较， 如果相同就设置新值，
        // 返回值：是否设置成功
        System.out.println("ai.compareAndSet(ai.get(), 10) = " + ai.compareAndSet(ai.get(), 10));
        System.out.println("ai.get() = " + ai.get());

        // 获取并且递增  先获取没递增的值， 再去递增
        System.out.println("ai.getAndIncrement() = " + ai.getAndIncrement());  //ai.getAndDecrement()
        System.out.println("ai.get() = " + ai.get());
        // 递增并且获取   先递增， 再获取
        System.out.println("ai.incrementAndGet() = " + ai.incrementAndGet());
        System.out.println("ai.get() = " + ai.get());

        // 懒设置  不会保证可见性
        ai.lazySet(8);
        // volatile保证了可见性
        //ai.set();
        System.out.println("ai.lazySet(8)");
        System.out.println("ai.get() = " + ai.get());

        System.out.println("ai.getAndSet(5) = " + ai.getAndSet(5));
        System.out.println("ai.get() = " + ai.get());
    }

    // AtomicInteger
    public  static  void aia() {
        int[] value = new int[]{1, 2};

        AtomicIntegerArray aia = new AtomicIntegerArray(value);

        System.out.println("ai.getAndSet(0, 3)");
        aia.getAndAdd(0, 3);
        System.out.println("aia.get(0) = " + aia.get(0));
        System.out.println("value[0] = " + value[0]);

        aia.compareAndSet(1, 2, 5);
        System.out.println("aia.compareAndSet(1, 2, 5)");
        System.out.println("aia.get(1) = " + aia.get(1));
    }





    public  static  void ar() {

        User user = new User("xushu", 20);
        AtomicReference<User> atomicUserRef = new AtomicReference<User>(user);
        //atomicUserRef.set(user);
        System.out.println("atomicUserRef.get() = " + atomicUserRef.get().toString());

        User updateUser = new User("zhuge", 22);
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println("atomicUserRef.compareAndSet(user, updateUser);");

        System.out.println("atomicUserRef.get() = " + atomicUserRef.get().toString());
    }

    public static void au() {
        // 创建原子更新器,并设置需要更新的对象类和对象的属性
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.
                newUpdater(User.class, "age");
        // 设置xushu的年龄是10岁
        User xushu = new User("xushu", 10);
        // 徐庶长了一岁,但是仍然会输出旧的年龄
        System.out.println(a.getAndIncrement(xushu));
        // 输出xushu现在的年龄
        System.out.println(a.get(xushu));
    }


    public static void ia() {
        // 无参构造函数  从0开始

        LongAdder longAdder = new LongAdder();

        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.longValue());//3

        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 1);//lambda表达式
        longAccumulator.accumulate(1);//1
        longAccumulator.accumulate(3);//4
        longAccumulator.accumulate(3);//7
        System.out.println(longAccumulator.get());
    }

    static class User {
        private String name;
        public volatile int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "name='" + name + ", age=" + age;
        }
    }




}

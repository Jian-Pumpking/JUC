import java.util.concurrent.locks.LockSupport;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class ThreadMethods {

    public static void main(String[] args) {


        //Thread.currentThread()
        // thread1.getName() thread1.setName()
        // run  和start
       // interrupt()：中断线程
        // sleep TimeUnit.Seconds.sleep 与 yield
        // 优先级
        //  join
        // 守护线程 setDaemon();

        class Task1 implements Runnable{
            @Override
            public void run() {
                for (int i = 0;i < 200;i++){
                    System.out.println("A:"+i);
                }
            }
        }
        class Task2 implements Runnable{
            @Override
            public void run() {
                for (int i = 0;i < 10;i++){
                    System.out.println("B:"+i);
                    Thread.yield();
                }
            }
        }
                new Thread(new Task2()).start();
                new Thread(new Task1()).start();

    }

}

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class ThreadExample extends Thread {

    @Override
    public void run() {
        System.out.println("Thread is running.");

        try {
            // 让线程休眠1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread is finished.");
    }

    public static void main(String[] args) {
        ThreadExample thread = new ThreadExample();

        // 启动线程
        thread.start();

        // 获取线程的优先级
        int priority = thread.getPriority();
        System.out.println("Thread priority: " + priority);

        // 设置线程的优先级
        thread.setPriority(Thread.MAX_PRIORITY);

        // 检查线程是否处于活动状态
        boolean isAlive = thread.isAlive();
        System.out.println("Thread is alive: " + isAlive);

        // 等待线程执行完毕
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断线程
        thread.interrupt();

        // 检查线程是否被中断
        boolean isInterrupted = thread.isInterrupted();
        System.out.println("Thread is interrupted: " + isInterrupted);
    }
}
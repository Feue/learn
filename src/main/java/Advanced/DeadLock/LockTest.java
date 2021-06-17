package Advanced.DeadLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三： Lock 锁 --- JDK5.0新增
 *
 * synchronized 和 lock 的异同
 * 同：二者都可以解决线程安全问题
 * 异：synchronized 机制在执行完相应的同步代码之后，自动释放同步监视器
 *    lock 需要手动启动同步（lock()）和结束同步（unlock()）
 *
 * @author Feue
 * @create 2021-05-29 14:52
 */
public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window implements Runnable {

    private int tickets = 100;
    // 1. 实例化 ReentrantLock
    // 参数 fair：true公平，false非公平
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                // 2. 调用锁定方法：lock()
                lock.lock();

                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": 售票，票号：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            } finally {
                // 3. 调用解锁方法：unlock()
                lock.unlock();
            }
        }
    }
}
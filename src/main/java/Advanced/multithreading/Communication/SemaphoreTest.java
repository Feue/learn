package Advanced.multithreading.Communication;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Feue
 * @create 2021-12-16 12:48
 */
public class SemaphoreTest {
    private volatile static Semaphore sem = new Semaphore(3);
    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                sem.acquire();
                System.out.println("当前线程是" + Thread.currentThread().getName()
                        + ", 还剩" + sem.availablePermits() + "个资源, 还有"
                        + sem.getQueueLength() + "个线程在等待");
                Thread.sleep(500+random.nextInt(500));
                System.out.println("线程"+Thread.currentThread().getName()+"释放了资源");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}

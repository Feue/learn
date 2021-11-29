package Advanced.multithreading;

import java.util.concurrent.*;

/**
 * @author Feue
 * @create 2021-05-30 15:06
 *
 * 创建线程的方式四：使用线程池
 *
 * 好处：
 *     1. 提高响应速度（减少了创建线程的时间）
 *     2. 降低资源消耗（重复利用线程池中的线程，不需要重复创建线程）
 *     3. 便于线程管理
 *         corePoolSize：线程池的大小
 *         maximumPoolSize：最大线程数
 *         keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 * Executors：工具类、线程池的工厂类，用于创建并返回不同类型的线程池
 *     Executors.newCachedThreadPool()：创建一个可根据需要创建新线程的线程池
 *     Executors.newFixedThreadPool(n)：创建一个可重用固定线程数的线程池
 *     Executors.newSingleThreadPool()：创建一个只有一个线程的线程池
 *     Executors.newScheduledExecutorService(n)：创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) service;
        // 设置线程池属性
//        executor.setCorePoolSize(15);
//        executor.setKeepAliveTime();

        // 2. 执行指定的线程的操作。需要提供实现 Runnable 接口或 Callable 接口的实现类的对象。
        service.execute(new NumThread()); // 适合使用于 Runnable
        service.execute(new NumThread1()); // 适合使用于 Runnable
//        service.submit(Callable callable); // 适合使用于 Callable
        // 3. 关闭线程池
        service.shutdown();

        // 推荐使用 ThreadPoolExecutor 创建线程池。
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, blockingQueue);
        executor1.submit(new NumThread());
        executor1.submit(new NumThread1());

        executor1.shutdown();
    }
}

class NumThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i%2 == 0) {
                System.out.println(Thread.currentThread().getName()+": "+i);
            }
        }
    }
}

class NumThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i%2 != 0) {
                System.out.println(Thread.currentThread().getName()+": "+i);
            }
        }
    }
}

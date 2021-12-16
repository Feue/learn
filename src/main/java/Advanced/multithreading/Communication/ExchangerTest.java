package Advanced.multithreading.Communication;

import java.util.concurrent.Exchanger;

/**
 * @author Feue
 * @create 2021-12-16 14:22
 */
public class ExchangerTest {
    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(ExchangerTest::run, "线程A").start();

        System.out.println("此时线程A阻塞，在等待线程B的数据");
        Thread.sleep(1000);

        new Thread(ExchangerTest::run, "线程B").start();
    }

    public static void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(threadName+"得到了另一个线程的数据: "
                    +exchanger.exchange("这是来自"+threadName+"的数据"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

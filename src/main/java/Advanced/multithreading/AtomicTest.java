package Advanced.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Feue
 * @create 2021-12-06 12:34
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger atomic = new AtomicInteger();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ",oldVal:" + atomic.getAndIncrement() + ",newVal:" + atomic.get());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ",oldVal:" + atomic.getAndIncrement() + ",newVal:" + atomic.get());
            }
        }).start();
    }
}

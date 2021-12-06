package Advanced.multithreading;

import java.util.Date;

/**
 * @author Feue
 * @create 2021-12-03 14:41
 *
 * Class 对象被锁定不影响该 class 的实例对象锁被获取
 */
public class SynchronizedTest {
    public static synchronized void fun1() {
        System.out.println("static method");
    }

    public synchronized void fun2() {
        System.out.println("normal method");
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();

        new Thread(() -> {
            SynchronizedTest.fun1();
            System.out.println(Thread.currentThread().getName()+new Date());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            test.fun2();
            System.out.println(Thread.currentThread().getName()+new Date());
        }).start();
    }
}

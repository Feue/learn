package Advanced.multithreading.practice;

/**
 * @author Feue
 * @create 2021-11-29 10:24
 *
 * 两个线程交替打印 a 和 b
 * a b a b a b ...
 */
public class AAndB {
    private static final Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (obj) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obj.notify();
                    System.out.print("a ");
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (obj) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obj.notify();
                    System.out.print("b ");
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

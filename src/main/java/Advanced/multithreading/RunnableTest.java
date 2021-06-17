package Advanced.multithreading;

/**
 * Thread实现方式一：实现Runnable接口
 * 1)定义子类，实现 Runnable 接口。
 * 2)子类中实现 Runnable 接口中的 run 方法。
 * 3)通过 Thread 类含参构造器创建线程对象。
 * 4)将 Runnable 接口的子类对象作为实际参数传递给 Thread 类的构造器中 。
 * 5)调用 Thread 类的 start 方法：开启线程调用 Runnable 子类接口的 run 方法。
 */
class MRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i += 2) {
            System.out.println(Thread.currentThread().getName()+": "+i);
        }
    }
}

public class RunnableTest {
    public static void main(String[] args) {
        MRunnable mRunnable = new MRunnable();

        Thread t1 = new Thread(mRunnable);
        Thread t2 = new Thread(mRunnable);

        t1.start();
        t2.start();
    }
}

package Advanced.multithreading.Communication;

/**
 * 线程通信的例子：使用两个线程打印 1-100 。线程 1，线程 2 交替打印。
 *
 * 1.wait(), notify(), notifyAll(), 三个方法必须在同步代码块或同步方法中使用。
 * 2.这三个方法的调用者必须是是同步代码块或同步方法中的同步监视器。
 *   否则，会出现 IllegalMonitorStateException 异常。
 * 3.这三个方法是定义在 Object 类中的。
 *
 * sleep() 和 wait() 的异同：
 * 同：一旦执行方法，都可以使当前线程进入阻塞状态
 * 异：1）两个方法声明的位置不同： Thread 类中声明sleep()， Object 类中声明 wait()。
 *    2）使用的要求不同：sleep()可以在任意需要的场景下使用，wait()必须在同步代码块或同步方法中调用。
 *    3）是否释放同步监视器：如果两个方法都在同步代码块或同步方法中使用，sleep()不释放同步监视器，wait()会释放同步监视器。
 *
 * @author Feue
 * @create 2021-05-29 15:33
 */
class Number implements Runnable {
    private int number = 1;
    private Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                // notify()唤醒一个线程，如果有多个线程被 wait 就唤醒优先级最高的一个线程，notifyAll() 唤醒其他所有被 wait 的线程
                obj.notify();

                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                } else {
                    break;
                }
                try {
                    // 使得调用 wait() 方法的线程进入阻塞状态
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class NumPrintTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

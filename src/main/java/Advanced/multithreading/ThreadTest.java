package Advanced.multithreading;

/**
 * Thread实现方式一：继承Thread类
 * 1)定义子类继承Thread类。
 * 2)子类重写run方法。
 * 3)创建Thread子类对象，即创建了线程对象。
 * 4)调用线程对象start方法：启动线程，调用run方法
 */
class MThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(getName()+": "+i);
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        MThread t1 = new MThread();
        MThread t2 = new MThread();

        t1.start();
        t2.start();
    }
}

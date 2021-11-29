package Advanced.multithreading;

/**
 * @author Feue
 * @create 2021-11-29 12:35
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        Thread test = new Thread(() -> {
            System.out.println("test当前线程组名称:"+Thread.currentThread().getThreadGroup().getName());
            System.out.println("test线程名称:"+Thread.currentThread().getName());
        });
        test.start();

        System.out.println("main方法所在线程组名称:"+Thread.currentThread().getThreadGroup().getName());
        System.out.println("main方法所在线程名称:"+Thread.currentThread().getName());
    }
}

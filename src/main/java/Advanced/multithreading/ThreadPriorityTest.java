package Advanced.multithreading;

import java.util.stream.IntStream;

/**
 * @author Feue
 * @create 2021-11-29 12:48
 */
public class ThreadPriorityTest {
    public static void main(String[] args) {
        IntStream.range(1, 10).forEach(i -> {
            T1 t1 = new T1();
            t1.setPriority(i);
            t1.start();
        });

        ThreadGroup group = new ThreadGroup("group");
        group.setMaxPriority(5);
        Thread thread = new Thread(group, "thread");
        thread.setPriority(8);
        System.out.println("线程组的优先级"+group.getMaxPriority());
        System.out.println("线程的优先级"+thread.getPriority());
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println(String.format("当前执行的线程是：%s，优先级：%d",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getPriority()));
        }
    }
}

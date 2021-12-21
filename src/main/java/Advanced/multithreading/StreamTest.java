package Advanced.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Feue
 * @create 2021-12-21 13:36
 */
public class StreamTest {
    public static void main(String[] args) {
        System.out.printf("本计算机核心数: %d%n", Runtime.getRuntime().availableProcessors());
        Random rand = new Random();
        List<Integer> list = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            list.add(rand.nextInt(100));
        }

        long preTime = getCurrentTime();
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);
        System.out.printf("单线程计算耗时: %d%n", getCurrentTime()-preTime);

        preTime = getCurrentTime();
        list.stream().parallel().reduce(Integer::sum).ifPresent(System.out::println);
        System.out.printf("多线程计算耗时: %d%n", getCurrentTime()-preTime);
    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }
}

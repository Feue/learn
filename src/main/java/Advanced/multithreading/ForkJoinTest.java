package Advanced.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author Feue
 * @create 2021-12-21 11:29
 */
public class ForkJoinTest {
    static class Fibonacci extends RecursiveTask<Integer> {
        private int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            } else {
                Fibonacci f1 = new Fibonacci(n - 1);
                f1.fork();
                Fibonacci f2 = new Fibonacci(n - 2);
                f2.fork();
                return f1.join()+f2.join();
            }
        }
    }

    public static void forkJoinTest() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("CPU核心数: "+Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        Fibonacci fibonacci = new Fibonacci(40);
        Future<Integer> future = forkJoinPool.submit(fibonacci);
        System.out.println("计算结果: "+future.get());
        long end = System.currentTimeMillis();
        System.out.printf("耗时: %d millis%n", end-start);
    }

    public static void recursionTest() {
        long start = System.currentTimeMillis();
        int ans = fibonacci(40);
        System.out.println("计算结果: "+ans);
        long end = System.currentTimeMillis();
        System.out.printf("耗时: %d millis%n", end-start);
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1)+fibonacci(n - 2);
    }

    public static void loopTest() {
        long start = System.currentTimeMillis();
        int pre = 1, ans = 1;
        for (int i = 3; i <= 40; i++) {
            ans += pre;
            pre = ans-pre;
        }
        System.out.println("计算结果: "+ans);
        long end = System.currentTimeMillis();
        System.out.printf("耗时: %d millis%n", end-start);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Fork Join-------------------");
        forkJoinTest();
        System.out.println("Recursion-------------------");
        recursionTest();
        System.out.println("Loop-------------------");
        loopTest();
    }
}

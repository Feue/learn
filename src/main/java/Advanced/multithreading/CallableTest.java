package Advanced.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现多线程方式三：实现 Callable 接口。
 *
 * 实现 Callable 接口相对于实现 Runnable 接口的强大之处：
 * 1. call()可以有返回值
 * 2. call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3. Callable 支持泛型
 *
 * @author Feue
 * @create 2021-05-29 21:35
 */
// 1.创建 Callable 的实现类
class ThreadNum implements Callable<Integer> {
    // 2.实现 Callable 接口的 call 方法，将线程需要进行的操作声明在 call() 中
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100 ; i++) {
            if (i%2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableTest {
    public static void main(String[] args) {
        // 3.创建 Callable 实现类的对象
        ThreadNum threadNum = new ThreadNum();
        // 4.创建 FutureTask 对象，将 Callable 实现类的对象传递给 FutureTask构造器
        FutureTask<Integer> futureTask = new FutureTask<>(threadNum);
        // 5.创建 Thread 对象，将 FutureTask类的对象传递给 Thread 构造器
        new Thread(futureTask).start();
        // 6.调用 FutureTask 对象的 get()，获取 Callable 中 call 方法的返回值
        try {
            // get()返回值即为 FutureTask 构造器参数的 Callable 实现类重写的 call() 返回值
            Integer sum = futureTask.get();
            System.out.println("总和为: "+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

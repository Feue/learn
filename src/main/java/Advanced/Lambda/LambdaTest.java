package Advanced.Lambda;

import org.junit.Test;

import java.util.Comparator;

/**
 * Lambda 表达式示例
 *
 * @author Feue
 * @create 2021-06-21 15:03
 */
public class LambdaTest {
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1");
            }
        };
        r1.run();
        System.out.println("********************************");
        Runnable r2 = () -> System.out.println("r2");
        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> comp1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comp1.compare(12, 20));
        System.out.println("********************************");
        // Lambda 表达式
        Comparator<Integer> comp2 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(comp2.compare(21, 13));
        System.out.println("********************************");
        // 方法引用
        Comparator<Integer> comp3 = Integer::compare;
        System.out.println(comp3.compare(10, 10));
    }
}

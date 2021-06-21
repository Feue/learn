package Advanced.StreamAPI;

import Employee.Employee;
import Employee.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1. Stream 关注的是对数据的运算，与 CPU 打交道
 *    集合关注的是数据的存储，与内存打交道
 * 2. (1) Stream 自己不会存储元素。
 *    (2) Stream 不会改变源对象。相反，他们会返回一个持有结果的新 Stream 。
 *    (3)Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 * 3. Stream 的执行流程
 *    (1) Stream 的实例化
 *    (2) 一系列中间操作（过滤、映射、...）
 *    (3) 终止操作
 *
 * @author Feue
 * @create 2021-06-21 20:41
 */
public class StreamAPITest {
    // 1. 创建 Stream
    @Test
    public void test1() {
        // 创建 Stream 方式一：通过集合
        List<Employee> employees = EmployeeData.getEmployees();
        // default Stream<E> stream() : 返回一个顺序流
        Stream<Employee> stream = employees.stream();
        // default Stream<E> parallelStream() : 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();

        // 创建 Stream 方式二：通过数组
        int[] arr1 = new int[] {1, 2, 3, 4, 5, 6};
        // static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream intStream = Arrays.stream(arr1);
        Employee e1 = new Employee(1001, "Bob");
        Employee e2 = new Employee(1002, "Tom");
        Employee[] arr2 = new Employee[] {e1, e2};
        Stream<Employee> employeeStream = Arrays.stream(arr2);

        // 创建 Stream 方式三：通过 Stream 的 of()
        Stream<Integer> integerStream = Stream.of(1, 2, 3);

        // 创建 Stream 方式四：创建无限流
        // 迭代
        // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out::println);
        // 生成
        // public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    // 2. 中间操作
    @Test
    public void test2() {
        // (1) 筛选与切片
        List<Employee> list = EmployeeData.getEmployees();
        // filter(Predicate p) 接收 Lambda 从流中排除某些元素
        list.stream().filter(e -> e.getSalary() >= 6000).forEach(System.out::println);
        System.out.println();
        //limit(long maxSize) 截断流，使其元素不超过给定数量
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();
        //skip(long n) 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        list.stream().skip(3).forEach(System.out::println);
        System.out.println();
        //distinct() 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        list.add(new Employee(1009, "Tom", 18, 10000));
        list.add(new Employee(1010, "Tom", 18, 10000));
        list.add(new Employee(1009, "Tom", 18, 10000));
        list.add(new Employee(1009, "Tom", 18, 10000));
        list.stream().distinct().forEach(System.out::println);
        System.out.println();

        // (2) 映射
        // map(Function f) 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> strs = Arrays.asList("aa", "bb", "cc");
        strs.stream().map(s -> s.toUpperCase()).forEach(System.out::println);
        System.out.println();
        // 练习1：获取员工姓名长度大于三的员工姓名
        list.stream().map(e -> e.getName()).filter(name -> name.length()>3).forEach(System.out::println);
        System.out.println();
        // 练习2：
        Stream<Stream<Character>> streamStream = strs.stream().map(StreamAPITest::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });
        System.out.println();
        // flatMap(Function f) 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        strs.stream().flatMap(StreamAPITest::fromStringToStream).forEach(System.out::println);
    }

    public static Stream<Character> fromStringToStream(String str) {
        List<Character> list = new ArrayList<>();
        for (Character c: str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
}

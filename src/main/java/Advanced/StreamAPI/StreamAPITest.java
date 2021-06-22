package Advanced.StreamAPI;

import Employee.Employee;
import Employee.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
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
        System.out.println("*********************************");

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
        System.out.println("*********************************");

        // (3)排序
        // sorted() 自然排序
        List<Integer> list1 = Arrays.asList(12, 9, 20, 5);
        list1.stream().sorted().forEach(System.out::println);
        System.out.println();
        // sorted(Comparator com) 定制排序
        list.stream().sorted((e1, e2) -> {
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            if (ageValue != 0) {
                return ageValue;
            } else {
                return -Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);
    }

    public static Stream<Character> fromStringToStream(String str) {
        List<Character> list = new ArrayList<>();
        for (Character c: str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    // 3. 终止操作
    @Test
    public void test3() {
        // (1)匹配与查找
        List<Employee> list = EmployeeData.getEmployees();
        // allMatch(Predicate p) 检查是否匹配所有元素
        // 练习：是否所有员工的年龄都大于18
        boolean allMatch = list.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);
        // anyMatch(Predicate p) 检查是否匹配最少一个元素
        // 练习：是否有员工的工资大于10000
        boolean anyMatch = list.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);
        // noneMatch(Predicate p) 检查是否没有匹配的元素
        // 练习：是否存在员工姓“雷”
        boolean noneMatch = list.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);
        // findFirst(Predicate p) 返回第一个元素
        System.out.println(list.stream().findFirst());
        // findAny(Predicate p) 返回当前流中的任意元素
        System.out.println(list.stream().findAny());
        // count() 返回流中元素总数
        System.out.println(list.stream().count());
        // max(Comparator c) 返回流中最大值
        Stream<Double> salaryStream = list.stream().map(Employee::getSalary);
        Optional<Double> maxSalary = salaryStream.max(Double::compare);
        System.out.println(maxSalary);
        // min(Comparator c) 返回流中最小值
        System.out.println(list.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println();
        // forEach(Consumer c) 内部迭代
        list.stream().forEach(System.out::println);
        System.out.println("*************************************");

        // (2)规约
        // reduce(T identity, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 T
        // 练习1：计算1-10的自然数的和
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list1.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        // reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
        // 练习2：计算公司所有员工工资的总和
        Optional<Double> sum2 = list.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(sum2);
        System.out.println("***********************************");

        // (3)收集
        // collect(Collector c) 将流转换为其他形式。接收一个 Collector 接口的实现，用于给 Stream 中元素做汇总的方法
        // 练习：查找工资大于 6000 的员工，结果返回一个 List 或 Set
        List<Employee> employeeList = list.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);
        System.out.println();
        Set<Employee> employeeSet = list.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }
}

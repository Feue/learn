package Foundation.Collection;

import java.util.*;

/**
 * @author Feue
 * @create 2021-06-03 12:15
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(123);
        list.add("456");
        list.add(6);
        list.add(null);
        list.add(10);

        // 抛异常: IndexOutOfBoundsException: Source does not fit in dest
//        List dest = new ArrayList();
//        Collections.copy(dest, list);

        // 正确方法
        List dest = new ArrayList(Arrays.asList(new Object[list.size()]));
        Collections.copy(dest, list);

        System.out.println(dest);

        /*
        Collections 类提供了多个 SynchronizedXxx() 方法，
        该方法可使将指定集合包装成线程同步的集合，
        从而可以解决多线程并发访问集合时的线程安全问题。
         */
        // 返回的 list1 即为线程安全的 List
        List list1 = Collections.synchronizedList(list);
//        System.out.println(list1);
        list1.forEach(System.out::println);

        //
        Map<String, String> map = new HashMap<>();
        map.put("str1", "000");
        map.put("str2", "000");
        map.put("str3", "000");
        map.put("str4", "000");
        Map<String, String> map1 = Collections.synchronizedMap(map);
        map1.forEach((k, v)->{
            System.out.println(k+" : "+v);
        });
    }
}

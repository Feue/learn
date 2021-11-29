package Foundation.Collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Arrays.asList()
 * 返回由指定数组支持的固定大小的列表。此方法作为基于数组和基于集合的API之间的桥梁，
 * Collection.toArray()结合使用。返回的List是可序列化并实现RandomAccess接口。
 */
public class Arrays_asList {
    public static void main(String[] args) {
        String[] myArray = {"Apple", "Banana", "Orange"};
        List<String> myList = Arrays.asList(myArray);
        // 上面两个语句等价于下面一条语句
        //List<String> myList = Arrays.asList("Apple","Banana", "Orange");
        System.out.println(myList);

        // Arrays.asList()是泛型方法，传入的对象必须是对象数组。
        int[] myArray2 = {1, 2, 3};
        //Integer[] myArray2 = {1, 2, 3}; // 使用包装类型数组就可以解决这个问题
        List myList2 = Arrays.asList(myArray2);
        System.out.println(myList2.size()); // 1
        System.out.println(myList2.get(0)); // 数组地址值
        //System.out.println(myList2.get(1)); // 报错：ArrayIndexOutOfBoundsException
        int[] array = (int[]) myList2.get(0);
        System.out.println(array[0]); // 1

        // 使用集合的修改方法:add()、remove()、clear()会抛出异常。
        List<Integer> myList3 = Arrays.asList(1, 2, 3);
        //myList3.add(4);//运行时报错：UnsupportedOperationException
        //myList3.remove(1);//运行时报错：UnsupportedOperationException
        //myList3.clear();//运行时报错：UnsupportedOperationException

        // Arrays.asList() 方法返回的并不是 java.util.ArrayList ，而是 java.util.Arrays 的一个内部类,
        // 这个内部类并没有实现集合的修改方法或者说并没有重写这些方法。
        List<Integer> myList4 = Arrays.asList(1, 2, 3);
        System.out.println(myList4.getClass()); // class java.util.Arrays$ArrayList

        // 如何正确将数组转化为ArrayList
        // 1. 自己实现
        Integer[] myArray5 = { 1, 2, 3 };
        System.out.println(arrayToList(myArray5).getClass()); // class java.util.ArrayList

        // 2.最简便的方法(推荐)
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println(list);

        // 3.使用 Java8 的Stream(推荐)
        Integer[] myArray6 = { 1, 2, 3 };
        List<Integer> myList6 = Arrays.stream(myArray6).collect(Collectors.toList());
        System.out.println("myList6: "+myList6);
        // 基本类型也可以实现转换（依赖boxed的装箱操作）
        int[] myArray7 = { 1, 2, 3 };
        List<Integer> myList7 = Arrays.stream(myArray7).boxed().collect(Collectors.toList());
        System.out.println("myList7: "+myList7);

        // 4. 使用 Guava(推荐)
        String[] aStringArray = {"a", "b", "c"};
        List<String> anotherListOrCollection = new ArrayList<String>(Arrays.asList(aStringArray));
        // 对于不可变集合，你可以使用ImmutableList类及其of()与copyOf()工厂方法：（参数不能为空）
//        List<String> il = ImmutableList.of("string", "elements");  // from varargs
        List<String> il = ImmutableList.copyOf(aStringArray);      // from array
        System.out.println("il: "+il);
        // 对于可变集合，你可以使用Lists类及其newArrayList()工厂方法：
        List<String> l1 = Lists.newArrayList(anotherListOrCollection);    // from collection
        List<String> l2 = Lists.newArrayList(aStringArray);               // from array
        List<String> l3 = Lists.newArrayList("or", "string", "elements"); // from varargs
        System.out.println("l1: "+l1);
        System.out.println("l2: "+l2);
        System.out.println("l3: "+l3);

        // 5. 使用 Apache Commons Collections
        String[] str = {"123", "456"};
        List<String> list2 = new ArrayList<>();
        CollectionUtils.addAll(list2, str);
        System.out.println("list2: "+list2);

        // 6. 使用 Java9 的 List.of()方法
//        Integer[] array2 = {1, 2, 3};
//        List<Integer> list3 = List.of(array);
//        System.out.println(list); /* [1, 2, 3] */
//        /* 不支持基本数据类型 */

    }

    // JDK1.5+
    // 正确将数组转化为ArrayList
    static <T> List<T> arrayToList(final T[] array) {
        final List<T> l = new ArrayList<T>(array.length);
        for (final T s : array) {
            l.add(s);
        }
        return l;
    }

}

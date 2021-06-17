package Foundation.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Collections_toArray {
    public static void main(String[] args) {
        // Collections.toArray是一个泛型方法：<T> T[] toArray(T[] a);
        // 如果toArray方法中没有传递任何参数的话返回的是Object类型数组。
        String [] s= new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(s);
        Collections.reverse(list);
        s = list.toArray(new String[0]); // 没有指定类型的话会报错
        // 由于JVM优化，new String[0]作为Collection.toArray()方法的参数现在使用更好，
        // new String[0]就是起一个模板的作用，指定了返回数组的类型，0是为了节省空间，因为它只是为了说明返回的类型。
        System.out.println(Arrays.toString(s));

        // 不要在 foreach 循环里进行元素的 remove/add 操作
        // 如果要进行remove操作，可以调用迭代器的 remove 方法而不是集合类的 remove 方法。
        // 因为如果列表在任何时间从结构上修改创建迭代器之后，以任何方式除非通过迭代器自身 remove/add 方法，
        // 迭代器都将抛出一个 ConcurrentModificationException ,这就是单线程状态下产生的 fail-fast 机制。
        // Java8开始，可以使用Collection#removeIf()方法删除满足特定条件的元素,如
        List<Integer> list2 = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            list2.add(i);
        }
        list2.removeIf(filter -> filter % 2 == 0); /* 删除list中的所有偶数 */
        System.out.println("list2: "+list2); /* [1, 3, 5, 7, 9] */
        // java.util 包下面的所有的集合类都是 fail-fast 的，而 java.util.concurrent 包下面的所有的类都是fail-safe的。

    }
}

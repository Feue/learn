package Foundation.Generics;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * generics
 * 泛型
 */
public class generics {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();

        list.add(12);
        // 这里直接添加会报错
        //list.add("a");
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        // 但是通过反射添加，是可以的
        add.invoke(list, "kl");

        System.out.println(list);

        // 1.如何实例化泛型类：
        Generic<Integer> genericInteger = new Generic<Integer>(123456);
        // 3.使用泛型方法
        // 创建不同类型数组： Integer, Double 和 Character
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = { "Hello", "World" };
        System.out.print("intArray: ");
        printArray(intArray);
        System.out.print("stringArray: ");
        printArray(stringArray);
    }

    // 泛型一般有三种使用方式:泛型类、泛型接口、泛型方法。
    // 1. 泛型类：
    // 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
    //在实例化泛型类时，必须指定T的具体类型
    static class Generic<T>{

        private T key;

        public Generic(T key) {
            this.key = key;
        }

        public T getKey(){
            return key;
        }
    }

    // 2.泛型接口：
    interface Generator<T> {
        public T method();
    }
    // 实现泛型接口，不指定类型
    static class GeneratorImpl<T> implements Generator<T>{
        @Override
        public T method() {
            return null;
        }
    }
    // 实现泛型接口，指定类型
    static class GeneratorImpl2<T> implements Generator<String>{
        @Override
        public String method() {
            return "hello";
        }
    }

    // 3.泛型方法
    public static < E > void printArray( E[] inputArray )
    {
        for ( E element : inputArray ){
            System.out.printf( "%s ", element );
        }
        System.out.println();
    }
}

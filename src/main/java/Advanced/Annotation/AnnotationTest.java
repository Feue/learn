package Advanced.Annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * 注解的使用
 *
 * 1. 理解 Annotation：
 *     1) JDK 5.0 新增的功能
 *     2) Annotation 其实就是代码中的特殊标记，这些标记可以在编译、类加载、运行时被读取，并执行相应的处理
 *       通过使用 Annotation ，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。
 *     3) 在 JavaSE 中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。
 *        在 JavaEE/Android 中，注解占据了重要角色，例如用来配置应用程序的任何切面，
 *        代替 JavaEE 旧版中所遗留的繁荣代码和 XML 配置等。
 *
 * 2. Annotation 的使用
 *     1) 生成文档相关的注解
 *     2) 在编译时进行格式检查(JDK 内置的三个基本注解)
 *         Override: 限定重写父类方法,该注解只能 用于方法
 *         Deprecated: 用于表示所修饰的元素类,方法等已过时。通常是因为所修饰的结构危险或存在更好的选择
 *         SuppressWarnings: 抑制编译器警告
 *     3) 跟踪代码依赖性，实现替代配置文件功能
 *
 * 3. 自定义注解
 *     1) 注解声明为 @interface
 *     2) 内部定义成员，通常使用 value 表示
 *     3) 可以使用 default 指定成员的默认值
 *     4) 如果自定义注解没有成员，表明是一个标识作用
 *   如果注解有成员，在使用时需要指定成员的值。
 *   自定义注解必须配上注解的信息处理流程(使用反射)才有意义。
 *   自定义注解通常都会指明两个元注解: Retention/Target
 *
 * 4. JDK 提供的 4 中元注解(对现有的注解进行解释说明的注解)
 *     Retention: 指定所修饰的 Annotation 的生命周期: SOURCE/CLASS(default)/RUNTIME
 *         只有声明为 RUNTIME 的才能通过反射获取。
 *     Target: 用于指定 Annotation 能够修饰哪些程序元素。
 *     Documented: 用于指定该元注解修饰的 Annotation 类将被 javadoc 工具提取成文档。
 *     Inherited: 被它修饰的 Annotation 将具有继承性
 *
 * 5. 通过反射获取注解信息
 *
 * 6. JDK 8 中注解的新特性: 可重复注解、类型注解
 *     6.1 可重复注解:
 *         1) 使用 Repeatable 元注解
 *         2) MyAnnotation
 *
 *     6.2 类型注解:
 *
 * @author Feue
 * @create 2021-06-02 19:35
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Person student = new Student();
        student.walk();

        @SuppressWarnings("unused")
        int num = 0;
    }

    @Test
    public void testGetAnnotation() {
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation: annotations) {
            System.out.println(annotation);
        }
    }
}

@MyAnnotation(value = "Jack")
@MyAnnotation(value = "0.0")
class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk() {
        System.out.println("人走路");
    }
}

interface Info {
    void show();
}

class Student extends Person implements Info {
    @Override
    public void walk() {
        System.out.println("学生走路");
    }

    @Override
    public void show() {

    }
}

class Generic<@MyAnnotation T> {
    public void show() throws @MyAnnotation Exception{
        ArrayList<@MyAnnotation String> list = new ArrayList<>();

        int num = (@MyAnnotation int) 10L;
    }
}

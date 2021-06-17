package Advanced.Reflection;

import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创建运行时类的对象
 *
 * @author Feue
 * @create 2021-06-15 21:12
 */
public class NewInstanceTest {
    @Test
    public void test1() throws InstantiationException, IllegalAccessException {
        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();
        // 要求调用此方法的运行时类（此处为 Person 类）必须具有有访问权限的空参构造器
        System.out.println(person);
    }

    @Test
    public void test2() { // 反射的动态性
        int num = new Random().nextInt(3); // 0 1 2
        System.out.println("num = "+num);
        String classPath = "";
        switch (num) {
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "Advanced.Reflection.Person";
                break;
        }
        try {
            Object obj = getInstance(classPath);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个指定类的对象
     * @param classPath 指定类的全类名
     * @return 创建的对象
     * @throws Exception
     */
    public Object getInstance(String classPath) throws Exception {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}

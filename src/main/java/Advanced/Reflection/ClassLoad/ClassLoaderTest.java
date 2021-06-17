package Advanced.Reflection.ClassLoad;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Feue
 * @create 2021-06-15 19:31
 */
public class ClassLoaderTest {
    @Test
    public void test1() {
        // 对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader1);
        // 调用系统类加载器的 getParent()：获取扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
        // 调用扩展类加载器的 getParent()：无法获取引导类加载器
        // 引导类加载器主要负责加载 Java 的核心类库，无法加载自定义类
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);

        ClassLoader classLoader4 = String.class.getClassLoader();
        System.out.println(classLoader4);
    }
    /*
    Properties：用来读取配置文件
     */
    @Test
    public void test2() throws IOException {
        Properties props = new Properties();
        // 方式一：此时的文件默认在当前 module 下
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
//        props.load(fis);

        // 方式二：使用 ClassLoader
        // 默认在当前 module 下的 src 中识别
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
//        System.out.println(is);
        props.load(is);

        String name = props.getProperty("name");
        String password = props.getProperty("password");
        System.out.println("name = "+name+" , password = "+password);
    }
}

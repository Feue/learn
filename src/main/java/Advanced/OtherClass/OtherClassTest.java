package Advanced.OtherClass;

import org.junit.Test;

/**
 * 一些常用类的使用
 * 1. System
 * 2. Math
 * 3. BigInteger 和 BigDecimal
 *
 * @author Feue
 * @create 2021-06-02 16:08
 */
public class OtherClassTest {
    @Test
    public void test1() {
        String javaVersion = System.getProperty("java.version");
        System.out.println("Java的version："+javaVersion);
        String javaHome = System.getProperty("java.home");
        System.out.println("Java的home："+javaHome);
        String osName = System.getProperty("os.name");
        System.out.println("os的name："+osName);
        String osVersion = System.getProperty("os.version");
        System.out.println("os的version："+osVersion);
        String userName = System.getProperty("user.name");
        System.out.println("user的name："+userName);
        String userHome = System.getProperty("user.home");
        System.out.println("user的home："+userHome);
        String userDir = System.getProperty("user.dir");
        System.out.println("user的dir："+userDir);
    }
}

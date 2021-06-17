package JVM.JMM;

/**
 * String.intern()返回引用测试
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // intern方法...(待了解)
        String s1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s1.intern() == s1);
        // "Java"这个字符串在加载sun.misc.Version时引入常量池
        String s2 = new StringBuilder("Ja").append("va").toString();
        System.out.println(s2.intern() == s2);
    }
}

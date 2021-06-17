package JVM.GC;

/**
 * testGC()方法执行后，objA和objB会被GC吗？
 * VM Options: -XX:+PrintGCDetails （打印GC日志）
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024*1024;

    // 该成员属性的唯一意义是占点内存，以便在GC日志中看清楚是否有回收过
    private byte[] bigSize = new byte[2*_1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // 假设在这发生GC，objA和objB是否能被回收？
        // 这两个对象已经不可能再被访问，但是因为它们互相引用着对方，
        // 导致它们的引用计数器都不为零，引用计数器算法就无法回收它们。
        // Java虚拟机并不是通过引用计数器算法判断对象是否存活的。
        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}

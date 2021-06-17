package JVM.GC;

/**
 * 长期存活的对象进入老年代
 * VM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 * -XX:MaxTenuringThreshold 参数设置晋升老年代的年龄阈值
 * 虚拟机为每个对象定义了一个对象年龄(Age)计数器，存储在对象头中。对象通常在 Eden 区中诞生，
 * 如果经过第一次 Minor GC 后仍然存活，并且能被 Survivor 容纳的话，
 * 该对象会被移动到 Survivor 空间中，并且将其对象年龄设置为 1 岁。
 */
public class TestTenuringThreshold {
    private static final int _1MB = 1024*1024;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        /*
         * 虚拟机为每个对象定义了一个对象年龄(Age)计数器，存储在对象头中。对象通常在 Eden 区中诞生，
         * 如果经过第一次 Minor GC 后仍然存活，并且能被 Survivor 容纳的话，
         * 该对象会被移动到 Survivor 空间中，并且将其对象年龄设置为 1 岁。
         * 对象在 Survivor 区中没熬过一次 Minor GC ，年龄就增加 1 岁，
         * 当它的年龄增加到一定程度，就会被晋升到老年代中。
         */
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB/4]; // 什么时候进入老年代取决于 XX:MaxTenuringThreshold 设置

        allocation2 = new byte[4*_1MB];
        allocation3 = new byte[4*_1MB];
        allocation3 = null;
        allocation3 = new byte[4*_1MB];
    }
}

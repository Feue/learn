package JVM.GC;

/**
 * 大对象直接进入老年代
 * VM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728 指定大于该设置值的对象直接在老年代分配
 * 该参数只对 Serial 和 ParNew 两款新生代收集器有效， HotSpot 的其他新生代收集器，如 Parallel Scavenge
 * 并不支持这个参数。如果必须使用此参数进行调优，可考虑 ParNew 加 CMS 的收集器组合。
 */
public class TestPretenureSizeThreshold {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4*_1MB];
        // Eden 空间几乎没有被使用，而老年代的 10MB 空间被使用了 40% ，
        // 也就是 4MB 的 allocation 对象直接就分配在老年代中。
    }
}

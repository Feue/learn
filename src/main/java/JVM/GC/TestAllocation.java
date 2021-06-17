package JVM.GC;

/**
 * 新生代 Minor GC
 * VM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class TestAllocation {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
        // 此时会发生一次 Minor GC ，原因是为 allocation4 分配内存时，发现 Eden 已经被占用了 6MB ，
        // 剩余空间已不足以分配 allocation4 所需的 4MB 内存，因此发生 Minor GC 。
        // 垃圾收集期间虚拟机又发现已有的三个 2MB 大小的对象全部无法放入 Survivor 空间(Survivor 空间只有 1MB 大小)，
        // 所以只好通过分配担保机制提前转移到老年代去。
        allocation4 = new byte[4*_1MB];
    }
}

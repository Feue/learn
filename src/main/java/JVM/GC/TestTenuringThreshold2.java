package JVM.GC;

/**
 * 动态对象年龄判定
 * VM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PrintTenuringDistribution
 */
public class TestTenuringThreshold2 {
    private static final int _1MB = 1024*1024;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB/4]; // allocation1 + allocation2 大于 Survivor空间一半
        allocation2 = new byte[_1MB/4];
        allocation3 = new byte[4*_1MB];
        allocation4 = new byte[4*_1MB];
        allocation4 = null;
        allocation4 = new byte[4*_1MB];
    }
}

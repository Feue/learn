package JVM.GC;

/**
 * 此代码演示了两点：
 * 1. 对象可以在被GC时自我拯救。
 * 2. 这种自救的机会只有一次，因为一个对象的 finalize() 方法最多只会被系统自动调用一次
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    // finalize() 能做的所有工作，使用 try-finally 或其他方式都可以做的更好、更及时。
    // 是 Java 刚诞生时为了使传统 C、C++ 程序员更容易接受 Java 所做出的一项妥协。
    // 它的运行代价高昂、不确定性大，无法保证各个对象的调用顺序，已被官方明确声明不推荐使用。
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一次自我拯救
        SAVE_HOOK = null;
        System.gc();
        // Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) SAVE_HOOK.isAlive();
        else System.out.println("no, i am dead :(");

        // 第二次自救失败
        SAVE_HOOK = null;
        System.gc();
        // Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) SAVE_HOOK.isAlive();
        else System.out.println("no, i am dead :(");
    }
}

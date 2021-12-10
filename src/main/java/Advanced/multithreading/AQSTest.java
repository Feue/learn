package Advanced.multithreading;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author Feue
 * @create 2021-12-06 15:49
 */
public class AQSTest {
    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        lockTest();
        semTest();
    }

    private static void lockTest() throws InterruptedException {
        MyLock lock = new MyLock();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                AQSTest.count++;
                lock.unlock();
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(AQSTest.count);
    }

    private static void semTest() {
        MySem aSem = new MySem(2);
        MySem bSem = new MySem(0);
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                aSem.acquireShared(2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("a ");
                bSem.releaseShared(2);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                bSem.acquireShared(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("b ");
                aSem.releaseShared(1);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                bSem.acquireShared(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("b ");
                aSem.releaseShared(1);
            }
        }).start();
    }
}

class MyLock extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        if (Thread.currentThread() != getExclusiveOwnerThread()) {
            throw new IllegalMonitorStateException();
        }
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public void unlock() {
        release(1);
    }
}

class MySem extends AbstractQueuedSynchronizer {
    public MySem(int permits) {
        setState(permits);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        for (;;) {
            int available = getState();
            int remaining = available-arg;
            if (remaining < 0 || compareAndSetState(available, remaining)) {
                return remaining;
            }
        }
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        for (;;) {
            int current = getState();
            int next = current+arg;
            if (compareAndSetState(current, next)) {
                return true;
            }
        }
    }
}

package Advanced.multithreading;

/**
 * @author Feue
 * @create 2021-12-14 13:13
 */
public class CopyOnWriteTest {
    private static CopyOnWriteMap<Integer, Integer> map = new CopyOnWriteMap<>();

    private static void read() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+": val = "+map.get(1));
            try {
                Thread.sleep(300+(int) (Math.random()*100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void write() {
        for (int i = 0; i < 30; i++) {
            int oldVal;
            do {
                oldVal = map.get(1);
            } while (!map.replace(1, oldVal, oldVal+1));
            System.out.println(Thread.currentThread().getName()+" add: old - "+oldVal+" new - "+(oldVal+1));
            try {
                Thread.sleep(1000+(int) (Math.random()*500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        map.put(1, 0);
        Thread reader1 = new Thread(CopyOnWriteTest::read);
        Thread reader2 = new Thread(CopyOnWriteTest::read);
        Thread reader3 = new Thread(CopyOnWriteTest::read);
        Thread writer1 = new Thread(CopyOnWriteTest::write);
        Thread writer2 = new Thread(CopyOnWriteTest::write);
        reader1.start();
        reader2.start();
        reader3.start();
        writer1.start();
        writer2.start();

        reader1.join();
        reader2.join();
        reader3.join();
        writer1.join();
        writer2.join();

        System.out.println(Thread.currentThread().getName()+": "+map.get(1));
    }
}

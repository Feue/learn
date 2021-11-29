package Advanced.Communication;

/**
 * 线程通信的应用：生产者/消费者问题
 *
 *
 *
 * @author Feue
 * @create 2021-05-29 20:55
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);
        Consumer c2 = new Consumer(clerk);

        p1.setName("生产者1");
        c1.setName("消费者1");
        c2.setName("消费者2");

        p1.start();
        c1.start();
        c2.start();
    }
}

class Clerk {
    private int productCount = 0;

    public synchronized void produce() {
        if (productCount < 20) {
            productCount++;
            System.out.println(Thread.currentThread().getName()+": 生产第"+productCount+"个产品。");
            this.notify();
        } else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consume() {
        if (productCount > 0) {
            System.out.println(Thread.currentThread().getName()+": 消费第"+productCount+"个产品。");
            productCount--;
            this.notify();
        } else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {//生产者
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+": 开始生产产品.....");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produce();
        }
    }
}

class Consumer extends Thread {//消费者
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+": 开始消费产品.....");
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consume();
        }
    }
}

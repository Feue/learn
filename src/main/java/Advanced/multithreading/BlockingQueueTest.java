package Advanced.multithreading;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Feue
 * @create 2021-12-09 17:01
 */
public class BlockingQueueTest {

    private int queueSize = 10;

    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);

    public static void main(String[] args) {
        BlockingQueueTest test = new BlockingQueueTest();

        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            for (;;) {
                try {
                    queue.take();
                    System.out.println("从队列中取走一个元素，队列剩余"+queue.size()+"个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            for (;;) {
                try {
                    queue.put(1);
                    System.out.println("向队列中插入一个元素，队列剩余空间"+(queueSize-queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

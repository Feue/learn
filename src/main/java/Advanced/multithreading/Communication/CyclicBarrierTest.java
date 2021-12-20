package Advanced.multithreading.Communication;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Feue
 * @create 2021-12-20 10:18
 */
public class CyclicBarrierTest {
    static class PreTask implements Runnable {
        private static final Random rand = new Random();

        private final String task;
        private final CyclicBarrier cyclicBarrier;

        public PreTask(String task, CyclicBarrier cyclicBarrier) {
            this.task = task;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            // 假设共三个关卡
            for (int i = 1; i < 4; i++) {
                try {
                    Thread.sleep(rand.nextInt(1000));
                    System.out.printf("关卡%d: %s完成%n", i, task);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    // 重置屏障
                    cyclicBarrier.reset();
                }
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("本关卡所有前置任务完成，开始游戏...");
        });

        new Thread(new PreTask("加载地图数据", cyclicBarrier)).start();
        new Thread(new PreTask("加载人物模型", cyclicBarrier)).start();
        new Thread(new PreTask("加载背景音乐", cyclicBarrier)).start();
    }
}

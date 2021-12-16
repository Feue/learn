package Advanced.multithreading.Communication;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author Feue
 * @create 2021-12-16 16:56
 */
public class CountDownLatchTest {
    static class PreTask implements Runnable {
        private static final Random random = new Random(System.currentTimeMillis());

        private String task;
        private CountDownLatch countDownLatch;

        public PreTask(String task, CountDownLatch countDownLatch) {
            this.task = task;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(task+" - 任务完成");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 主任务
        new Thread(() -> {
            try {
                System.out.println("等待数据加载...");
                System.out.println("还有"+countDownLatch.getCount()+"个前置任务");
                countDownLatch.await();
                System.out.println("数据加载完成，正式开始游戏！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 前置任务
        new Thread(new PreTask("加载地图数据", countDownLatch)).start();
        new Thread(new PreTask("加载人物模型", countDownLatch)).start();
        new Thread(new PreTask("加载背景音乐", countDownLatch)).start();
    }
}

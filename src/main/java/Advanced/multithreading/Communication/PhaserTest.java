package Advanced.multithreading.Communication;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author Feue
 * @create 2021-12-20 10:53
 */
public class PhaserTest {
    static class PreTask implements Runnable {
        private static final Random rand = new Random();

        private final String task;
        private final Phaser phaser;

        public PreTask(String task, Phaser phaser) {
            this.task = task;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            for (int i = 1; i < 4; i++) {
                try {
                    // 第二关起不加载教程
                    if (i >= 2 && "加载新手教程".equals(task)) {
                        continue;
                    }
                    Thread.sleep(rand.nextInt(1000));
                    System.out.printf("关卡%d: 需要加载%d个模块，当前%s%n", i, phaser.getRegisteredParties(), task);
                    if (i == 1 && "加载新手教程".equals(task)) {
                        System.out.println("下次关卡移除新手教程");
                        phaser.arriveAndDeregister();
                    } else {
                        phaser.arriveAndAwaitAdvance();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(4) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.printf("第%d关准备完成%n", phase+1);
                return phase == 3 || registeredParties == 0;
            }
        };

        new Thread(new PreTask("加载新手教程", phaser)).start();
        new Thread(new PreTask("加载地图数据", phaser)).start();
        new Thread(new PreTask("加载人物模型", phaser)).start();
        new Thread(new PreTask("加载背景音乐", phaser)).start();
    }
}

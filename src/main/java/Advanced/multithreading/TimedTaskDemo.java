package Advanced.multithreading;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Feue
 * @create 2021-12-24 9:50
 */
public class TimedTaskDemo {
    private static final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        // 新建一个固定延时时间的计划任务
        executor.scheduleWithFixedDelay(() -> {
            if (haveMsgAtCurrentTime()) {
                System.out.println(format.format(new Date()));
                System.out.println("发送消息了");
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public static boolean haveMsgAtCurrentTime() {
        // 有没有当前时间需要发送的消息
        return true;
    }
}

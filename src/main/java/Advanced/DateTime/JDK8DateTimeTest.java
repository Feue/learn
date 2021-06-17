package Advanced.DateTime;

import org.junit.Test;

import java.time.*;

/**
 * @author Feue
 * @create 2021-06-01 18:22
 */
public class JDK8DateTimeTest {
    /*
    LocalDate/LocalTime/LocalDateTime 的使用
     */

    @Test
    public void test1() {
        // now(): 获取当前的日期、时间、日期+时间
        System.out.println("now(): 获取当前的日期、时间、日期+时间");
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        // of(): 设置指定的年、月、日、时、分、秒、毫秒，没有偏移量。
        System.out.println("of(): 设置指定的年、月、日、时、分、秒、毫秒，没有偏移量。");
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 6, 1, 19, 5, 30);
        System.out.println(localDateTime1);

        // getXxx(): 获取相关属性
        System.out.println("getXxx(): 获取相关属性");
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getHour());
        System.out.println(localDateTime1.getMonth());
        System.out.println(localDateTime1.getMonthValue());
        System.out.println(localDateTime1.getDayOfWeek());

        //
        // withXxx(): 设置相关属性
        System.out.println("体现不可变性");
        System.out.println(localDateTime);
        System.out.println("withXxx(): 设置相关属性");
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(15);
        System.out.println(localDateTime2);

        // plusXxx():
        System.out.println("plusXxx():");
        LocalDateTime localDateTime3 = localDateTime.plusWeeks(1);
        System.out.println(localDateTime3);

        // minusXxx():
        System.out.println("minusXxx():");
        LocalDateTime localDateTime4 = localDateTime.minusDays(5);
        System.out.println(localDateTime4);
    }

    /*
    Instant 的使用
        类似于 java.util.Date
     */
    @Test
    public void test2() {
        // now(): 获取格林威治时间
        Instant instant = Instant.now(); // 格林威治时间
        System.out.println(instant); // 2021-06-01T11:37:14.544Z

        // 添加时间偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime); // 2021-06-01T19:41:14.005+08:00

        // toEpochMilli(): 获取对应的时间戳（自1970年1月1日0时0分0秒(UTC)的毫秒数）--> Date 类的 getTime()
        long epochMilli = instant.toEpochMilli();
        System.out.println(epochMilli);

        // ofEpochMilli(): 通过给定的毫秒数，获取 Instant 实例。  --> Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1622547805275L);
        System.out.println(instant1);
    }
}

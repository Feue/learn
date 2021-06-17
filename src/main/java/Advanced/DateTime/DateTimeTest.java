package Advanced.DateTime;

import org.junit.Test;

import java.util.Date;

/**
 * @author Feue
 * @create 2021-05-31 15:31
 */
public class DateTimeTest {

    /*
    java.util.Date类
        |---java.sql.Date

    1. 两个构造器的使用
        > 构造器1：Date(): 创建一个对应当前时间的 Date 对象
        > 构造器2：Date(long): 创建指定时间戳的 Date 对象
    2. 两个方法的使用
        > toString(): 显示当前的年、月、日、时、分、秒
        > getTime(): 获取当前 Date 对象的时间戳
    3. java.sql.Date 对应着数据库中的日期类型的变量
        > 如何实例化
        > 如何将 java.util.Date 对象转换为 java.sql.Date 对象
     */

    @Test
    public void test1() {
        long time = System.currentTimeMillis();
        // 返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。
        // 称为时间戳
        System.out.println(time);
    }

    @Test
    public void test2() {
        // 构造器1：Date(): 创建一个对应当前时间的 Date 对象
        Date date1 = new Date();
        System.out.println(date1); // Mon May 31 15:45:07 CST 2021
        System.out.println(date1.getTime()); // 1622447107715

        // 构造器2：Date(long)
        Date date2 = new Date(1622447107715L);
        System.out.println(date2);

        // 创建 java.sql.Date 对象
        java.sql.Date date3 = new java.sql.Date(123123123123L);
        System.out.println(date3); // 1973-11-26

        // 如何将 java.util.Date 对象转换为 java.sql.Date 对象
        // 情况一：
//        Date date4 = new java.sql.Date(321321321321L);
//        java.sql.Date date5 = (java.sql.Date) date4;
        // 情况二：
        Date date6 = new Date();
        java.sql.Date date7 = new java.sql.Date(date6.getTime());
    }
}

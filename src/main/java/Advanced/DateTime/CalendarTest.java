package Advanced.DateTime;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * 日历类 Calendar (抽象类)的使用
 *
 * 注意：
 *    获取月份时：一月是0， 二月是1，……
 *    获取星期时：周日是1， 周一是2，……
 *
 * @author Feue
 * @create 2021-06-01 16:04
 */
public class CalendarTest {
    @Test
    public void testCalendar() {
        // 1. 实例化
        // 方法一：创建其子类 (GregorianCalendar) 的对象
        // 方法二：调用其静态方法 getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getClass());

        // 2. 常用方法
        // get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.MONTH));

        // set()
        calendar.set(Calendar.DAY_OF_MONTH, 18);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // add()
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // getTime() Calendar ---> Date
        Date date = calendar.getTime();
        System.out.println(date);

        // setTime()
        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

    }
}

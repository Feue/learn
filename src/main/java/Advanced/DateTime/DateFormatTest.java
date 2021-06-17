package Advanced.DateTime;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Feue
 * @create 2021-05-31 16:48
 */
public class DateFormatTest {
    /*
    SimpleDateFormat 的使用：SimpleDateFormat 对日期 Date 类的格式化和解析

    1. 两个操作
    1.1 格式化：日期 ---> 字符串
    1.2 解析：字符串 ---> 日期

    2. SimpleDateFormat 的实例化

     */

    @Test
    public void testSimpleDateFormat() throws ParseException {
        // 实例化 SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat();

        // 格式化
        Date date = new Date();
//        System.out.println(date);
        String format = sdf.format(date);
        System.out.println(format);

        // 解析
        String str = "21-5-31 下午5:13";
        Date date1 = sdf.parse(str);
        System.out.println(date1);

        //********************按照指定的方式格式化和解析：调用带参的构造器**********************
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);
        // 解析: 要求字符串必须符合 SimpleDateFormat 识别的格式（构造器参数）
        // 否则抛异常
        Date date2 = sdf1.parse("2021-06-01 00:00:00");
        System.out.println(date2);
    }

    /*
    练习一： 字符串”1999-12-07“转换为 java.sql.Date

    练习二：”三天打鱼两天晒网“   1990-01-01 ---> xxxx-xx-xx
        2021-06-01?  总天数? (date1.getTime()-date0.getTime())/(1000*60*60*24)+1
        总天数 % 5 == 1, 2, 3 : 打鱼
        总天数 % 5 == 4, 0 : 晒网
     */
    @Test
    public void exerTest() throws ParseException {
        String birth = "1999-12-07";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date date = new java.sql.Date(sdf.parse(birth).getTime());
        System.out.println(date);

        String oneDay = "2021-06-01";
        Date date0 = sdf.parse("1999-01-01");
        Date date1 = sdf.parse(oneDay);
        long startTime = date0.getTime();
        long thisTime = date1.getTime();
        long days = (thisTime-startTime)/(24*60*60*1000)+1;
//        System.out.println(days);
        switch ((int) (days%5)) {
            case 1: case 2: case 3:
                System.out.println("打鱼");
                break;
            case 4: case 0:
                System.out.println("晒网");
                break;
        }
    }
}

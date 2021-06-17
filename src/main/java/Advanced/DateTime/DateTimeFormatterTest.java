package Advanced.DateTime;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * @author Feue
 * @create 2021-06-01 19:51
 */
public class DateTimeFormatterTest {
    /*
    DateTimeFormatter: 格式化或解析日期、时间
        类似于 SimpleDateFormat

     */
    @Test
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.now();
        // 方式一：预定义的标准格式。如：ISO_LOCAL_DATE_TIME/ISO_LOCAL_DATE/ISO_LOCAL_TIME
        System.out.println("预定义的标准格式:");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 格式化：
        String format = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(format);
        // 解析：
        TemporalAccessor parse = formatter.parse("2021-06-01T19:59:21.069");
        System.out.println(parse);

        // 方式二：本地化相关的格式。
        // 格式化:
        // 如：ofLocalizedDateTime(): FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT
        System.out.println("ofLocalizedDateTime():");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

        String str1 = formatter1.format(localDateTime);
        String str2 = formatter2.format(localDateTime);
        String str3 = formatter3.format(localDateTime);

        System.out.println(str1); // 2021年6月1日 下午08时08分28秒
        System.out.println(str2); // 2021-6-1 20:08:28
        System.out.println(str3); // 21-6-1 下午8:08

        // 如：ofLocalizedDate(): FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT / FormatStyle.FULL
        System.out.println("ofLocalizedDate():");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter4 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        DateTimeFormatter formatter5 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        DateTimeFormatter formatter6 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        DateTimeFormatter formatter7 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

        String str4 = formatter4.format(localDate);
        String str5 = formatter5.format(localDate);
        String str6 = formatter6.format(localDate);
        String str7 = formatter7.format(localDate);

        System.out.println(str4); // 2021年6月1日 星期二
        System.out.println(str5); // 2021年6月1日
        System.out.println(str6); // 2021-6-1
        System.out.println(str7); // 21-6-1

        // (重点)方式三：自定义的格式。如：ofPattern("yyyy-MM-dd hh:mm:ss E"):
        System.out.println("ofPattern():");
        DateTimeFormatter formatter8 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss E");
        // 格式化:
        String str8 = formatter8.format(localDateTime);
        System.out.println(str8); // 2021-06-01 08:16:14 星期二
        // 解析:
        TemporalAccessor parse1 = formatter8.parse("2021-06-01 08:16:14 星期二");
        System.out.println(parse1);
    }
}

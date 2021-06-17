package Advanced.Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Java 中使用 InetAddress 类代表 IP
 *
 * 本机回路地址：127.0.0.1
 *
 * 端口号：16位整数 0~65535(MySQL 3306/TomCat 8080...)
 *
 * 网络套接字Socket：端口号与IP地址组合
 *
 * @author Feue
 * @create 2021-06-07 21:36
 */
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress inet1 = InetAddress.getByName("192.168.0.1");
            System.out.println(inet1);

            InetAddress inet2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inet2);

            // 本机回路地址: localhost/127.0.0.1
            InetAddress inet3 = InetAddress.getByName("localhost");
            System.out.println(inet3);
            InetAddress inet4 = InetAddress.getLocalHost();
            System.out.println(inet4);

            // 获取域名
            System.out.println(inet2.getHostName());
            // 获取主机地址
            System.out.println(inet2.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

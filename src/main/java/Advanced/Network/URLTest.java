package Advanced.Network;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL 网络编程
 *
 * 格式：
 * http://localhost:8080/examples/beauty.jpg?username=Tom
 * 协议    主机名    端口号 资源地址             参数列表
 *
 * @author Feue
 * @create 2021-06-14 20:10
 */
public class URLTest {
    /*
    常用方法:
    public String getProtocol() 获取该 URL 的协议名
    public String getHost() 获取 该 URL 的主机名
    public String getPort() 获取 该 URL 的端口号
    public String getPath() 获取 该 URL 的文件路径
    public String getFile() 获取 该 URL 的文件名
    public String getQuery() 获取 该 URL 的查询名
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/examples/beauty.jpg?username=Tom");

            System.out.println("协议名: "+url.getProtocol());
            System.out.println("主机名: "+url.getHost());
            System.out.println("端口号: "+url.getPort());
            System.out.println("文件路径: "+url.getPath());
            System.out.println("文件名: "+url.getFile());
            System.out.println("查询名: "+url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

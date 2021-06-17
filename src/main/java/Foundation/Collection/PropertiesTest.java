package Foundation.Collection;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Feue
 * @create 2021-06-02 21:32
 */
public class PropertiesTest {
    // Properties: 常用来处理配置文件。 key 和 value 都是 String
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        FileInputStream fis = new FileInputStream("jdbc.properties");
        properties.load(fis); // 加载流对应文件

        String name = properties.getProperty("name");

        String password = properties.getProperty("password");

        System.out.println("name:"+name+", password:"+password);

        fis.close();
    }
}

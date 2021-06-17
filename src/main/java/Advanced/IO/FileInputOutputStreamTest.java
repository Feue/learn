package Advanced.IO;

import org.junit.Test;

import java.io.*;

/**
 * @author Feue
 * @create 2021-06-06 21:14
 */
public class FileInputOutputStreamTest {
    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // 1. 创建 File 类的对象，指明读入和写出的文件
            File srcFile = new File("爱情与友情.jpg");
            File dstFile = new File("爱情与友情1.jpg");

            // 2. 创建输入流和输出流的对象
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(dstFile);

            // 3. 数据的读入和写出操作
            byte[] cbuf = new byte[5];
            int len;
            while ((len = fis.read(cbuf)) != -1) {
                fos.write(cbuf, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭输入输出流
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

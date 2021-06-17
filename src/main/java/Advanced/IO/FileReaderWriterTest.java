package Advanced.IO;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Feue
 * @create 2021-06-06 21:06
 */
public class FileReaderWriterTest {
    @Test
    public void test1() {
        FileReader fr = null;
        FileWriter fw = null;

        try {
            // 1. 创建 File 类的对象，指明读入和写出的文件
            File srcFile = new File("hello.txt");
            File dstFile = new File("hello1.txt");

            // 2. 创建输入流和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(dstFile);

            // 3. 数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                fw.write(cbuf, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭输入输出流
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package Advanced.IO;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Feue
 * @create 2021-06-06 22:17
 */
public class RandomAccessFileTest {
    @Test
    public void test() {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile("爱情与友情.jpg", "r");
            raf2 = new RandomAccessFile("爱情与友情2.jpg", "rw");

            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf1.read(buffer)) != -1) {
                raf2.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf1.close();
                raf2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");

        raf1.seek(3); // 将指针调到位置3(从0开始)
        raf1.write("xyz".getBytes());

        raf1.close();
    }

    @Test
    public void test3() throws IOException {
        // 数据插入
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");

        byte[] buffer = new byte[1024];
        int len;
        // 方式一：使用 StringBuilder
//        StringBuilder sb = new StringBuilder((int) new File("hello.txt").length());
//        raf1.seek(3);
//        while ((len = raf1.read(buffer)) != -1) {
//            sb.append(new String(buffer, 0, len));
//        }
//
//        raf1.seek(3);
//        raf1.write("xyz".getBytes());
//        raf1.write(sb.toString().getBytes());

        // 方式二：使用ByteArrayOutputStream
        raf1.seek(12);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = raf1.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        raf1.seek(12);
        raf1.write("222".getBytes());
        raf1.write(baos.toByteArray());

        raf1.close();
    }
}

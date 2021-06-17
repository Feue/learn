package Advanced.IO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Feue
 * @create 2021-06-03 21:11
 */
public class FileTest {

    /*
    1. 创建 File 类的实例
        File(String filepath)
        File(String parentPath, String childPath)
        File(File parent, String childPath)

    2. 绝对路径、相对路径

    3. 路径分隔符
        Windows: \\
        Unix: /
     */

    @Test
    public void test1() {
        // 构造器1:
        File file1 = new File("test.txt"); // 相对路径
        File file2 = new File("F:\\Java\\learn\\test.txt"); // 绝对路径

        System.out.println(file1);
        System.out.println(file2);

        // 构造器2:
        File file3 = new File("F\\Java", "learn");
        System.out.println(file3);

        // 构造器3:
        File file4 = new File(file3, "test.txt");
        System.out.println(file4);
    }

    /*
    File 类的获取功能
        public String getAbsolutePath() 获取绝对路径
        public String getPath 获取路径
        public String getName 获取名称
        public String getParent() 获取上层文件目录路径。 若无返回 null
        public long length 获取文件长度即：字节数。不能获取目录的长度。
        public long lastModified 获取最后一次的修改时间，毫秒值

      下面两个方法适合于文件目录
        public String[] list 获取指定目录下的所有文件或者文件目录的名称数组
        public File[] listFiles 获取指定目录下的所有文件或者文件目录的 File 数组
     */

    @Test
    public void test2() {
        File file1 = new File("test.txt");

        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));

        System.out.println("文件目录：");
        File file2 = new File("F:\\Java\\learn");
//        System.out.println(Arrays.toString(file2.list()));
        for (String str: file2.list()) {
            System.out.println(str);
        }
        System.out.println();
//        System.out.println(Arrays.toString(file2.listFiles()));
        for (File f: file2.listFiles()) {
            System.out.println(f);
        }
    }

    /*
    File 类的重命名功能
        public boolean renameTo(File dest) 把文件重命名为指定的文件路径
     */

    @Test
    public void test3() {
        File file1 = new File("test.txt");
        File file2 = new File("F:\\Java\\learn\\test2.txt");

        boolean renameTo = file1.renameTo(file2);
        System.out.println(renameTo);
    }

    /*
    File 类的判断功能
        public boolean isDirectory() 判断是否是文件目录
        public boolean isFile() 判断是否是文件
        public boolean exists() 判断是否存在
        public boolean canRead() 判断是否可读
        public boolean canWrite() 判断是否可写
        public boolean isHidden() 判断是否隐藏
     */

    @Test
    public void test4() {
        File file = new File("test2.txt");

        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.exists());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.isHidden());
    }

    /*
    File 类的创建功能
        public boolean createNewFile(): 创建文件。若文件存在，则不创建，返回 false
        public boolean mkdir(): 创建文件目录。如果此文件目录存在，就不创建了。
            如果此文件目录的上层目录不存在也不创建。
        public boolean mkdirs(): 创建文件目录。如果上层文件目录不存在，一并创建
        注意事项：如果你创建文件或者文件目录没有写盘符路径，那么，默认在项目路径下。

    File 类的删除功能
        public boolean delete(): 删除文件或者文件夹
        注意事项：Java中的删除不走回收站。
        要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录
     */

    @Test
    public void test5() throws IOException {
        File file = new File("hello.txt");
        if (file.exists()) {// 文件存在
            file.delete();
            System.out.println("删除成功");
        } else {
            file.createNewFile();
            System.out.println("创建成功");
        }
    }
    @Test
    public void test6() {
        // 文件目录创建
        File file = new File("d:\\io1\\io2");

        boolean mkdir = file.mkdir();
        if (mkdir) {
            System.out.println("创建成功1");
        }

        File file2 = new File("d:\\io1\\io2");

        boolean mkdirs = file2.mkdirs();
        if (mkdirs) {
            System.out.println("创建成功2");
        }
    }
}

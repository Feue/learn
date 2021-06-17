package main.java.EscapeCharacter;

public class test {
    public static void main(String[] args) {
        System.out.println("换行符\\n:");
        char c1 = 'n', c2 = '\n';
        System.out.print(c2);
        System.out.println(c1);
        System.out.println("制表符\\t:");
        char c3 = '\t';
        System.out.print('A');
        System.out.print(c3);
        System.out.println('B');
    }
}
//native2ascii 将字符转换为Unicode码
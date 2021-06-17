package Advanced.IO;

import org.junit.Test;

import java.io.*;

/**
 * @author Feue
 * @create 2021-06-06 21:24
 */
class Person implements Serializable{

    public static final long serialVersionUID = 65489132189742L;

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ObjectInputOutputStreamTest {
    // 序列化
    @Test
    public void testObjectOutputStream() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            oos.writeObject(new String("Hello, Feue!"));
            oos.writeObject(new Person("Feue", 21));

            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 反序列化
    @Test
    public void testObjectInputStream() {
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            String str = (String) ois.readObject();
            System.out.println(str);

            Person person = (Person) ois.readObject();
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

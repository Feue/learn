package Advanced.Reflection.objectAnalyzer;

import java.util.ArrayList;

/**
 * This program uses reflection to spy on objects.
 */
public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> squares = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            squares.add(i*i);
        }
        System.out.println(new ObjectAnalyzer().toString(squares));
//        Class cl = squares.getClass();
//        Field[] fileds = cl.getDeclaredFields();
//        for (Field f: fileds) {
//            System.out.println(f);
//        }
    }
}
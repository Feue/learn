import java.util.*;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int v = in.nextInt();
        int t = (s+v-1)/v+10;
        int h = t/60%24;
        int m = t%60;
        if(m == 0) h = (32-h)%24;
        else h = (31-h)%24;
        System.out.printf("%02d:%02d\n", h, 60-m);
        in.close();
    }

    public int kthLargestValue(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] val = new int[n][m];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i == 0 || j == 0) val[i][j] = matrix[i][j];
                else val[i][j] = val[i-1][j]^val[i][j-1]^val[i-1][j-1]^matrix[i][j];
                list.add(val[i][j]);
            }
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        return list.get(k-1);
    }
}
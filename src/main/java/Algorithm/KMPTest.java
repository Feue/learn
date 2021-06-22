package Algorithm;

/**
 * KMP 字符串匹配算法板子
 *
 * @author Feue
 * @create 2021-06-22 19:26
 */
public class KMPTest {
    public static int[] getNext(String s) {
        int len = s.length();
        int[] next = new int[len];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < len-1) {
            if (k == -1 || s.charAt(j) == s.charAt(k)) {
                k++;
                j++;
                if (s.charAt(j) != s.charAt(k)) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static int KMP(String s, String p) {
        int i = 0;
        int j = 0;
        int slen = s.length();
        int plen = p.length();
        int[] next = getNext(p);
        while (i < slen && j < plen) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == plen) {
            return i-j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String s = "abcabdabcd";
        String p = "abdab";
        int kmp = KMP(s, p);
        System.out.println(kmp);
    }
}

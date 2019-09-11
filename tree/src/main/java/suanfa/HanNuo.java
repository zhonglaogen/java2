package suanfa;

/**
 * 汉诺塔
 */
public class HanNuo {

    public void hanNuo(int n, char a, char b, char c) {
        if (n == 1) {
            move(n, a, c);
        } else {
            hanNuo(n - 1, a, c, b);
            move(n, a, c);
            hanNuo(n - 1, b, a, c);
        }
    }

    private void move(int n, char a, char c) {
        System.out.println(n + ":" + a + "->" + c);
    }

    public static void main(String[] args) {
        HanNuo h = new HanNuo();
        h.hanNuo(3, 'a', 'b', 'c');
    }
}

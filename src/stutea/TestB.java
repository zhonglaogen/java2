package stutea;

/**
 * TestB
 *
 * @author zhonglx
 * @version 1.0.0 2021/2/3 11:21
 */
public class TestB {
    public static void main(String[] args) {
        A b = new B();
        A c = new C();
        System.out.println(b instanceof A);
        System.out.println(b instanceof B);
        System.out.println(b instanceof C);

        System.out.println(c instanceof  B);
    }
}

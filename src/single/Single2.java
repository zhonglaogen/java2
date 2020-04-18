package single;

/**
 * 懒汉模式
 */
public class Single2 {
    private static Single2 s2;
    private Single2(){}
    public static synchronized Single2 getInstance() {
        if (s2 == null) {
            s2 = new Single2();
        }
        return s2;
    }
}


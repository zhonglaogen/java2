package hario.pracitce;

/**
 * TestShare
 *
 * @author zhonglx
 * @version 1.0.0 2020/12/12 20:05
 */
public class TestShare {

    static class Share {
        int s1;
//        long l1, l2, l3, l4, l5, l6, l7, l8 ,l9, l10;
        int s2;
    }

    public static void main(String[] args) throws InterruptedException {
        Share s1 = new Share();
        Thread t1 = new Thread(() -> {
            int count = 0;
            while (count < ((1 << 31) - 1)) {
                s1.s1 = 2;
                count++;
            }
        });

        Thread t2 = new Thread(() -> {
            int count = 0;
            while (count < ((1 << 31) - 1)) {
                s1.s2 = 2;
                count++;
            }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis() - start);
    }
}

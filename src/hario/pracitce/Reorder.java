package hario.pracitce;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Reorder
 *
 * @author zhonglx
 * @version 1.0.0 2020/12/12 18:25
 */
public class Reorder {

    private static int x = 0;
    private static int y = 0;
    private static int r1 = 0;
    private static int r2 = 0;

    public static void main(String[] args) throws Exception {
//        CountDownLatch begin = new CountDownLatch(1);
        Semaphore begin1 = new Semaphore(1);
        Semaphore begin2 = new Semaphore(1);
        Semaphore end = new Semaphore(1);
        
        Random rand = new Random();
        new Thread(() -> {
            while(true) {
                try {
                    begin1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (rand.nextInt(8) == 0) {}
                x = 1;
                r1 = y;

                end.release();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    begin2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (rand.nextInt(8) == 0) {}
                y = 1;
                r2 = x;

                end.release();
            }

        }).start();


        int N = 100000;
        int k = 0;
        for (int i = 0; i < 100000; i ++) {
            x = y = 0;

            begin1.release();
            begin2.release();
            end.acquire();
            end.acquire();

            if (r1 == 0 && r2 == 0) {
                k ++;
                System.out.printf("%4d reorder appeared in iterate %d%n", k, i);
            }
        }

    }

}

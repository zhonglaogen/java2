package hario.pracitce;

import java.util.concurrent.Semaphore;

/**
 * TestExp
 *
 * @author 钟连喜
 * @version 1.0.0 2020/6/22 18:15
 */
public class TestExp extends Thread {

   public static int x = 0;
   public static int y = 0;
    public static int xx = 0;
    public static int yy = 0;
    static Semaphore s1 = new Semaphore(0);
    static Semaphore s2 = new Semaphore(0);
    static Semaphore s3 = new Semaphore(0);
    static Thread main = null;
    static Object lock  = new Object();
    static volatile int aa = 1;

    public static volatile boolean finish = false;



    public static void main(String[] args) throws Exception {

        main = Thread.currentThread();
        Thread t1 = new Thread(() -> {
            while (!finish) {

                    try {
                        s1.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x = 1;
//                    aa = 2;
                    xx = y;
                    s3.release();
                }



        });
        Thread t2 = new Thread(() -> {
            while (!finish) {

                    try {
                        s2.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 等待开始 await
                    y = 1;
//                    aa = 2;
                    yy = x;
                    // 结束 singial
                    s3.release();
                }



        });

        int count = 0;
        int num = 0;
        t1.start();
        t2.start();
        while (count < 1000000) {
            x = 0;
            y = 0;
            // 通知开始
            s1.release();
            s2.release();
            // 等待开始
            s3.acquire();
            s3.acquire();
            if (xx == 0 && yy == 0) {
                System.out.println(num++ + "cur: " + count);
            }

            count ++;
        }
        finish = true;
    }
}
/**
 *
 * 假设有如下使用场景：
 *
 * A 是应用程序的框架级结构体，在 A 包含子模块 B 和 C 的指针；
 * B 为了方便的使用应用的其他子模块（比如 C ）功能，所以在其结构体包含了 A 的指针；
 * C 要调用 A 包中的某个方法；
 *
  *
  * @author zhonglianxi
  * @date 2020/9/16 16:13
  * @param null
  * @return
 */


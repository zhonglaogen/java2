package testlock.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 多个线程抢占多个资源
 * 抢占车位
 * 分为公平所和非公平所，不写默认是false
 * 有增加有减少
 */
public class SemaphoreTest {
    static Semaphore s=new Semaphore(3);
    public static void main(String[] args) {
        //模拟6部汽车
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    s.acquire();
                    System.out.println(Thread.currentThread().getName()+"\talready have truck space");
                    try{ TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e){ e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t停车 3 秒");
                } catch (InterruptedException e) {

                }finally {
                    s.release();
                }
            },String.valueOf(i)).start();
        }
    }

}

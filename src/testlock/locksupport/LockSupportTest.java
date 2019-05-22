package testlock.locksupport;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 指定线程复活
 * 不复活线程就会一直阻塞
 */
public class LockSupportTest extends Thread {
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+"\t"+"start***************");
        LockSupport.park();
        System.out.println(Thread.currentThread().getName()+"\t"+"over****************");
    }

    public static void main(String[] args) {
        LockSupportTest lockSupportTest = new LockSupportTest();
        LockSupportTest lockSupportTest1 = new LockSupportTest();
        lockSupportTest.start();
        try{ TimeUnit.SECONDS.sleep(2);}catch(InterruptedException e){ e.printStackTrace();}
        lockSupportTest1.start();
        try{ TimeUnit.SECONDS.sleep(2);}catch(InterruptedException e){ e.printStackTrace();}
        LockSupport.unpark(lockSupportTest);
        LockSupport.unpark(lockSupportTest1);


    }
}

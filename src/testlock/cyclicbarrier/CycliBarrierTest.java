package testlock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 先到的被阻塞，七个全都到了，在运行最后线程
 */
public class CycliBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println(Thread.currentThread().getName()+"××××××××××××召唤神龙");
        });
        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(()->{
                        System.out.println(Thread.currentThread().getName()+"\t收集到的第"+ finalI +"龙珠");
                try {
                    cyclicBarrier.await();
                    System.out.println("ok"+Thread.currentThread().getName());
                } catch (InterruptedException e) {

                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
                }
    }
}

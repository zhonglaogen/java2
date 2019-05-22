package testlock.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 通过枚举实现一个存储字符串的数据库功能
 * CountDownLatch做减法，await的线程都要等到减为0才可以继续
 */
public class TestCountDownLatch {

    static CountDownLatch countDownLatch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        merage();

    }

    /**
     * 统一六国
     * @throws InterruptedException
     */
    private static void merage() throws InterruptedException {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "国，被灭");
                countDownLatch.countDown();
            }, CounttryEmune.for_each_countryEmun(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t" + "统一六国");
    }

    /**
     * 关门，所有人走了，主线程才能走
     * @throws InterruptedException
     */
    private static void closeDoor() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "结束");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t" + "全部结束");
    }
}

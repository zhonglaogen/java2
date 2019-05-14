package testthreadlocal;

public class LongThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread()+"long---pre：：："+TestThreadLocal.t1.get());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread()+"long---mid：：："+TestThreadLocal.t1.get());
//            TestThreadLocal.t1.set(5);
            System.out.println(Thread.currentThread()+"long---end：：："+TestThreadLocal.t1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

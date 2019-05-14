package testthreadlocal;

public class ShortThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread()+"short---pre：：："+TestThreadLocal.t1.get());
        TestThreadLocal.t1.set(1);
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread()+"short---pre：：："+TestThreadLocal.t1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package testlock;

import java.util.concurrent.TimeUnit;

public class TestLock {//结果交替执行1,2
    Object o=new Object();
    void  m(){
        synchronized (o){

            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }

    }

    public static void main(String[] args) {
        TestLock t=new TestLock();
        new Thread(t::m,"1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        t.o=new Object();//不加永远不会执行线程2
        new Thread(t::m,"2").start();


    }
}

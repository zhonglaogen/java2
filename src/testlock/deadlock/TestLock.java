package testlock.deadlock;

import java.util.concurrent.TimeUnit;

public class TestLock {//结果交替执行1,2
    Object o=new Object();
    void  m(){
        //锁住的对象是new Object();
        synchronized (o){

            while (true){
                try {
                    System.out.println(o.toString());
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
        new Thread(t::m,"AAAAAAAAAAAAAA").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.o=new Object();//不加永远不会执行线程2,此时他们锁的不是同一个对象了
        new Thread(t::m,"BBBBBBBBBBBBB").start();


    }
}

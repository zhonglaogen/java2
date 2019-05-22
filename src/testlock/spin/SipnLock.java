package testlock.spin;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 循环比较获取直到成功为止，没有类似的wait阻塞
 *
 * 通过Cas操作完成自旋锁，A线程进来调用mylock方法自己持有锁五秒钟，B随后进来后发现当前线程有锁，自旋等待，
 * 直到A释放锁B随后抢到
 */
public class SipnLock {
    //原子引用线程
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    public void myLock(){
        Thread t=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t"+"come in");
        //自旋，第一个为希望值，第二个为交换的值，交换成功返回true,
        //期望值相同才可以交换
        while(!atomicReference.compareAndSet(null,t)){

        }


    }

    public void myunLock(){
        Thread thread=Thread.currentThread();
        //期望值相同才可以交换
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t"+"unlock");
    }


    public static void main(String[] args) {
       SipnLock sipnLock=new SipnLock();
       new Thread(()->{
           sipnLock.myLock();
          try{ TimeUnit.SECONDS.sleep(5);}catch(InterruptedException e){ e.printStackTrace();}
            sipnLock.myunLock();

       },"AA").start();

       try{ TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e){ e.printStackTrace();}
       new Thread(()->{
           sipnLock.myLock();
           try{ TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e){ e.printStackTrace();}
           sipnLock.myunLock();
              },"BB").start();
    }
}

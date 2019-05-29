package testlock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程间按顺序调用，实现Abc三个线程启动，实现如下
 * AA打印5次，BB打印10 次，cc打印15次
 * AA打印5次，BB打印10 次，cc打印15次
 * 来十次
 * await方法会将锁释放，线程进入等待状态
 * signal方法会将线程重等待条件队列迟放出，进入等待锁的队列，
 * ，尝试重新获取锁（会在unlock方法执行后，获得锁，才放出），才会进入就绪状态
 * 注意：等待锁的队列可与运行或就绪状态的线程争抢锁
 *
 */
class ShareResource{
    private int num=1;//A:1,B:2,C:3
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public void print5(){

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"拿到了锁");
            //1判断,此处为精确唤醒，可以用if判断
            while (num!=1){
                System.out.println(Thread.currentThread().getName()+"await");
                c1.await();
            }
            //2干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3通知
            num=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }}
    public void print10(){

        lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"拿到了锁");
                    //1判断
                    while (num!=2){
                        System.out.println(Thread.currentThread().getName()+"await");
                      c2.await();
                    }
                    //2干活
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName()+"\t"+i);
                    }
                    //3通知
                    num=3;
                    c3.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
    }
    public void print15(){

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"拿到了锁");
            //1判断
            while (num!=3){
                System.out.println(Thread.currentThread().getName()+"await");
                c3.await();
            }
            //2干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3通知
            num=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class ConditionTest {
    public static void main(String[] args) {

        ShareResource resource=new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("线程A在运行");
                resource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("线程B在运行");
                resource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("线程C在运行");
                resource.print15();
            }
        },"C").start();
    }

}

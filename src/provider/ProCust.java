package provider;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个初始值为0的变量，两个线程对其交替操作，一个加一，一个减一，来五轮
 * 1 线程 操作（方法） 资源类
 * 2 判断 干活 通知
 * 3 防止虚假唤醒：：while
 *
 * 生产的时候不能消费，消费的时候不能生产
 */
class  Share{
    private int num=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void increment()throws Exception{
        lock.lock();
                try {
                    //1判断，多线程要用while,不用if,
                    // await释放锁后，此线程不一定会在signal后的unlock时得到锁，
                    //如果其他线程得到锁，
                    // 当其他生产线程生产后的signal的unlock才会争抢锁，得到锁才能执行
                    while (num!=0){
                        //等待，不能生产
                        condition.await();
                    }
                    //2干活
                    num++;
                    System.out.println(Thread.currentThread().getName()+"\t生产"+num);
                    //3通知唤醒
                    condition.signalAll();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

    }
    public void decrement()throws Exception{
        lock.lock();
        try {
            //1判断
            while (num==0){
                //等待，不能消费
                condition.await();
            }
            //2干活
            num--;
            System.out.println(Thread.currentThread().getName()+"\t消费"+num);
            //3通知唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
public class ProCust {
    public static void main(String[] args) {
        Share share=new Share();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    share.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
               },"AA").start();


        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    share.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    share.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();


        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    share.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}


package testlock.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 多个线程同时读一个资源没错，为了满足并发，读取共享资源可以同时进行
 * 如果有一个资源正在写，其他线程就不能读写
 * lock只支持一个线程进去，单读写锁可支持多个线程读
 * 读写锁的使用
 * 写操作：：原子，独占 整个过程是完整的统一体，中间不许中断
 */
class Mycache{
    //立刻通知，可见性及时
    private volatile Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();


    public void put(String key,Object value){
       lock.writeLock().lock();
               try {
                   System.out.println(Thread.currentThread().getName()+"\t正在写入："+key);
                   try{ TimeUnit.MILLISECONDS.sleep(300);}catch(InterruptedException e){ e.printStackTrace();}
                   map.put(key, value);
                   System.out.println(Thread.currentThread().getName()+"\t写入完成");
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                   lock.writeLock().unlock();
               }


    }
    public void get(String key){
        lock.readLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"\t正在读取：");
                    try{ TimeUnit.MILLISECONDS.sleep(300);}catch(InterruptedException e){ e.printStackTrace();}
                    Object o = map.get(key);
                    System.out.println(Thread.currentThread().getName()+"\t读取完成"+o);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.readLock().unlock();
                }

    }

}

public class TestReadAndWrite {
    public static void main(String[] args) {
        Mycache m1 = new Mycache();


             for (int i = 0; i < 5; i++) {
                 int finalI = i;
                 new Thread(()->{
                             m1.put(finalI +"", finalI +"");
                         },String.valueOf(i)).start();
                     }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                m1.get(finalI +"");
            },String.valueOf(i)).start();
        }
    }

}

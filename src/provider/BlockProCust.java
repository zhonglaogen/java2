package provider;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不要锁
 * 不需要控制wait和notify
 *
 * 生产者消费者模式两大特点：阻塞，和操作资源的原子性
 *
 * 1阻塞队列：生产满了会阻塞，直到不满才继续生产（代替了await和signal）
 * 普通的消费：满了阻塞是用await，不满要signal才知道（模拟阻塞【用锁才可以模拟阻塞，await释放锁，线程进入等待队列】）
 *
 * 2锁的存在是因为得到锁资源的线程才可以操作资源，避免多人同时操作同一个资源
 *
 * 3普通的消费者要锁,判断是否可添加，才能i++（操作资源）
 * 此种用原子代替i++（比较交换，其他线程操作后）
 * 比较交换失败后，会再次用新的值再次cas
 */
class Mysource{
    //默认开启，进行生产+消费
    private volatile  boolean FLAG=true;
    private AtomicInteger atomicInteger=new AtomicInteger();

    BlockingQueue<String> blockingQueue=null;

    public Mysource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd()throws Exception{
        String data=null;
        boolean retValue=false;
        while (FLAG){
             data = atomicInteger.incrementAndGet() + "";
            retValue=blockingQueue.offer(data,1L,TimeUnit.SECONDS);
             if(retValue){
                 System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"success");
             }else {
                 System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
             }
             TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t停止生产，FlAG等于false");
    }
    public void myCustomer()throws Exception{
        String result=null;
        while (FLAG){
            result=blockingQueue.poll(1L,TimeUnit.SECONDS);
            if(result==null || result.equalsIgnoreCase("")){
                //一但取不到就停止生产和消费，这不对
//                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t"+"超过两秒未去掉");
                System.out.println();
                System.out.println();
                System.out.println();
                //直接返回，不在继续消费
                return;
            }
                System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"success");
            }
        }
        public void stop()throws Exception{
        this.FLAG=false;
        }
    }


public class BlockProCust {
    public static void main(String[] args) throws Exception {
        Mysource mysource=new Mysource(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\tprod thread is start");
            try {
                mysource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

//        try{ TimeUnit.SECONDS.sleep(10);}catch(InterruptedException e){ e.printStackTrace();}

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\tcustmoer thread is start");
            try {
                mysource.myCustomer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try{ TimeUnit.SECONDS.sleep(9);}catch(InterruptedException e){ e.printStackTrace();}

        System.out.println("5 second is end");
        mysource.stop();
    }
}

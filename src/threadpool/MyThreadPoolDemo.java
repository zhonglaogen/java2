package threadpool;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //看线程数
        System.out.println(Runtime.getRuntime().availableProcessors());
        /**
         * cpu 线程数+1
         * IO 线程数×2
         * 线程数/0.8~0.9
         */
        //最大线程是阻塞队列加上最大线程数目
        ExecutorService executorService=new ThreadPoolExecutor(2,
                5,
                6,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(2));

        for (int i = 0; i < 7; i++) {
            final int tmp=i;
            executorService.submit(()->{
                try{ TimeUnit.SECONDS.sleep(2);}catch(InterruptedException e){ e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+tmp);
            });
        }
        executorService.shutdown();


    }
}

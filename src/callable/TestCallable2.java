package callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * get方法会阻塞主线程，直至线程结束，但是放到一个list里面，线程就会并行
 */
public class TestCallable2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es=Executors.newFixedThreadPool(100);
        List<Future> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> f=es.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(2000);
                    return Thread.currentThread().getName();
                }
            });
            list.add(f);
        }
        for (Future future:list){
            String s= (String) future.get();
            System.out.println(s);
        }
        es.shutdown();
    }
}

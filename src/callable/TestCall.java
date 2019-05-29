package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TestCall implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("ENTER CALL METHOD");
        try{ TimeUnit.SECONDS.sleep(2);}catch(InterruptedException e){ e.printStackTrace();}
        return 15;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask=new FutureTask<>(new TestCall());
        new Thread(futureTask).start();
        //第二次的new thread不会在调用call方法了
//        new Thread(futureTask).start();
        //get 会阻塞当前线程
        Integer integer = futureTask.get();
        System.out.println(integer);


    }
}

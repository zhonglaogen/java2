import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.FutureTask;

public class Test1 {
    static User u1;
    public static void main(String[] args) throws InterruptedException {
//        Thread.currentThread().interrupt();
//        User user=new User("aa");

        byte[] b=null;
        for(int i=0; i<10;i++){
            b=new byte[1*1024*1024];    //1M
            //-Xmx20m -Xms20m -Xmn1m -XX:+PrintGCDetails
        }

        u1=new User();
        u1=null;
        //第一次gc时会调用finalize方法
        System.gc();
        Thread.sleep(1000);
        if(u1==null){
            System.out.println("is clean");
        }else {
            System.out.println("no clean");
        }
        u1=null;
        System.gc();
        if(u1==null){
            System.out.println("is clean");
        }else {
            System.out.println("no clean");
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i;;){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
//        thread.setDaemon(false);
        thread.start();
        System.out.println("main is over");
    }
}

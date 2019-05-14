import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.FutureTask;

public class Test1 {
    public static void main(String[] args) {
//        Thread.currentThread().interrupt();
//        User user=new User("aa");

        byte[] b=null;
        for(int i=0; i<10;i++){
            b=new byte[1*1024*1024];    //1M
            //-Xmx20m -Xms20m -Xmn1m -XX:+PrintGCDetails
        }

    }
}

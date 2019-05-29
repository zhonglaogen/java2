import single.Single5;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.FutureTask;

public class Test1 {
    static User u1;
    static int num;
    public static void main(String[] args) throws InterruptedException {
//        Thread.currentThread().interrupt();
//        User user=new User("aa");

        //测试gc
//        byte[] b=null;
//        for(int i=0; i<10;i++){
//            b=new byte[1*1024*1024];    //1M
//            //-Xmx20m -Xms20m -Xmn1m -XX:+PrintGCDetails
//        }

//        //测试finalize方法
//        u1=new User();
//        u1=null;
//        //第一次gc时会调用finalize方法
//        System.gc();
//        Thread.sleep(1000);
//        if(u1==null){
//            System.out.println("is clean");
//        }else {
//            System.out.println("no clean");
//        }
//        u1=null;
//        System.gc();
//        if(u1==null){
//            System.out.println("is clean");
//        }else {
//            System.out.println("no clean");
//        }


        //测试守护线程
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i;;){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//
//                    }
//                }
//            }
//        },"AAAAAA");
//        thread.setDaemon(true);//不加这句会一直执行线程AAAAAA,会在主线程结束后释放守护线程
//        thread.start();
//        System.out.println("main is over");

        //测试枚举的单例模式
//
//        Single5 instace = Single5.INSTANCE;
//        System.out.println(instace.hashCode());

        //打印整数二进制
//        int a=-1;
//        for(int i=0;i<32;i++){
//            int t=(a&0x80000000>>>i)>>>(31-i);
//            System.out.print(t);
//        }

//        String aa="123456789";
//        int i = aa.lastIndexOf(".");
//        System.out.println(i);

        while (true){
            num++;
            System.out.println(num);
            if(num==10){
                return;
            }
            System.out.println("while");

        }





    }
}

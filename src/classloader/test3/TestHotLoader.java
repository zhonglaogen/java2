package classloader.test3;

import classloader.test1.HelloLoader;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestHotLoader {
    public static void main(String[] args) {
//        Timer t1=new Timer();
//        t1.schedule(new TimerTask() {
//            @Override
//            public void run() {
                HotClassLoader hotClassLoader = new HotClassLoader("/home/zhonglianxi/zlx/testloader/");
                try {
                    Class<?> aClass = hotClassLoader.loadClass("classloader.test1.HelloLoader");
                    HelloLoader o = (HelloLoader) aClass.newInstance();
                    o.print();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
//
//            }
//        },2*1000);

    }
}

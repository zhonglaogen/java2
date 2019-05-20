package testlock;

public class Deadlock {
    private static String a="aaaa";
    private static String b="bbbb";

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a){
                    System.out.println(Thread.currentThread()+"获得a，等待b");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b){
                        System.out.println(Thread.currentThread()+"获得b");
                    }
                }
            }
        },"线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b){
                    System.out.println(Thread.currentThread()+"获得b，等待a");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (a){
                        System.out.println(Thread.currentThread()+"获得a");
                    }
                }
            }
        },"线程2").start();
    }
}

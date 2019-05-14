package testthreadlocal;

public class TestThreadLocal {
    public static ThreadLocal<Integer> t1=new ThreadLocal<>();
    public static void main(String[] args) {
        LongThread longThread=new LongThread();
        ShortThread shortThread=new ShortThread();
        longThread.start();
        shortThread.start();
    }
}

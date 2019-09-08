public class My  extends Thread implements Runnable{
    @Override
    public void run() {

        System.out.println("run .......");
    }

    public static void main(String[] args) {
        new My().start();
    }
}

import java.util.Date;
import java.util.concurrent.TimeUnit;

class Myt{
    static {
        System.out.println(Myt.i);
    }
    public static int i=5;
    static {
        System.out.println(Myt.i);
    }static {
        i=6;
    }
    static {
        System.out.println(Myt.i);
    }

}
public class Test2 {
    public static void main(String[] args) {
        new Myt();


    }
}

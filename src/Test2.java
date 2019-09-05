import sun.instrument.InstrumentationImpl;

import java.lang.instrument.Instrumentation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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


        Random random = new Random(100);
        Random random1 = new Random(100);
        int i = random.nextInt();
        int ii=random1.nextInt();
        int iii=random.nextInt();

        int i2 = new Random().nextInt(100);
        int i3 = new Random().nextInt(100);
        int i4 = new Random().nextInt(100);
        System.out.println(i);
        System.out.println(ii);
        System.out.println(iii);
//        System.out.println(i2);
//        System.out.println(i3);
//        System.out.println(i4);


    }
}

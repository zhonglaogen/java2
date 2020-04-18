package single;

/**
 * 静态内部类
 */
public class Singe4 {

    private static class MySingle4{
        private static Singe4 s4=new Singe4();
    }

    private Singe4(){}

    public static Singe4 getInstace(){
        return MySingle4.s4;
    }
}

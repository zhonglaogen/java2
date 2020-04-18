package single;

/**
 * 饿汉模式
 */
public class Single1 {
    private static  final Single1 single1=new Single1();
    private Single1(){}
    public static Single1 getInstance(){
        return single1;
    }

}

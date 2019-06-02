package threadlocal;

/**
 * 线程独享的单例
 */
public class SingleThreadlocal {
    private static ThreadLocal<SingleThreadlocal> my=new ThreadLocal<>();
    private SingleThreadlocal(){}
    public static SingleThreadlocal getInstace(){
        SingleThreadlocal singleThreadlocal = my.get();
        if(singleThreadlocal!=null){
            singleThreadlocal=new SingleThreadlocal();
        }
        return singleThreadlocal;
    }
}

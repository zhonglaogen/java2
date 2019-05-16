package single;

public class Single3 {
    private static volatile Single3 s3;//volatile关键字的作用是禁止指令重排
    // ，创建对象时有三步，有可能未初始化完对象，1分配内存，2初始化对象，3指向初始化对象
    private Single3(){}
    public static Single3 getInstace(){
        if(s3==null){
            //二次判断是因为在此处可能切换到其他线程，拿到锁后可能已经将对象创建
            synchronized (Single3.class){
                if(s3==null){
                    s3=new Single3();
                }
            }
        }
        return s3;
    }
}

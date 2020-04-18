package single;

/**
 * 防止反射,反射时发现是枚举就报错,\
 * 序列化时只是序列化名字,不序列化具体内容,反序列化也只是通过名字获取对象
 * 继承枚举类,枚举为静态的final的常量
 */
public enum Single5 {
    INSTANCE;
    private Single5(){}


}

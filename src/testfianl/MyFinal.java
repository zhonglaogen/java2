package testfianl;

/**
 * 1. 通过子类引用父类的静态字段,只会触发父类的初始化,而不会触发子类的初始化。
 * 2. 定义对象数组,不会触发该类的初始化。
 * 3. 常量在编译期间会存入调用类的常量池中,本质上并没有直接引用定义常量的类,不会触
 * 发定义常量所在的类。
 * 4. 通过类名获取 Class 对象,不会触发类的初始化。
 * 5. 通过 Class.forName 加载指定类时,如果指定参数 initialize 为 false 时,也不会触发类初
 * 始化,其实这个参数是告诉虚拟机,是否要对类进行初始化。
 * 6. 通过 ClassLoader 默认的 loadClass 方法,也不会触发初始化动作。
 */
public class MyFinal {
    static {
        System.out.println("class is load");
    }
    public static final int a=500;
}

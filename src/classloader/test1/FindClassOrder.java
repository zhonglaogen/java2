package classloader.test1;

import jdk.nashorn.internal.runtime.linker.Bootstrap;

public class FindClassOrder
{
    /**
     * 测试bootstraploade加载器加载HelloLoader
     * -Xbootclasspath/a:/home/zhonglianxi/zlx/testloader
     * 开始没成功是因为包名不一致，默认加载同包内的类
     * java是用java和c写的
     * @param args
     */
    public static void main(String[] args) {
        HelloLoader loader=new HelloLoader();
        //bootstraploade不继承ClassLoader,是虚拟机自己实现的，
        loader.print();
    }
}

package classloader.test2;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //自定义class位置寻找class，所以找不到Object
            OrderClassLoader myLoader=new OrderClassLoader("/home/zhonglianxi/zlx/testloader/");
            Class clz=myLoader.loadClass("classloader.test1.HelloLoader");
            Object o = clz.newInstance();
            System.out.println(o.getClass().getClassLoader());
            System.out.println(clz.getClassLoader());

            System.out.println("================Class Load Tree=========");
            ClassLoader cl=myLoader;
            while (cl!=null){
                System.out.println(cl);
                cl=cl.getParent();
            }


        }


}

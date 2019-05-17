package classloader.test2;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        OrderClassLoader myLoader=new OrderClassLoader();
        Class clz=myLoader.loadClass("classloader.test1.HelloLoader");
        System.out.println(clz.getClassLoader());

        System.out.println("================Class Load Tree=========");
        ClassLoader cl=myLoader;
        while (cl!=null){
            System.out.println(cl);
            cl=cl.getParent();
        }
    }
}

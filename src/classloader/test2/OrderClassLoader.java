package classloader.test2;

import classloader.test1.FindClassLoader2;

import java.io.IOException;

/**破坏双亲委派
 * 1思想：java自带的loadClass是在内部先findloadClass，没有就在父类中找，都没有，
 * 此时已经在最顶层的classloader，(findClass)就在双亲委派加载class
 * 2而我写的loadClass是直接就findclass，在findclass中找一下当前的findLoadedClass，没有就加载，
 * 加载不了（class属于系统）就return super.loadClass(name,resolve);
 */
public class OrderClassLoader extends ClassLoader {

    private String path;
    public OrderClassLoader(String path){
        this.path=path;
    }
    @Override
    /**
     * resolve在loadClass(String name)默认设为false，在执行此方法
     */
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class re=findClass(name);
        if(re==null){
            System.out.println("无法加载类"+name+"需要请求父加载器1");
            return super.loadClass(name,resolve);
            }
            return re;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //此加载器没有Object类，自己加载也没路径
        Class zz=findLoadedClass(name);
        if(null==zz){
            try {
                //加载字节码的二进制
                String myPath = path + name.replace(".","/") + ".class";
                byte[] bytes = FindClassLoader2.loadClassByte(myPath);
                 zz = defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return zz;
    }
}

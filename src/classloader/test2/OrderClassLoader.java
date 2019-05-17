package classloader.test2;

import classloader.test1.FindClassLoader2;

import java.io.IOException;

public class OrderClassLoader extends ClassLoader {
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class re=findClass("name");
        if(re==null){
            System.out.println("无法加载类"+name+"需要请求父加载器");
            return super.loadClass(name,resolve);
            }
            return re;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class zz=this.findLoadedClass(name);
        if(null==zz){
            try {
                byte[] bytes = FindClassLoader2.loadClassByte(name);
                defineClass(name,bytes,0,bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return zz;
    }
}

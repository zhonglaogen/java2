package classloader.test3;

import classloader.test1.FindClassLoader2;

import java.io.IOException;

public class HotClassLoader extends ClassLoader{
    private String abpath;
    public HotClassLoader(String path){
        this.abpath=path;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class loadedClass=findClass(name);
        if(loadedClass==null){
            System.out.println("无法加载类"+name+"需要请求父加载器1");
          return super.loadClass(name,resolve);
        }
        return loadedClass;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> zz = findLoadedClass(name);
        String path=abpath+name.replace(".","/")+".class";
        if(zz==null){
            try {
                byte[] bytes = FindClassLoader2.loadClassByte(path);
                zz = defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return zz;
    }


}

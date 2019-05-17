package classloader.test1;
import io.Testrandom;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.junit.Test;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class FindClassLoader2
{

    //在definclass时生成class对象
    public static void main(String[] args)throws Exception {
        String path="/home/zhonglianxi/IdeaProjects/java2/out/production/java2/classloader/test1/HelloLoader.class";
        ClassLoader cl=FindClassLoader2.class.getClassLoader();
        byte[] bHelloLoader=loadClassByte(path);
        Method md_defineClass=ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        md_defineClass.setAccessible(true);
        md_defineClass.invoke(cl,bHelloLoader,0,bHelloLoader.length);

        HelloLoader loader=new HelloLoader();
        System.out.println(loader.getClass().getClassLoader());
        loader.print();
    }
    //拿到class文件的字节数组
 public   static byte[] loadClassByte(String path) throws IOException {
       FileInputStream is=new FileInputStream(path);
       BufferedInputStream bis = new BufferedInputStream(is );
       try {
           int length = is.available();
           byte[] bs = new byte[length];
           System.err.println("ddd:" + bs.length);
           bis.read(bs);

           // is.close();
           return bs;
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           bis.close();
       }
       return null;
    }

}


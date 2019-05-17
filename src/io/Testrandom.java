package io;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 任意位置的读写
 * model各个参数详解
 * r 代表以只读方式打开指定文件
 * rw 以读写方式打开指定文件
 * rws 读写方式打开，并对内容或元数据都同步写入底层存储设备,rw只在buffer满或close的时候才写入，这个在write就写了
 * rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
 * raf.seek(pointe);//移动文件指针位置raf.seek(raf.length());
 * 功能一：读取任意位置的数据
 * 功能二：追加数据
 * 功能三：任意位置插入数据，创建一个缓冲文件，将要插入后面的内容存入缓冲区，插入内容后再将缓冲内容追加回来
 **/
public class Testrandom {
    @Test
    public void bioReadFile(String path) {
        File file = new File(path);
        RandomAccessFile rs = null;
        try {
            rs = new RandomAccessFile(file, "rw");
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = rs.read(b)) != -1) {
                System.out.print(new String(b, 0, len));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void nioReadFile(String name) {
        RandomAccessFile rw = null;
        try {
            rw = new RandomAccessFile(name, "rw");
            FileChannel fc = rw.getChannel();
            ByteBuffer bf = ByteBuffer.allocateDirect(1024);
//            ByteBuffer.wrap("ssss".getBytes());//创建指定大小的缓冲区
            int bytesRead = fc.read(bf);
            while (bytesRead != -1) {
                bf.flip();
                while (bf.hasRemaining()) {
                    byte b = bf.get();
                }
                bf.compact();
                bytesRead = fc.read(bf);
            }
            fc.close();
            rw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

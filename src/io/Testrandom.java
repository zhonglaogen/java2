package io;

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
        String file = Testrandom.class.getResource("xx").getFile();
        RandomAccessFile rw = null;
        try {
            rw = new RandomAccessFile(file, "rw");
            FileChannel fc = rw.getChannel();
            ByteBuffer bf = ByteBuffer.allocateDirect(1024);
//            ByteBuffer.wrap("ssss".getBytes());//创建指定大小的缓冲区
            int bytesRead = fc.read(bf);

            while (bytesRead != -1) {
                // 切换为读取模式
                bf.flip();
                byte[] res = new byte[bf.limit()];
                while (bf.hasRemaining()) {
                    bf.get(res);
                    System.out.println(new String(res));
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

    public static void main(String[] args) {
        nioReadFile("xx");
    }

    /**
     *
     // capacicty:作为一个内存块，Buffer有一个固定的大小值，也叫“capacity”.
     // 你只能往里写capacity个byte、long，char等类型。一旦Buffer满了，需要将其清空（通过读数据或者清除数据）才能继续写数据往里写数据。

     // position
     // 当你写数据到Buffer中时，position表示当前的位置。初始的position值为0.当一个byte、long等数据写到Buffer后，
     // position会向前移动到下一个可插入数据的Buffer单元。position最大可为capacity – 1.
     // 当读取数据时，也是从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0.
     // 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置。

     // limit
     // 在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。
     // 当切换Buffer到读模式时，
     // limit表示你最多能读到多少数据。因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值。
     // 换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）

     // flip
     // flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
     // 换句话说，position现在用于标记读的位置，limit表示之前写进了多少个byte、char等 —— 现在能读取多少个byte、char等。

     // rewind
     // 将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。

     // 一旦读完Buffer中的数据，需要让Buffer准备好再次被写入。可以通过clear()或compact()方法来完成。
     // clear
     // 如果调用的是clear()方法，position将被设回0，limit被设置成 capacity的值。换句话说，Buffer
     // 被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。
     // 如果Buffer中有一些未读的数据，调用clear()方法，数据将“被遗忘”，意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有。
     // compact
     // 如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。
     // compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。

     */

}

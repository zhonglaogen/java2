package niginx;

import com.sun.corba.se.impl.io.OutputStreamHook;
import javafx.beans.binding.ObjectExpression;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//tomcat就是根据tcp
 class ProxyServer //发送端
{
    private int totalServer;
    private int connectCount;
    public ProxyServer(int totalServer){
        this.totalServer=totalServer;
    }
    public void start()throws Exception {
        {
             ExecutorService  es = Executors.newCachedThreadPool();//可缓存线程池
            ServerSocket serverSocket = null;
            Socket cs = null;
            try {
                serverSocket = new ServerSocket(59999);
                while (true) {
                    cs = serverSocket.accept();
                    connectCount+=1;
                    int serverPort=connectCount%totalServer;
                    es.execute(new RequestProxThread(cs,serverPort));
                }
            } finally {
                serverSocket.close();
            }

        }
    }

    public static void main(String[] args) throws Exception {

        ProxyServer p1=new ProxyServer(2);
        p1.start();


    }

}

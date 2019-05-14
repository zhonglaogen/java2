package ch1.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPChatServer {
    private ServerSocket serverSocket = null;
    private List<Socket> sockets;

    public TCPChatServer() {
        sockets=new ArrayList<>();
        ExecutorService es= Executors.newCachedThreadPool();//可缓存线程池
        try {
            serverSocket = new ServerSocket(5432);//创建服务器端套接字
            while (true){
                Socket s=serverSocket.accept();//监听并接收客户端的连接。此方法在连接传入前一直阻塞
                sockets.add(s);
                es.execute(new MyThread(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)  {
        new TCPChatServer();


    }
    class MyThread extends Thread{
        private Socket s;
        private PrintWriter pw;
        private BufferedReader br;
        String msg;
        public MyThread(Socket socket) {
            this.s=socket;
        }

        @Override
        public void run() {

            try {
//                new ReceiveMsgThread(s).start();//启动专门监听对方发送消息的线程,（有几率的）将消息显示到服务器，但是就不能显示到客户端
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                msg="用户共计"+sockets.size();
                sendMsg();
                while ((msg=br.readLine())!=null){//一直监听
                    sendMsg();
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != s) {
                    try {
                        s.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (null != serverSocket) {
                    try {
                        serverSocket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }
        public void sendMsg() {
            try {
                //System.out.println(msg);

                for (int i = sockets.size() - 1; i >= 0; i--) {
                    pw = new PrintWriter(sockets.get(i).getOutputStream(), true);
                    pw.println(msg);
                    System.out.println("ok");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

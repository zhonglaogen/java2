package niginx;

import java.io.*;
import java.net.Socket;

public class RequestProxThread implements Runnable {
    private Socket cs;
    private int port;
    private int serverPort;

    public RequestProxThread(Socket cs,int serverPort) {
        this.cs = cs;
        this. port = cs.getPort();
        this.serverPort=8080+serverPort;
    }

    @Override
    public void run() {
        ObjectInputStream cinput=null;
        PrintWriter out=null;

        BufferedReader bufr=null;
        ObjectOutputStream coutput=null;
        try{
            //接受到客户端请求
            System.out.println(port+"端口:"+"正在接受到客户端请求……");
            Object request=null;
            cinput=new ObjectInputStream(cs.getInputStream());
            request = (Transport)cinput.readObject();
            System.out.println(port+"端口:"+"已经收到客户端请求，正在转发请求至服务器……");


            //转发请求至服务器
            Socket ss=new Socket("localhost",serverPort);
            out=new PrintWriter(ss.getOutputStream(),true);
            String message=  ((Transport) request).getMessage().toString();
            out.println(message);
            System.out.println(port+"端口:"+"请求转发成功，正在接受服务器的响应");

            //接受服务器的响应
            bufr=new BufferedReader(new InputStreamReader(ss.getInputStream()));
            StringBuilder stringBuilder=new StringBuilder();
            String line=null;
            while((line=bufr.readLine())!=null){
                System.out.println(line);
                stringBuilder.append(line+"\n");
            }
            Transport result=new Transport(stringBuilder);
            System.out.println(port+"端口:"+"服务器响应成功");

            //转发请求至客户端
            System.out.println("正在发送响应至客户端……");
            coutput=new ObjectOutputStream(cs.getOutputStream());
            coutput.writeObject(result);
            System.out.println(port+"端口:"+"转发响应结束");


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                coutput.close();
                bufr.close();
                out.close();
                cinput.close();
                cs.close();
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}

package niginx;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s=new Socket("localhost",59999);
//
//        PrintWriter printWriter=new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
//        printWriter.print("send\n");
//        printWriter.close();

        //向代理服务器发送请求
        System.out.println("正在向代理服务器发送请求……");
        ObjectOutputStream cliOut=new ObjectOutputStream(s.getOutputStream());
        StringBuilder reqMessage=new StringBuilder();
        reqMessage.append("GET /hello.html HTTP/1.1\n");
        reqMessage.append("Accept: */*\n");
        reqMessage.append("Accept-Language: zh-cn\n");
        reqMessage.append("Host:172.16.99.99:12000\n");
        reqMessage.append("Connection:Keep-Alive\n");
        Transport transport=new Transport(reqMessage);
        cliOut.writeObject(transport);
        System.out.println("发送请求成功，等待响应……");

        //接收到的代理服务起相应
        ObjectInputStream cliIn=new ObjectInputStream(s.getInputStream());
        Object res = (Transport)cliIn.readObject();
        String resMessage = ((Transport) res).getMessage().toString();
        System.out.println(resMessage);
        System.out.println("请求结束");

        cliIn.close();
        cliOut.close();

    }
}

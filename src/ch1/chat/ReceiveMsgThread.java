package ch1.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveMsgThread extends Thread {
    private Socket s;
    public ReceiveMsgThread(Socket s){
        this.s=s;
    }
    @Override
    public void run() {
        BufferedReader in=null;
        try {
            in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            while (true){
                System.out.println("@@@@"+in.readLine()+"&&&&&");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

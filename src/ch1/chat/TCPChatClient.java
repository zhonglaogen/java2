package ch1.chat;

import java.io.*;
import java.net.Socket;

public class TCPChatClient {
    public static void main(String[] args) {
        Socket s = null;

        try {
            s = new Socket("127.0.0.1", 5432);
            new ReceiveMsgThread(s).start();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                out.println(br.readLine());
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
        }

    }
}
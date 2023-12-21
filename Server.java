package compro2.ex;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        Scanner scanner = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("연결을 기다리고 있음");
            socket = serverSocket.accept();
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("클라이언트와 연결되었음");

            while (true) {
                String msg = in.readLine();
                if (msg.equalsIgnoreCase("quit")) {
                    System.out.println("클라이언트에서 연결을 종료하였음");
                    break;
                }
                System.out.println("클라이언트가 보낸 문자열 : " + msg);
                System.out.print("클라이언트로 보낼 문자열을 입력하고 엔터키를 치세요 : ");
                String omsg = scanner.nextLine();
                out.write(omsg + "\n");
                out.flush();
            }
            out.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

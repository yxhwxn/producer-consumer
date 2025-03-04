package main.src.step05_socket;

import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("서버 소켓 생성, 서버가 시작되었습니다. 클라이언트를 기다립니다.");

        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었습니다.");
    }

}

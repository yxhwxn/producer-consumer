package main.src.step05_socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
    public static void main(String[] args) {
        final String SIMPLE_SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 5555;

        // 서버에 연결 시도
        try {
            Socket socket = new Socket(SIMPLE_SERVER_IP, SERVER_PORT);
            System.out.println("서버에 연결되었습니다.");

        } catch (UnknownHostException e) {
            System.out.println("서버를 찾을 수 없습니다: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("서버에 연결 대기 중입니다.");
        }
    }
}


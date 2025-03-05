package main.src.step05_socket.multi_socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 멀티 클라이언트를 처리할 수 있는 서버
 */
public class MultiServer {
    public static boolean isClientConnected = false; // 클라이언트 연결 상태

    public static void main(String[] args) {
        int port = 12345; // 서버 포트
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 시작되었습니다. 포트: " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                isClientConnected = true; // 클라이언트 연결 상태 설정
                System.out.println("클라이언트 연결: " + clientSocket.getInetAddress());

                // 클라이언트와의 통신을 위한 스레드 생성
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

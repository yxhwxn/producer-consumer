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
    private static final int PORT = 5555;
    private static final int THREAD_POOL_SIZE = 10; // 최대 동시 클라이언트 수 제한

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE); // 스레드 풀 생성

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 시작되었습니다. 클라이언트를 기다립니다...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 클라이언트 연결 대기
                System.out.println("클라이언트 연결됨: " + clientSocket.getInetAddress());

                // 새로운 클라이언트 처리 스레드 실행
                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

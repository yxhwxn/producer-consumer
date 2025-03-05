package main.src.step05_socket.thread_pool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread Pool을 활용한 멀티 클라이언트 서버
 */
public class MultiServer {
    private static final int PORT = 12345; // 서버 포트
    private static final int THREAD_POOL_SIZE = 10; // 최대 동시 클라이언트 수 제한
    private static boolean isClientConnected = false; // 클라이언트 연결 상태

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE); // 스레드 풀 생성

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 시작되었습니다. 포트: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                isClientConnected = true; // 클라이언트 연결 상태 설정
                System.out.println("클라이언트 연결: " + clientSocket.getInetAddress());

                // 클라이언트 요청을 스레드 풀에서 실행
                threadPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown(); // 서버 종료 시 스레드 풀 종료
        }
    }
}

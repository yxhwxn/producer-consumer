package main.src.step05_socket.thread_pool_waiting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiServer {
    private static final int MAX_THREADS = 2; // 최대 스레드 수
    private static final int MAX_QUEUE_SIZE = 5; // 대기 큐 크기
    private static ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREADS); // 스레드 풀 생성
    private static ArrayBlockingQueue<Socket> waitingQueue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE); // 대기 큐

    public static void main(String[] args) {
        int port = 12345; // 서버 포트
        // 대기 클라이언트 처리 스레드 시작
        new Thread(MultiServer::handleWaitingClients).start();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 시작되었습니다. 포트: " + port);
            while (true) {
                // 실제 accept() 시스템 콜 호출 부분? -> PlainSocketImpl.socketAccept()의 accept0
                Socket clientSocket = serverSocket.accept();
                String clientAddress = clientSocket.getInetAddress().getHostAddress();
                int clientPort = clientSocket.getPort(); // 클라이언트 포트 번호
                System.out.println(clientAddress + ":" + clientPort + "클라이언트로부터 연결 시도");

                // 현재 활성화된 스레드 수를 체크
                if (threadPool.getActiveCount() < MAX_THREADS) {
                    threadPool.execute(new ClientHandler(clientSocket));
                    System.out.println(clientAddress + ":" + clientPort + " 클라이언트가 서버에 연결되었습니다");
                } else {
                    if (waitingQueue.offer(clientSocket)) {
                        System.out.println(clientAddress + ":" + clientPort + " 클라이언트가 서버에 연결 대기 중: ");
                    } else {
                        System.out.println("대기 큐가 가득 차서 연결을 거부합니다: " + clientAddress + ":" + clientPort);
                        clientSocket.close(); // 큐가 가득 차면 연결 종료
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleWaitingClients() {
        while (true) {
            try {
                Socket clientSocket = waitingQueue.take(); // 대기 큐에서 클라이언트 소켓을 가져옴
                if (clientSocket != null) {
                    threadPool.execute(new ClientHandler(clientSocket));
                    String clientAddress = clientSocket.getInetAddress().getHostAddress();
                    int clientPort = clientSocket.getPort();
                    // System.out.println(clientAddress + ":" + clientPort + " 클라이언트가 서버에 연결되었습니다");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

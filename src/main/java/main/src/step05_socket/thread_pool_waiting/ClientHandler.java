package main.src.step05_socket.thread_pool_waiting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * 클라이언트 요청을 처리하는 핸들러 (스레드 풀 사용)
 */
class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        System.out.println("클라이언트 스레드 ID: " + Thread.currentThread().getId()); // 현재 스레드 ID 출력

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("클라이언트 스레드 ID: " + Thread.currentThread().getId() + " 클라이언트로부터 수신: " + inputLine);
                out.println("서버 응답: " + inputLine); // 클라이언트에게 에코 응답
            }
        } catch (SocketException e) {
            System.out.println("클라이언트가 연결을 종료했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("클라이언트 연결 종료: " + clientSocket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

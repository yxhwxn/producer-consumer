package main.src.step05_socket.multi_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * 클라이언트 요청을 처리하는 핸들러 (스레드)
 */
class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        System.out.println("클라이언트 스레드 ID: " + Thread.currentThread().getId()); // 스레드 ID 출력

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("클라이언트 스레드 ID: " + Thread.currentThread().getId() + " 클라이언트로부터 수신: " + inputLine);
                out.println(inputLine); // 클라이언트에게 에코
            }
        } catch (SocketException e) {
            System.out.println("클라이언트가 연결을 종료했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("클라이언트 연결 종료: " + clientSocket.getInetAddress());
                SimpleServer.isClientConnected = false; // 연결 종료 시 상태 변경
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

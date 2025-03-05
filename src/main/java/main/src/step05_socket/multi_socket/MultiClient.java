package main.src.step05_socket.multi_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiClient {
    public static void main(String[] args) {
        final String SIMPLE_SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 5555;

        // 서버에 연결 시도
        try {
            Socket socket = new Socket(SIMPLE_SERVER_IP, SERVER_PORT);
            System.out.println("서버에 연결되었습니다.");

            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput); // 서버로 메시지 전송
                    System.out.println("서버로부터 수신: " + in.readLine()); // 서버로부터 에코 수신
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("서버를 찾을 수 없습니다: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("서버에 연결 대기 중입니다.");
        }
    }
}

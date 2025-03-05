package main.src.step05_socket.thread_pool;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 간단한 멀티 클라이언트 프로그램
 */
public class MultiClient {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 12345;

        // 서버에 연결 시도
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("서버에 연결되었습니다. 메시지를 입력하세요!");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput); // 서버로 메시지 전송
                System.out.println("서버로부터 수신: " + in.readLine()); // 서버 응답 출력
            }

        } catch (UnknownHostException e) {
            System.out.println("서버를 찾을 수 없습니다: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("서버에 연결할 수 없습니다.");
        }
    }
}

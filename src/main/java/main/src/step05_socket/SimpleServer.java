package main.src.step05_socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 소켓 기반의 네트워크 프로그램 중 서버 역할을 수행하는 클래스
 */
public class SimpleServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("서버가 시작되었습니다. 클라이언트를 기다립니다.");

        Socket socket = serverSocket.accept();
        System.out.println("클라이언트가 연결됨");

        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        while (true) {
            try {
                int request = in.read();
                out.write(request);
            }
            catch (IOException e) {
                break;
            }
        }
    }
}

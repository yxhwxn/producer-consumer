package main.src.step01_producer_consumer;

// Thread 관련 메서드를 사용하기 쉽게 추상화
public class ThreadUtil {

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

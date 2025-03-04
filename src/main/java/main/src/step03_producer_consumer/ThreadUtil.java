package main.src.step03_producer_consumer;

/**
 * 스레드 sleep 기능을 제공하는 유틸리티 클래스
 */
public class ThreadUtil {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

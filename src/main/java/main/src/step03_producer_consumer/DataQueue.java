package main.src.step03_producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * `BlockingQueue`를 사용하여 동기화 문제를 해결한 데이터 큐 클래스
 */
public class DataQueue {
    private final BlockingQueue<Message> queue;

    public DataQueue(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity); // 큐(버퍼)의 고정된 최대 크기 지정
    }

    /**
     * 생산자(Producer) - 메시지 추가
     */
    public void add(Message message) {
        try {
            queue.put(message); // 큐가 가득 차면 자동으로 대기
            System.out.println("✅ [Producer] 메시지 추가: " + message.getData() + " (queue size: " + queue.size() + ")");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 소비자(Consumer) - 메시지 가져오기
     */
    public Message poll() {
        try {
            Message message = queue.take(); // 큐가 비어 있으면 자동으로 대기
            System.out.println("\t✅ [Consumer] 메시지 소비: " + message.getData() + " (queue size: " + queue.size() + ")");
            return message;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

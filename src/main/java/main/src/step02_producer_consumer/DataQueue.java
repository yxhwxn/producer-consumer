package main.src.step02_producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 데이터를 적재할 수 있는 고정된 크기의 큐를 가진 클래스
 */
public class DataQueue {
    private final Queue<Message> queue = new LinkedList<>();
    private final int capacity = 5; // 최대 버퍼 크기

    /**
     * 생산자(Producer) - 데이터 추가
     */
    public synchronized void add(Message message) {
        while (queue.size() >= capacity) { // 큐가 가득 찬 경우
            try {
                System.out.println("🚨 [Producer] 큐가 가득 찼음. 대기 중... (queue size: " + queue.size() + ")");
                wait(); // 소비자가 데이터를 소비할 때까지 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.add(message);
        System.out.println("✅ [Producer] 메시지 추가: " + message.getData() + " (queue size: " + queue.size() + ")");
        notifyAll(); // 대기 중인 Consumer들을 깨움
    }

    /**
     * 소비자(Consumer) - 데이터 가져오기
     */
    public synchronized Message poll() {
        while (queue.isEmpty()) { // 큐가 비어 있으면 기다림
            try {
                System.out.println("⚠️  [" + Thread.currentThread().getName() + "] 큐가 비어 있음! 대기 중...");
                wait(); // 새로운 데이터가 추가될 때까지 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Message message = queue.poll();
        System.out.println("\t✅ [" + Thread.currentThread().getName() + "] 메시지 소비: " + message.getData() +
                " (queue size: " + queue.size() + ")");
        notifyAll(); // Producer가 대기 중이면 깨움
        return message;
    }
}

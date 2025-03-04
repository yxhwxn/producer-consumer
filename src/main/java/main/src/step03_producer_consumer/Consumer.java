package main.src.step03_producer_consumer;

/**
 * 소비자 클래스 (데이터 소비)
 */
public class Consumer implements Runnable {
    private final DataQueue dataQueue;

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        consume();
    }

    public void consume() {
        for (int i = 0; i < 5; i++) { // 5개의 메시지 소비
            Message message = dataQueue.poll();
            consumeMessage(message);

            // 랜덤 대기 시간 추가 (소비 속도 조절)
            ThreadUtil.sleep((long) (Math.random() * 1500));
        }
    }

    private void consumeMessage(Message message) {
        if (message != null) {
            System.out.println(
                    "📥 [" + Thread.currentThread().getName() + "] 메시지 소비 완료: " + message.getData());
        } else {
            System.out.println("⚠️  [" + Thread.currentThread().getName() + "] 큐가 비어 있음!");
        }
    }
}

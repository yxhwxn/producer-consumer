package main.src.step03_producer_consumer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 생산자 클래스 (데이터 생산)
 */
public class Producer implements Runnable {
    private final DataQueue dataQueue;

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // 총 10개의 메시지 생산
            Message message = new Message(generateUniqueString());
            System.out.println("[Producer] 메시지 생산: " + message.getData());
            dataQueue.add(message);

            // 랜덤 대기 시간 추가 (생산 속도 조절)
            ThreadUtil.sleep((long) (Math.random() * 1000));
        }
    }

    private String generateUniqueString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String timePart = sdf.format(new Date());

        Random rand = new Random();
        char randomChar = (char) ('A' + rand.nextInt(26)); // A-Z 범위의 랜덤 알파벳
        return timePart + "-" + randomChar; // ex. "093410-X"
    }
}

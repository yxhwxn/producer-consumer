package main.src.step01_producer_consumer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 생산자, 데이터(Message)를 생산하는 역할을 수행하는 클래스
 *
 * 스레드로 동작하도록 구현(Runnable 인터페이스 구현)
 */
public class Producer implements Runnable {

    // DataQueue를 필드로 작성
    private final DataQueue dataQueue;

    public Producer(DataQueue dataQueue) {
        super();
        this.dataQueue = dataQueue;
    }

    // 프로듀서 스레드가 작업할 내용(run() 내부에 작성)
    @Override
    public void run() {
        produce();
    }

    // 데이터 생산 작업
    public void produce() {
        for (int i = 0; i < 5; i++) {
            Message message = produceMessage();

            // 생산한 메시지를 큐(DataQueue.queue)에 적재
            dataQueue.add(message);

            // 현실성을 위해 랜덤 시간동안 지연
            ThreadUtil.sleep((long) (Math.random() * 100));
        }
    }

    private Message produceMessage() {
        Message message = new Message(generateUniqueString());

        System.out.println(String.format("[%s] 메시지 생산: %s%n", Thread.currentThread().getName(), message.getData()));

        return message;
    }

    private String generateUniqueString() {
        // 현재 시간으로부터 고유한 문자열 생성
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String timePart = sdf.format(new Date());

        // 뒤에 1~2개의 랜덤 대문자 알파벳 추가
        Random rand = new Random();
        int randomLength = rand.nextInt(2) + 1;
        StringBuilder randomChars = new StringBuilder();

        for (int i = 0; i < randomLength; i++) {
            char randomChar = (char) ('A' + rand.nextInt(26)); // A-Z 범위의 대문자 랜덤 선택
            randomChars.append(randomChar);
        }

        return timePart + "-" + randomChars.toString(); // ex. "093410-AB"
    }

}

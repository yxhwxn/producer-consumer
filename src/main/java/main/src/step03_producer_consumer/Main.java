package main.src.step03_producer_consumer;

/**
 * Producer-Consumer 실행 메인 클래스
 */
public class Main {
    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue(5);

        // Producer 1명, Consumer 2명 실행
        Thread producerThread = new Thread(new Producer(dataQueue), "Producer");
        Thread consumerThread1 = new Thread(new Consumer(dataQueue), "Consumer-1");
        Thread consumerThread2 = new Thread(new Consumer(dataQueue), "Consumer-2");

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}

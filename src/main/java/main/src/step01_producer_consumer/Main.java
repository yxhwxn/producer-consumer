package main.src.step01_producer_consumer;

public class Main {

    public static void main(String[] args) {
        // 데이터 큐 생성
        DataQueue dataQueue = new DataQueue();

        // Producer 객체와 Producer 스레드 생성
        Producer producer = new Producer(dataQueue);
        Thread producerThread = new Thread(producer, "Producer");

        // Consumer 객체와 Consumer 스레드 생성
        Consumer consumer = new Consumer(dataQueue);
        Thread consumerThread = new Thread(consumer, "Consumer");

        // Producer, Consumer 스레드 시작
        producerThread.start();
        consumerThread.start();
    }

}

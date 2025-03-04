package main.src.step03_producer_consumer;

/**
 * 생산, 소비할 데이터를 추상화한 클래스
 */
public class Message {
    private String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Message [data=" + data + "]";
    }
}

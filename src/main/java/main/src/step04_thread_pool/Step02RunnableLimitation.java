package main.src.step04_thread_pool;

/**
 * Runnable의 반환 타입은 아래와 같이 void 이기 때문에
 *
 * @FunctionalInterface
 * public interface Runnable {
 *     /**
 *      * When an object implementing interface {@code Runnable} is used
 *      * to create a thread, starting the thread causes the object's
 *      * {@code run} method to be called in that separately executing
 *      * thread.
 *      * <p>
 *      * The general contract of the method {@code run} is that it may
 *      * take any action whatsoever.
 *      *
 *      * @see     java.lang.Thread#run()
 *      *
 *        public abstract void run();
 * }
 *
 * 스레드의 작업 수행 결과값을 반환받으려면??
 * 스레드의 작업 수행 결과값을 반환받기 위해서는 별도의 변수가 필요(result라는 변수)
 */
public class Step02RunnableLimitation {

    private static String result;

    public static void main(String[] args) {

        Runnable runnableTask = new Runnable() {
            public void run() {
                result = "Runnable 실행 결과";
            }
        };

        Thread thread = new Thread(runnableTask);
        thread.start();

        try {
            thread.join(); // thread의 작업이 끝날때 까지 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(result); // 메인 스레드에서 결과 확인
    }

}

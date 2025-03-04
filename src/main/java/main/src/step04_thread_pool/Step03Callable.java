package main.src.step04_thread_pool;

import java.util.concurrent.Callable;

/**
 * Callable은 Runnable과 다르게 V call()을 통해 스레드의 작업 수행 결과값을
 * 반환받을 수 있음
 */
public class Step03Callable {
    public static void main(String[] args) {

        Callable<String> callableTask = new Callable<String>() {
            public String call() {
                return "Callable 작업 결과";
            }
        };

        // Callable 실행
        try {
            // call()은 run()과 유사하기 때문에 직접 호출하진 않음, 이후에는 스레드풀에 제출하여 실행됨
            String result = callableTask.call();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

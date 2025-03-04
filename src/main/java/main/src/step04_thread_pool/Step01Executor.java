package main.src.step04_thread_pool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
        * 기존 스레드 활용 방식
 * Runnable을 통해 스레드 객체를 생성하고, 실행을 하는 등 직접적인 관리
 *
         * 스레드 풀을 통해 각 스레드가 수행할 작업을 제출만 하면됨
 * 실제 작업의 실행은 개발자가 하는 것이 아니라, 내장 라이브러리가 해줌
 *
         * Exeuctor 인터페이스
 * - 실제 스레드의 작업 실행을 담당하는 역할
 */
public class Step01Executor {

    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();

        Runnable thread = () -> System.out.println("스레드 작업 수행");

        // exeuctor(실행자)에게 스레드가 수행할 작업을 제출
        executor.execute(thread);// 이 라인에 브레이크포인트
        /**
         * void execute(Runnable command);
         * 실제 스레드의 작업 실행은 execute() 내부에서 수행됨
         * 실제 스레드를 실행하는 코드를 execute() 내부에서 확인
         *
         */

        /**
         * execute() 내부에서 실제 스레드가 실행되는 코드는 어디에 있을까?
         * executor.execute(thread)에 브레이크 포인트 걸고 실행하면 다음과 같은 스택으로 따라갈 수 있음
         *  Thread.start() line: 799
         ThreadPoolExecutor.addWorker(Runnable, boolean) line: 945
         ThreadPoolExecutor.execute(Runnable) line: 1353
         Executors$FinalizableDelegatedExecutorService(Executors$DelegatedExecutorService).execute(Runnable) line: 721
         Step01Executor.main(String[]) line: 16

         */

        /*
         * ThreadPoolExecutor?
         *
         * AbstractExecutorService를 상속,
         * - public class ThreadPoolExecutor extends AbstractExecutorService {
         *
         * AbstractExecutorService는 ExecutorService 인터페이스를 구현
         * public abstract class AbstractExecutorService implements ExecutorService {
         *
         * ExecutorService는 Executor 인터페이스를 상속
         * public interface ExecutorService extends Executor {
         *
         * 이러한 ExecutorService의 구현체는 팩토리 클래스인 Executors를 통해 생성 가능
         * ex. Executors.newSingleThreadExecutor(); -> 이 메서드의 반환타입은 ExecutorService
         *
         */
    }

}

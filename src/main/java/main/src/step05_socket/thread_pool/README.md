## Thread Pool Model

1. Thread Pool (Executors.newFixedThreadPool(10)) 사용

   - 서버가 최대 10개의 클라이언트를 동시에 처리 가능.
   - 불필요한 스레드 생성을 방지하고 서버의 리소스 낭비를 최소화.

2. 각 클라이언트의 요청을 ClientHandler (Runnable)에서 처리

   - Thread 대신 Runnable을 사용하여 스레드 생성 비용 절감.
   - 불필요한 스레드 생성 없이 클라이언트 요청을 효율적으로 분배.

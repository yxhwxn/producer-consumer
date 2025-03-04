## 1. Producer-Consumer 동기화 문제 해결

### [Step01 ~ Step03]

> producer-consumer 동시성 처리(단계별로 어떻게 발전되는지 확인)

- **`step01`** : prodecer-consumer 문제 발생 코드
- **`step02`** : `notify()`, `wait()`, `synchronized`를 이용한 동기화 이슈 해결 코드
- **`step03`** : `BlockingQueue`를 이용한 동기화 이슈 해결 코드

## 2. Thread Pool

### [Step04]
> thread pool 관리

- ExecutorService를 이용한 thread pool 생성
- Runnable 객체를 이용한 작업 처리
- Runnable 객체를 이용한 작업 처리 결과를 Future 객체로 받아오기
- Runnable vs Callable

### [Step05]

> socket을 활용한 simple server, simple client 구현(단일 클라이언트 처리)

- ServerSocket을 이용한 simple server 구현
- Step05에서는 1:1 클라이언트 처리만 가능
- N:N 클라이언트 처리를 위해서는 multi-threading이 필요
  - 다음 step에서 진행됨
    > _**점차 발전하는 과정에서 thread pool이 어떻게 쓰이는지 확인하기!**_

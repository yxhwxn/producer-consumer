# Step02 개선 방향

- 매번 개발자들이 동시성 프로그래밍을 할 때마다 동일한 코드를 작성하는 것은 비효율적이다.
- 따라서, 동시성 프로그래밍을 위한 공통 코드를 라이브러리로 만들어 제공하는 것이 때로는 좋을 수 있다.
- step03에서는 동시성 프로그래밍을 위한 공통 코드를 라이브러리인, BlockingQueue를 이용하여 생산자-소비자 문제를 해결하는 방법을 알아본다.

> Java Thread API 쓰지 않고, BlockingQueue 이용

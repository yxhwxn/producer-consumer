## 요청당 스레드 모델(One-to-One Threading-Model)

> 해당 패키지의 서버 방식은 단일 클라이언트를 처리할 수 있는 방법

> 요청이 올 때마다 스레드를 하나씩 생성해서 할당하는 방식 </br>
> **`new ClientHandler(clientSocket).start();`**

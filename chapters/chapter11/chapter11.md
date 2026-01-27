# Chapter 11. 멀티 스레드 (Multi Thread)

> 스레드는 프로세스 내에서 실행되는 독립적인 실행 흐름

---

## 프로세스와 스레드

### 프로세스 (Process)
- 운영체제에서 실행 중인 프로그램
- 독립적인 메모리 공간을 가짐
- 하나 이상의 스레드를 포함

### 스레드 (Thread)
- 프로세스 내에서 실행되는 작업 단위
- 프로세스의 메모리를 공유
- 동시에 여러 작업 수행 가능

```
[프로세스]
├── 코드 영역 (공유)
├── 데이터 영역 (공유)
├── 힙 영역 (공유)
└── 스레드들
    ├── 스레드1 (독립적인 스택)
    ├── 스레드2 (독립적인 스택)
    └── 스레드3 (독립적인 스택)
```

---

## 메인 스레드

### Java 프로그램의 시작점

```java
public class MainThreadExample {
    public static void main(String[] args) {
        // main() 메소드를 실행하는 스레드 = 메인 스레드
        Thread mainThread = Thread.currentThread();
        System.out.println("현재 스레드: " + mainThread.getName());  // main
    }
}
```

### 싱글 스레드 vs 멀티 스레드

```java
// 싱글 스레드 - 순차 실행
public void singleThread() {
    작업1();  // 완료 후
    작업2();  // 실행
    작업3();  // 실행
}

// 멀티 스레드 - 동시 실행
public void multiThread() {
    new Thread(() -> 작업1()).start();
    new Thread(() -> 작업2()).start();
    new Thread(() -> 작업3()).start();
    // 세 작업이 동시에 실행
}
```

---

## 스레드 생성

### 방법 1: Thread 클래스 상속

```java
public class MyThread extends Thread {
    @Override
    public void run() {
        // 스레드가 실행할 코드
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + ": " + i);
            try {
                Thread.sleep(500);  // 0.5초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 사용
MyThread thread = new MyThread();
thread.start();  // run()이 아닌 start() 호출!
```

### 방법 2: Runnable 인터페이스 구현 (권장)

```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

// 사용
Thread thread = new Thread(new MyRunnable());
thread.start();

// 람다로 간단하게
Thread thread2 = new Thread(() -> {
    System.out.println("람다 스레드 실행");
});
thread2.start();
```

### Runnable이 권장되는 이유
1. 다른 클래스 상속 가능 (Java는 단일 상속)
2. 작업과 스레드 분리 → 재사용성 향상
3. 람다 표현식 사용 가능

---

## 스레드 이름과 상태

### 스레드 이름 설정

```java
Thread thread = new Thread(() -> {
    System.out.println(Thread.currentThread().getName());
});
thread.setName("작업-스레드-1");
thread.start();  // "작업-스레드-1" 출력
```

### 스레드 상태 (Thread State)

| 상태 | 설명 |
|------|------|
| NEW | 스레드 객체 생성, 아직 start() 호출 전 |
| RUNNABLE | 실행 중 또는 실행 대기 |
| WAITING | 다른 스레드의 통지를 기다림 |
| TIMED_WAITING | 일정 시간 대기 (sleep, wait) |
| BLOCKED | 락 획득 대기 |
| TERMINATED | 실행 완료 |

```java
Thread.State state = thread.getState();
System.out.println("상태: " + state);
```

---

## 동기화 (synchronized)

### 동기화가 필요한 이유

```java
public class Counter {
    private int count = 0;

    // 동기화 없이 - 경쟁 상태 발생
    public void increment() {
        count++;  // 읽기 → 증가 → 저장 (3단계, 중간에 다른 스레드 끼어들 수 있음)
    }
}
```

### 메소드 동기화

```java
public class Counter {
    private int count = 0;

    // synchronized 키워드로 한 번에 한 스레드만 실행
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

### 블록 동기화

```java
public class Counter {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        // 특정 블록만 동기화 (성능상 유리)
        synchronized (lock) {
            count++;
        }
    }
}
```

### 동기화 주의점
- 동기화 범위는 최소화 (성능 저하 방지)
- 데드락(교착 상태) 주의
- 가능하면 `java.util.concurrent` 패키지 사용

---

## 스레드 풀 (ExecutorService)

### 스레드 풀이 필요한 이유
- 스레드 생성/소멸 비용 절감
- 스레드 개수 제한으로 시스템 자원 관리
- 작업 큐를 통한 효율적인 작업 분배

### 스레드 풀 생성

```java
import java.util.concurrent.*;

// 고정 크기 스레드 풀
ExecutorService executor = Executors.newFixedThreadPool(4);

// 캐시 스레드 풀 (필요시 생성, 유휴 시 제거)
ExecutorService executor2 = Executors.newCachedThreadPool();

// 단일 스레드
ExecutorService executor3 = Executors.newSingleThreadExecutor();
```

### 작업 제출

```java
ExecutorService executor = Executors.newFixedThreadPool(2);

// Runnable 작업 (리턴값 없음)
executor.execute(() -> {
    System.out.println("작업 실행: " + Thread.currentThread().getName());
});

// Callable 작업 (리턴값 있음)
Future<Integer> future = executor.submit(() -> {
    Thread.sleep(1000);
    return 100;
});

// 결과 받기 (블로킹)
Integer result = future.get();
System.out.println("결과: " + result);

// 종료
executor.shutdown();
```

### 여러 작업 처리

```java
ExecutorService executor = Executors.newFixedThreadPool(3);

List<Future<String>> futures = new ArrayList<>();
for (int i = 0; i < 5; i++) {
    int taskId = i;
    Future<String> future = executor.submit(() -> {
        Thread.sleep(1000);
        return "작업 " + taskId + " 완료";
    });
    futures.add(future);
}

// 모든 결과 수집
for (Future<String> future : futures) {
    System.out.println(future.get());
}

executor.shutdown();
```

---

## Spring에서의 활용

### @Async 비동기 처리

```java
@Service
public class EmailService {

    @Async  // 별도 스레드에서 실행
    public void sendEmail(String to, String content) {
        // 시간이 오래 걸리는 이메일 발송
        // 메인 스레드는 대기하지 않고 바로 리턴
    }

    @Async
    public CompletableFuture<String> sendEmailWithResult(String to) {
        // 결과가 필요한 경우
        return CompletableFuture.completedFuture("전송 완료");
    }
}
```

### Spring의 스레드 풀 설정

```java
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}
```

---

## 정리

| 개념 | 설명 |
|------|------|
| Thread | 프로세스 내 실행 단위 |
| Runnable | 작업 정의 인터페이스 |
| synchronized | 동기화로 경쟁 상태 방지 |
| ExecutorService | 스레드 풀 관리 |
| Future | 비동기 작업 결과 |
| @Async (Spring) | 간편한 비동기 처리 |

---

## 다음 단계
→ Chapter 12: 컬렉션 자료구조

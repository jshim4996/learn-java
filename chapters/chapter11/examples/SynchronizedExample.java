// 동기화 예제 - 경쟁 상태 해결
public class SynchronizedExample {

    public static void main(String[] args) throws InterruptedException {
        // 동기화 없는 카운터 (문제 발생)
        System.out.println("=== 동기화 없음 ===");
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        runCounterTest(unsafeCounter);

        // 동기화된 카운터
        System.out.println("\n=== 동기화 있음 ===");
        SafeCounter safeCounter = new SafeCounter();
        runCounterTest(safeCounter);
    }

    private static void runCounterTest(Counter counter) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();  // t1 종료 대기
        t2.join();  // t2 종료 대기

        System.out.println("기대값: 20000, 실제값: " + counter.getCount());
    }
}

interface Counter {
    void increment();
    int getCount();
}

// 동기화 없는 카운터 - 경쟁 상태 발생
class UnsafeCounter implements Counter {
    private int count = 0;

    @Override
    public void increment() {
        count++;  // 읽기 → 증가 → 저장 (3단계, 중간에 끼어들 수 있음)
    }

    @Override
    public int getCount() {
        return count;
    }
}

// 동기화된 카운터
class SafeCounter implements Counter {
    private int count = 0;

    @Override
    public synchronized void increment() {
        count++;  // 한 번에 한 스레드만 실행
    }

    @Override
    public synchronized int getCount() {
        return count;
    }
}

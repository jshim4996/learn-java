import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// 스레드 풀 (ExecutorService) 예제
public class ExecutorExample {

    public static void main(String[] args) throws Exception {
        // 1. 고정 크기 스레드 풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("=== 스레드 풀 생성 (크기: 3) ===\n");

        // 2. Runnable 작업 제출 (리턴값 없음)
        System.out.println("--- Runnable 작업 ---");
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.execute(() -> {
                System.out.println("작업 " + taskId + " 실행 중 - " + Thread.currentThread().getName());
                sleep(500);
            });
        }

        Thread.sleep(3000);  // 작업 완료 대기

        // 3. Callable 작업 제출 (리턴값 있음)
        System.out.println("\n--- Callable 작업 ---");
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            int taskId = i;
            Future<String> future = executor.submit(() -> {
                sleep(1000);
                return "작업 " + taskId + " 완료!";
            });
            futures.add(future);
        }

        // 결과 수집
        for (Future<String> future : futures) {
            String result = future.get();  // 블로킹: 결과 올 때까지 대기
            System.out.println(result);
        }

        // 4. 스레드 풀 종료
        System.out.println("\n--- 스레드 풀 종료 ---");
        executor.shutdown();  // 새 작업 거부, 기존 작업 완료 후 종료

        if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("모든 작업 완료!");
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

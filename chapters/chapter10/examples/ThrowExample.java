/**
 * Chapter 11 - throw/throws 예제
 *
 * 실행: javac ThrowExample.java && java ThrowExample
 */
public class ThrowExample {
    public static void main(String[] args) {
        // === throw로 예외 발생 ===
        System.out.println("=== throw 예제 ===");
        try {
            validateAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("검증 실패: " + e.getMessage());
        }

        try {
            validateAge(25);
            System.out.println("나이 검증 성공!");
        } catch (IllegalArgumentException e) {
            System.out.println("검증 실패: " + e.getMessage());
        }

        // === throws로 예외 떠넘기기 ===
        System.out.println("\n=== throws 예제 ===");
        try {
            processFile("data.txt");
        } catch (Exception e) {
            System.out.println("파일 처리 실패: " + e.getMessage());
        }

        // === 예외 체이닝 ===
        System.out.println("\n=== 예외 체이닝 ===");
        try {
            loadConfiguration();
        } catch (RuntimeException e) {
            System.out.println("설정 로드 실패: " + e.getMessage());
            System.out.println("원인: " + e.getCause().getMessage());
        }

        // === 예외 다시 던지기 ===
        System.out.println("\n=== 예외 다시 던지기 ===");
        try {
            processWithLogging();
        } catch (Exception e) {
            System.out.println("최종 처리: " + e.getMessage());
        }
    }

    // throw 예제
    static void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("나이는 0 이상이어야 합니다: " + age);
        }
        if (age > 150) {
            throw new IllegalArgumentException("나이가 너무 큽니다: " + age);
        }
    }

    // throws 예제 (예외 떠넘기기)
    static void processFile(String path) throws Exception {
        if (!path.endsWith(".txt")) {
            throw new Exception("txt 파일만 처리 가능합니다");
        }
        System.out.println("파일 처리 중: " + path);
    }

    // 예외 체이닝
    static void loadConfiguration() {
        try {
            readConfigFile();
        } catch (Exception e) {
            throw new RuntimeException("설정 파일 로드 중 오류", e);
        }
    }

    static void readConfigFile() throws Exception {
        throw new Exception("파일을 읽을 수 없습니다");
    }

    // 예외 다시 던지기 (로깅 후)
    static void processWithLogging() throws Exception {
        try {
            throw new Exception("처리 중 오류 발생");
        } catch (Exception e) {
            System.out.println("[LOG] 에러 기록: " + e.getMessage());
            throw e;  // 다시 던지기
        }
    }
}

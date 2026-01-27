/**
 * Chapter 11 - 기본 예외 처리
 *
 * 실행: javac TryCatchExample.java && java TryCatchExample
 */
public class TryCatchExample {
    public static void main(String[] args) {
        // === 기본 try-catch ===
        System.out.println("=== 기본 try-catch ===");
        try {
            int result = 10 / 0;
            System.out.println("결과: " + result);
        } catch (ArithmeticException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        System.out.println("프로그램 계속 실행\n");

        // === try-catch-finally ===
        System.out.println("=== try-catch-finally ===");
        try {
            String str = null;
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("null 참조 에러!");
        } finally {
            System.out.println("finally는 항상 실행됨\n");
        }

        // === 다중 catch ===
        System.out.println("=== 다중 catch ===");
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[10]);
        } catch (NullPointerException e) {
            System.out.println("null 에러");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 초과: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("기타 예외");
        }

        // === 멀티 catch (Java 7+) ===
        System.out.println("\n=== 멀티 catch ===");
        try {
            // 상황에 따라 다른 예외 발생 가능
            riskyMethod(1);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("예외 처리: " + e.getClass().getSimpleName());
        }

        // === 예외 정보 출력 ===
        System.out.println("\n=== 예외 정보 ===");
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("getMessage: " + e.getMessage());
            System.out.println("클래스: " + e.getClass().getName());
            System.out.println("\nStack Trace:");
            e.printStackTrace();
        }
    }

    static void riskyMethod(int type) {
        if (type == 1) {
            throw new NullPointerException("null 발생");
        } else {
            throw new IllegalArgumentException("잘못된 인자");
        }
    }
}

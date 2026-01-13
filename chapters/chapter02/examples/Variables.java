/**
 * Chapter 02 - 변수 선언 기본
 *
 * 실행: javac Variables.java && java Variables
 */
public class Variables {
    public static void main(String[] args) {
        // 정수 타입
        byte smallNum = 127;
        int count = 1000000;
        long bigNum = 10000000000L;  // L 접미사 필수

        // 실수 타입
        double price = 19.99;
        float rate = 3.14f;          // f 접미사 필수

        // 문자 타입
        char grade = 'A';

        // 논리 타입
        boolean isActive = true;

        // 문자열 타입 (참조 타입)
        String name = "홍길동";

        // var 키워드 (Java 10+)
        var message = "타입 추론";   // String으로 추론
        var number = 42;             // int로 추론

        // 출력
        System.out.println("=== 변수 값 출력 ===");
        System.out.println("이름: " + name);
        System.out.println("점수: " + grade);
        System.out.println("활성: " + isActive);
        System.out.println("가격: " + price);
        System.out.println("큰 숫자: " + bigNum);
    }
}

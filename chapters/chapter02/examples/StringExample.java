/**
 * Chapter 02 - 문자열(String) 다루기
 *
 * 실행: javac StringExample.java && java StringExample
 */
public class StringExample {
    public static void main(String[] args) {
        // === 문자열 생성 ===
        String name = "홍길동";
        String greeting = "안녕하세요";

        // === 문자열 연결 ===
        System.out.println("=== 문자열 연결 ===");
        String message = greeting + ", " + name + "님!";
        System.out.println(message);

        // 숫자와 연결
        int age = 25;
        System.out.println("나이: " + age + "세");

        // === String.format() ===
        System.out.println("\n=== String.format() ===");
        String formatted = String.format("이름: %s, 나이: %d세", name, age);
        System.out.println(formatted);

        double price = 1234.567;
        System.out.printf("가격: %.2f원%n", price);  // 소수점 2자리

        // === 주요 메소드 ===
        System.out.println("\n=== 문자열 메소드 ===");
        String text = "  Hello, Java!  ";

        System.out.println("길이: " + text.length());
        System.out.println("trim: [" + text.trim() + "]");
        System.out.println("대문자: " + text.toUpperCase());
        System.out.println("소문자: " + text.toLowerCase());
        System.out.println("포함여부: " + text.contains("Java"));
        System.out.println("시작여부: " + text.trim().startsWith("Hello"));
        System.out.println("치환: " + text.replace("Java", "World"));
        System.out.println("부분문자열: " + text.trim().substring(0, 5));

        // === 문자열 비교 (중요!) ===
        System.out.println("\n=== 문자열 비교 ===");
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");

        // == 는 참조 비교 (JS와 다름!)
        System.out.println("s1 == s2: " + (s1 == s2));        // true (리터럴 풀)
        System.out.println("s1 == s3: " + (s1 == s3));        // false (다른 객체)

        // equals()로 내용 비교 (권장)
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true

        // === 텍스트 블록 (Java 15+) ===
        System.out.println("\n=== 텍스트 블록 ===");
        String json = """
            {
                "name": "홍길동",
                "age": 25
            }
            """;
        System.out.println(json);
    }
}

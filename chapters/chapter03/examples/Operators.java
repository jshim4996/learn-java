/**
 * Chapter 03 - 연산자 종합
 *
 * 실행: javac Operators.java && java Operators
 */
public class Operators {
    public static void main(String[] args) {
        // === 산술 연산자 ===
        System.out.println("=== 산술 연산자 ===");
        int a = 10, b = 3;
        System.out.println("10 + 3 = " + (a + b));
        System.out.println("10 - 3 = " + (a - b));
        System.out.println("10 * 3 = " + (a * b));
        System.out.println("10 / 3 = " + (a / b));   // 3 (정수 나눗셈!)
        System.out.println("10 % 3 = " + (a % b));

        // 실수 나눗셈
        System.out.println("10.0 / 3 = " + (10.0 / 3));

        // === 증감 연산자 ===
        System.out.println("\n=== 증감 연산자 ===");
        int x = 5;
        System.out.println("x = " + x);
        System.out.println("x++ = " + (x++));  // 5 출력 후 증가
        System.out.println("x = " + x);        // 6
        System.out.println("++x = " + (++x));  // 증가 후 7 출력

        // === 비교 연산자 ===
        System.out.println("\n=== 비교 연산자 ===");
        System.out.println("10 == 3: " + (a == b));
        System.out.println("10 != 3: " + (a != b));
        System.out.println("10 > 3: " + (a > b));
        System.out.println("10 <= 3: " + (a <= b));

        // === 문자열 비교 (중요!) ===
        System.out.println("\n=== 문자열 비교 ===");
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");

        System.out.println("s1 == s2: " + (s1 == s2));        // true
        System.out.println("s1 == s3: " + (s1 == s3));        // false!
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true (권장)

        // === 논리 연산자 ===
        System.out.println("\n=== 논리 연산자 ===");
        boolean p = true, q = false;
        System.out.println("true && false: " + (p && q));
        System.out.println("true || false: " + (p || q));
        System.out.println("!true: " + (!p));

        // === 삼항 연산자 ===
        System.out.println("\n=== 삼항 연산자 ===");
        int score = 85;
        String result = (score >= 60) ? "합격" : "불합격";
        System.out.println("점수 " + score + ": " + result);

        // === 복합 대입 연산자 ===
        System.out.println("\n=== 복합 대입 연산자 ===");
        int num = 10;
        num += 5;  // 15
        System.out.println("10 += 5: " + num);
        num -= 3;  // 12
        System.out.println("-= 3: " + num);
        num *= 2;  // 24
        System.out.println("*= 2: " + num);
    }
}

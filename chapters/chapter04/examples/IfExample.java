/**
 * Chapter 04 - if 조건문
 *
 * 실행: javac IfExample.java && java IfExample
 */
public class IfExample {
    public static void main(String[] args) {
        int score = 85;

        // === 기본 if-else ===
        System.out.println("=== 성적 판정 ===");
        if (score >= 90) {
            System.out.println("A 학점");
        } else if (score >= 80) {
            System.out.println("B 학점");
        } else if (score >= 70) {
            System.out.println("C 학점");
        } else if (score >= 60) {
            System.out.println("D 학점");
        } else {
            System.out.println("F 학점");
        }

        // === 중첩 if ===
        System.out.println("\n=== 중첩 조건 ===");
        int age = 20;
        boolean hasLicense = true;

        if (age >= 18) {
            if (hasLicense) {
                System.out.println("운전 가능");
            } else {
                System.out.println("면허 필요");
            }
        } else {
            System.out.println("나이 미달");
        }

        // === 논리 연산자 활용 ===
        System.out.println("\n=== 논리 연산자 ===");
        if (age >= 18 && hasLicense) {
            System.out.println("운전 가능 (AND 조건)");
        }

        // === 삼항 연산자로 간결하게 ===
        String result = (score >= 60) ? "합격" : "불합격";
        System.out.println("\n결과: " + result);
    }
}

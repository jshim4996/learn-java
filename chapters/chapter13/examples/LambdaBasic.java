import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Chapter 16 - 람다식 기본
 *
 * 실행: javac LambdaBasic.java && java LambdaBasic
 */
public class LambdaBasic {
    public static void main(String[] args) {
        // === 람다식 기본 문법 ===
        System.out.println("=== 람다식 기본 ===");

        // 매개변수 없음
        Runnable hello = () -> System.out.println("Hello, Lambda!");
        hello.run();

        // 매개변수 1개 (괄호 생략 가능)
        Printer printer = msg -> System.out.println("출력: " + msg);
        printer.print("테스트 메시지");

        // 매개변수 2개
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        System.out.println("10 + 5 = " + add.calculate(10, 5));
        System.out.println("10 * 5 = " + multiply.calculate(10, 5));

        // === 블록이 있는 람다 ===
        System.out.println("\n=== 블록 람다 ===");
        Calculator complexCalc = (a, b) -> {
            int result = a + b;
            result = result * 2;
            return result;
        };
        System.out.println("(10 + 5) * 2 = " + complexCalc.calculate(10, 5));

        // === 기존 방식 vs 람다 ===
        System.out.println("\n=== 익명 클래스 vs 람다 ===");

        // 익명 클래스 (기존 방식)
        Comparator<String> comp1 = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };

        // 람다식 (간결)
        Comparator<String> comp2 = (s1, s2) -> s1.length() - s2.length();

        List<String> words = new ArrayList<>(List.of("banana", "apple", "cherry", "date"));
        System.out.println("정렬 전: " + words);
        words.sort(comp2);
        System.out.println("길이순 정렬: " + words);

        // === 컬렉션과 람다 ===
        System.out.println("\n=== 컬렉션과 람다 ===");
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        // forEach
        System.out.print("forEach: ");
        numbers.forEach(n -> System.out.print(n + " "));
        System.out.println();

        // removeIf
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("짝수 제거: " + numbers);

        // replaceAll
        numbers.replaceAll(n -> n * 10);
        System.out.println("10배: " + numbers);
    }
}

// 사용자 정의 함수형 인터페이스
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

@FunctionalInterface
interface Printer {
    void print(String message);
}

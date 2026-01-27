import java.util.List;
import java.util.function.*;

/**
 * Chapter 16 - 표준 함수형 인터페이스
 *
 * 실행: javac FunctionalInterface.java && java FunctionalInterface
 */
public class FunctionalInterface {
    public static void main(String[] args) {
        // === Consumer: 소비 (입력O, 반환X) ===
        System.out.println("=== Consumer ===");
        Consumer<String> printer = s -> System.out.println("출력: " + s);
        printer.accept("Hello Consumer!");

        Consumer<Integer> doubleAndPrint = n -> System.out.println("2배: " + (n * 2));
        doubleAndPrint.accept(5);

        // andThen 체이닝
        Consumer<String> upper = s -> System.out.println(s.toUpperCase());
        Consumer<String> lower = s -> System.out.println(s.toLowerCase());
        upper.andThen(lower).accept("Hello");

        // === Supplier: 공급 (입력X, 반환O) ===
        System.out.println("\n=== Supplier ===");
        Supplier<Double> random = () -> Math.random();
        System.out.println("랜덤: " + random.get());
        System.out.println("랜덤: " + random.get());

        Supplier<String> greeting = () -> "Hello, World!";
        System.out.println(greeting.get());

        // === Function: 변환 (입력O, 반환O) ===
        System.out.println("\n=== Function ===");
        Function<String, Integer> strLength = s -> s.length();
        System.out.println("길이: " + strLength.apply("Hello"));

        Function<Integer, String> intToStr = n -> "숫자: " + n;
        System.out.println(intToStr.apply(42));

        // andThen 체이닝
        Function<String, Integer> getLength = String::length;
        Function<Integer, Integer> doubleIt = n -> n * 2;
        System.out.println("길이 * 2: " + getLength.andThen(doubleIt).apply("Hello"));

        // === BiFunction: 2인자 변환 ===
        System.out.println("\n=== BiFunction ===");
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<String, String, String> concat = (a, b) -> a + b;

        System.out.println("add(10, 20): " + add.apply(10, 20));
        System.out.println("concat: " + concat.apply("Hello, ", "World!"));

        // === Predicate: 조건 검사 (boolean 반환) ===
        System.out.println("\n=== Predicate ===");
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<String> isEmpty = String::isEmpty;

        System.out.println("isPositive(5): " + isPositive.test(5));
        System.out.println("isPositive(-3): " + isPositive.test(-3));
        System.out.println("isEven(4): " + isEven.test(4));
        System.out.println("isEmpty(\"\"): " + isEmpty.test(""));

        // 조합: and, or, negate
        Predicate<Integer> isPositiveEven = isPositive.and(isEven);
        Predicate<Integer> isNegative = isPositive.negate();

        System.out.println("isPositiveEven(4): " + isPositiveEven.test(4));
        System.out.println("isPositiveEven(-4): " + isPositiveEven.test(-4));
        System.out.println("isNegative(-5): " + isNegative.test(-5));

        // === UnaryOperator: 같은 타입 변환 ===
        System.out.println("\n=== UnaryOperator ===");
        UnaryOperator<Integer> square = n -> n * n;
        UnaryOperator<String> addExclaim = s -> s + "!";

        System.out.println("square(5): " + square.apply(5));
        System.out.println("addExclaim: " + addExclaim.apply("Hello"));

        // === BinaryOperator: 같은 타입 2개 연산 ===
        System.out.println("\n=== BinaryOperator ===");
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        BinaryOperator<String> join = (a, b) -> a + " " + b;

        System.out.println("sum(10, 20): " + sum.apply(10, 20));
        System.out.println("join: " + join.apply("Hello", "World"));

        // === 실용 예제 ===
        System.out.println("\n=== 실용 예제 ===");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Predicate로 필터링 조건 정의
        Predicate<Integer> filterCondition = n -> n > 5 && n % 2 == 0;

        System.out.print("5보다 크고 짝수: ");
        for (Integer n : numbers) {
            if (filterCondition.test(n)) {
                System.out.print(n + " ");
            }
        }
        System.out.println();
    }
}

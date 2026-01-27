import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * Chapter 16 - 메소드 참조
 *
 * 실행: javac MethodReference.java && java MethodReference
 */
public class MethodReference {
    public static void main(String[] args) {
        // === 정적 메소드 참조 ===
        System.out.println("=== 정적 메소드 참조 ===");

        // 람다식
        Function<String, Integer> parser1 = s -> Integer.parseInt(s);
        // 메소드 참조
        Function<String, Integer> parser2 = Integer::parseInt;

        System.out.println("parseInt: " + parser2.apply("123"));

        // Math.abs 예제
        Function<Integer, Integer> abs1 = n -> Math.abs(n);
        Function<Integer, Integer> abs2 = Math::abs;
        System.out.println("abs(-10): " + abs2.apply(-10));

        // === 인스턴스 메소드 참조 (클래스::메소드) ===
        System.out.println("\n=== 인스턴스 메소드 참조 ===");

        // 람다식
        Function<String, String> upper1 = s -> s.toUpperCase();
        // 메소드 참조
        Function<String, String> upper2 = String::toUpperCase;

        System.out.println("toUpperCase: " + upper2.apply("hello"));

        // 정렬 예제
        List<String> words = new ArrayList<>(List.of("Banana", "apple", "Cherry"));
        words.sort(String::compareToIgnoreCase);
        System.out.println("정렬: " + words);

        // === 특정 객체의 메소드 참조 ===
        System.out.println("\n=== 특정 객체 메소드 참조 ===");

        // 람다식
        Consumer<String> printer1 = s -> System.out.println(s);
        // 메소드 참조
        Consumer<String> printer2 = System.out::println;

        printer2.accept("Hello from method reference!");

        // 커스텀 객체
        StringProcessor processor = new StringProcessor();
        Consumer<String> process1 = s -> processor.process(s);
        Consumer<String> process2 = processor::process;

        process2.accept("테스트 문자열");

        // === 생성자 참조 ===
        System.out.println("\n=== 생성자 참조 ===");

        // Supplier - 기본 생성자
        Supplier<List<String>> listFactory1 = () -> new ArrayList<>();
        Supplier<List<String>> listFactory2 = ArrayList::new;

        List<String> newList = listFactory2.get();
        newList.add("항목1");
        System.out.println("새 리스트: " + newList);

        // Function - 매개변수 있는 생성자
        Function<String, StringBuilder> sbFactory = StringBuilder::new;
        StringBuilder sb = sbFactory.apply("Hello");
        sb.append(" World");
        System.out.println("StringBuilder: " + sb);

        // 배열 생성자 참조
        Function<Integer, String[]> arrayFactory = String[]::new;
        String[] array = arrayFactory.apply(5);
        System.out.println("배열 길이: " + array.length);

        // === 실용 예제 ===
        System.out.println("\n=== 실용 예제 ===");
        List<String> names = List.of("홍길동", "김철수", "이영희", "박지성");

        // forEach + 메소드 참조
        System.out.println("-- 이름 출력 --");
        names.forEach(System.out::println);

        // map 스타일 변환 (Stream 없이)
        System.out.println("\n-- 길이 변환 --");
        List<Integer> lengths = new ArrayList<>();
        Function<String, Integer> getLength = String::length;
        for (String name : names) {
            lengths.add(getLength.apply(name));
        }
        System.out.println("길이: " + lengths);

        // 정렬 + 메소드 참조
        System.out.println("\n-- 정렬 --");
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort(Comparator.comparing(String::length));
        System.out.println("길이순: " + sortedNames);

        sortedNames.sort(Comparator.comparing(String::length).reversed());
        System.out.println("길이 역순: " + sortedNames);
    }
}

class StringProcessor {
    public void process(String s) {
        System.out.println("처리: [" + s.toUpperCase() + "]");
    }
}

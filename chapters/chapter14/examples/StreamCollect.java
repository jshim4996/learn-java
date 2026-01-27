import java.util.*;
import java.util.stream.*;

/**
 * Chapter 17 - 스트림 수집 연산
 *
 * 실행: javac StreamCollect.java && java StreamCollect
 */
public class StreamCollect {
    public static void main(String[] args) {
        List<String> names = List.of("홍길동", "김철수", "이영희", "박지성", "김연아");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // === toList / toSet ===
        System.out.println("=== toList / toSet ===");
        List<String> list = names.stream()
            .filter(n -> n.startsWith("김"))
            .collect(Collectors.toList());
        System.out.println("김씨 리스트: " + list);

        // Java 16+
        List<String> list2 = names.stream()
            .filter(n -> n.length() == 3)
            .toList();
        System.out.println("3글자 이름: " + list2);

        Set<Integer> set = numbers.stream()
            .map(n -> n % 3)
            .collect(Collectors.toSet());
        System.out.println("mod 3 셋: " + set);

        // === toMap ===
        System.out.println("\n=== toMap ===");
        Map<String, Integer> nameLength = names.stream()
            .collect(Collectors.toMap(
                name -> name,
                name -> name.length()
            ));
        System.out.println("이름-길이: " + nameLength);

        // === joining ===
        System.out.println("\n=== joining ===");
        String joined = names.stream()
            .collect(Collectors.joining(", "));
        System.out.println("콤마 조인: " + joined);

        String formatted = names.stream()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("포맷팅: " + formatted);

        // === groupingBy ===
        System.out.println("\n=== groupingBy ===");
        Map<Integer, List<String>> byLength = names.stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println("길이별 그룹: " + byLength);

        Map<String, List<String>> byFirstChar = names.stream()
            .collect(Collectors.groupingBy(n -> n.substring(0, 1)));
        System.out.println("첫글자별 그룹: " + byFirstChar);

        // 그룹별 개수
        Map<Integer, Long> countByLength = names.stream()
            .collect(Collectors.groupingBy(
                String::length,
                Collectors.counting()
            ));
        System.out.println("길이별 개수: " + countByLength);

        // === partitioningBy ===
        System.out.println("\n=== partitioningBy ===");
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("짝/홀 분할: " + evenOdd);
        System.out.println("짝수: " + evenOdd.get(true));
        System.out.println("홀수: " + evenOdd.get(false));

        // === summarizing ===
        System.out.println("\n=== summarizing ===");
        IntSummaryStatistics stats = numbers.stream()
            .collect(Collectors.summarizingInt(Integer::intValue));

        System.out.println("개수: " + stats.getCount());
        System.out.println("합계: " + stats.getSum());
        System.out.println("평균: " + stats.getAverage());
        System.out.println("최소: " + stats.getMin());
        System.out.println("최대: " + stats.getMax());

        // === reducing ===
        System.out.println("\n=== reducing ===");
        Optional<Integer> sum = numbers.stream()
            .collect(Collectors.reducing(Integer::sum));
        System.out.println("합계: " + sum.orElse(0));

        Integer sumWithInit = numbers.stream()
            .collect(Collectors.reducing(0, Integer::sum));
        System.out.println("합계(초기값): " + sumWithInit);

        // === flatMap + collect ===
        System.out.println("\n=== flatMap ===");
        List<List<Integer>> nested = List.of(
            List.of(1, 2, 3),
            List.of(4, 5),
            List.of(6, 7, 8, 9)
        );

        List<Integer> flat = nested.stream()
            .flatMap(List::stream)
            .toList();
        System.out.println("중첩 리스트: " + nested);
        System.out.println("평탄화: " + flat);

        // 문자열 분리 + 평탄화
        List<String> sentences = List.of("Hello World", "Java Stream", "Lambda Expression");
        List<String> words = sentences.stream()
            .flatMap(s -> Arrays.stream(s.split(" ")))
            .toList();
        System.out.println("문장: " + sentences);
        System.out.println("단어: " + words);
    }
}

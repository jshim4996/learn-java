import java.util.*;
import java.util.stream.*;

/**
 * Chapter 17 - 스트림 기본
 *
 * 실행: javac StreamBasic.java && java StreamBasic
 */
public class StreamBasic {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // === 스트림 생성 ===
        System.out.println("=== 스트림 생성 ===");

        // 컬렉션에서
        Stream<Integer> s1 = numbers.stream();

        // 배열에서
        String[] arr = {"a", "b", "c"};
        Stream<String> s2 = Arrays.stream(arr);

        // 직접 생성
        Stream<String> s3 = Stream.of("x", "y", "z");

        // 범위
        IntStream range = IntStream.rangeClosed(1, 5);
        System.out.println("1~5 합계: " + range.sum());

        // === filter ===
        System.out.println("\n=== filter ===");
        List<Integer> evens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .toList();
        System.out.println("짝수: " + evens);

        List<Integer> bigNumbers = numbers.stream()
            .filter(n -> n > 5)
            .toList();
        System.out.println("5 초과: " + bigNumbers);

        // === map ===
        System.out.println("\n=== map ===");
        List<Integer> doubled = numbers.stream()
            .map(n -> n * 2)
            .toList();
        System.out.println("2배: " + doubled);

        List<String> names = List.of("홍길동", "김철수", "이영희");
        List<Integer> lengths = names.stream()
            .map(String::length)
            .toList();
        System.out.println("이름 길이: " + lengths);

        // === filter + map 조합 ===
        System.out.println("\n=== filter + map 조합 ===");
        List<Integer> result = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 10)
            .toList();
        System.out.println("짝수 * 10: " + result);

        // === sorted ===
        System.out.println("\n=== sorted ===");
        List<Integer> shuffled = List.of(5, 2, 8, 1, 9, 3);

        List<Integer> sorted = shuffled.stream()
            .sorted()
            .toList();
        System.out.println("오름차순: " + sorted);

        List<Integer> reversed = shuffled.stream()
            .sorted(Comparator.reverseOrder())
            .toList();
        System.out.println("내림차순: " + reversed);

        // === distinct ===
        System.out.println("\n=== distinct ===");
        List<Integer> duplicates = List.of(1, 2, 2, 3, 3, 3, 4);
        List<Integer> unique = duplicates.stream()
            .distinct()
            .toList();
        System.out.println("중복 제거: " + unique);

        // === limit / skip ===
        System.out.println("\n=== limit / skip ===");
        List<Integer> first3 = numbers.stream()
            .limit(3)
            .toList();
        System.out.println("처음 3개: " + first3);

        List<Integer> skip3 = numbers.stream()
            .skip(3)
            .toList();
        System.out.println("3개 건너뛰기: " + skip3);

        // 페이징 예시 (skip + limit)
        int page = 2;
        int size = 3;
        List<Integer> page2 = numbers.stream()
            .skip((page - 1) * size)
            .limit(size)
            .toList();
        System.out.println("2페이지 (3개씩): " + page2);

        // === 최종 연산 ===
        System.out.println("\n=== 최종 연산 ===");

        // count
        long count = numbers.stream()
            .filter(n -> n > 5)
            .count();
        System.out.println("5 초과 개수: " + count);

        // reduce
        int sum = numbers.stream()
            .reduce(0, Integer::sum);
        System.out.println("합계: " + sum);

        Optional<Integer> max = numbers.stream()
            .reduce(Integer::max);
        System.out.println("최대값: " + max.orElse(0));

        // anyMatch / allMatch / noneMatch
        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noNegative = numbers.stream().noneMatch(n -> n < 0);

        System.out.println("짝수 있음: " + hasEven);
        System.out.println("전부 양수: " + allPositive);
        System.out.println("음수 없음: " + noNegative);

        // findFirst
        Optional<Integer> first = numbers.stream()
            .filter(n -> n > 5)
            .findFirst();
        System.out.println("5 초과 첫번째: " + first.orElse(-1));
    }
}

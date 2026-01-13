import java.util.HashSet;
import java.util.Set;

/**
 * Chapter 15 - HashSet 예제
 *
 * 실행: javac SetExample.java && java SetExample
 */
public class SetExample {
    public static void main(String[] args) {
        // === 기본 사용 ===
        System.out.println("=== 기본 사용 ===");
        Set<String> fruits = new HashSet<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        fruits.add("사과");  // 중복 - 추가 안됨!
        fruits.add("바나나"); // 중복 - 추가 안됨!

        System.out.println("fruits: " + fruits);
        System.out.println("size: " + fruits.size());  // 3

        // === 조회 ===
        System.out.println("\n=== 조회 ===");
        System.out.println("contains(\"사과\"): " + fruits.contains("사과"));
        System.out.println("contains(\"포도\"): " + fruits.contains("포도"));
        System.out.println("isEmpty(): " + fruits.isEmpty());

        // === 삭제 ===
        System.out.println("\n=== 삭제 ===");
        fruits.remove("바나나");
        System.out.println("remove(\"바나나\"): " + fruits);

        // === 순회 (순서 보장 안됨!) ===
        System.out.println("\n=== 순회 ===");
        Set<String> colors = new HashSet<>(Set.of("빨강", "파랑", "노랑", "초록"));
        for (String color : colors) {
            System.out.println("- " + color);
        }

        // === 중복 제거 용도 ===
        System.out.println("\n=== 중복 제거 ===");
        String[] items = {"A", "B", "A", "C", "B", "D", "A"};
        Set<String> uniqueItems = new HashSet<>();
        for (String item : items) {
            uniqueItems.add(item);
        }
        System.out.println("원본 배열 길이: " + items.length);
        System.out.println("중복 제거 후: " + uniqueItems);
        System.out.println("고유 개수: " + uniqueItems.size());

        // === 집합 연산 ===
        System.out.println("\n=== 집합 연산 ===");
        Set<Integer> set1 = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Set.of(4, 5, 6, 7, 8));
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        // 합집합
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("합집합: " + union);

        // 교집합
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("교집합: " + intersection);

        // 차집합
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("차집합 (set1 - set2): " + difference);

        // === 초기화 방법 ===
        System.out.println("\n=== 초기화 방법 ===");
        // Set.of (불변)
        Set<String> immutable = Set.of("a", "b", "c");
        System.out.println("Set.of: " + immutable);

        // 가변 Set
        Set<String> mutable = new HashSet<>(Set.of("x", "y", "z"));
        mutable.add("w");
        System.out.println("가변 Set: " + mutable);
    }
}

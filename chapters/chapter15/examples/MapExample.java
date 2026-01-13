import java.util.HashMap;
import java.util.Map;

/**
 * Chapter 15 - HashMap 예제
 *
 * 실행: javac MapExample.java && java MapExample
 */
public class MapExample {
    public static void main(String[] args) {
        // === 기본 사용 ===
        System.out.println("=== 기본 사용 ===");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("홍길동", 95);
        scores.put("김철수", 88);
        scores.put("이영희", 92);
        System.out.println("scores: " + scores);

        // 키 중복 - 값 덮어씀
        scores.put("홍길동", 97);
        System.out.println("홍길동 점수 수정: " + scores);

        // === 조회 ===
        System.out.println("\n=== 조회 ===");
        System.out.println("get(\"홍길동\"): " + scores.get("홍길동"));
        System.out.println("get(\"없는사람\"): " + scores.get("없는사람"));  // null
        System.out.println("getOrDefault(\"없는사람\", 0): " + scores.getOrDefault("없는사람", 0));
        System.out.println("containsKey(\"김철수\"): " + scores.containsKey("김철수"));
        System.out.println("containsValue(88): " + scores.containsValue(88));
        System.out.println("size(): " + scores.size());

        // === 삭제 ===
        System.out.println("\n=== 삭제 ===");
        scores.remove("김철수");
        System.out.println("remove(\"김철수\"): " + scores);

        // === 순회 ===
        System.out.println("\n=== 순회 방법 ===");
        scores = new HashMap<>(Map.of("홍길동", 95, "김철수", 88, "이영희", 92));

        // keySet
        System.out.println("-- keySet --");
        for (String name : scores.keySet()) {
            System.out.println(name + ": " + scores.get(name));
        }

        // values
        System.out.println("-- values --");
        for (Integer score : scores.values()) {
            System.out.println("점수: " + score);
        }

        // entrySet (권장)
        System.out.println("-- entrySet --");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // forEach + 람다
        System.out.println("-- forEach + 람다 --");
        scores.forEach((name, score) -> {
            System.out.println(name + " -> " + score);
        });

        // === 유용한 메소드 (Java 8+) ===
        System.out.println("\n=== 유용한 메소드 ===");

        // putIfAbsent
        scores.putIfAbsent("새사람", 80);
        scores.putIfAbsent("홍길동", 0);  // 이미 있으면 무시
        System.out.println("putIfAbsent: " + scores);

        // compute
        scores.compute("홍길동", (key, val) -> val + 5);
        System.out.println("compute (홍길동 +5): " + scores);

        // merge
        scores.merge("김철수", 10, Integer::sum);  // 기존값 + 10
        System.out.println("merge (김철수 +10): " + scores);

        // === 실용 예제: 단어 카운팅 ===
        System.out.println("\n=== 단어 카운팅 ===");
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.merge(word, 1, Integer::sum);
        }
        System.out.println("단어 빈도: " + wordCount);

        // === 초기화 방법 ===
        System.out.println("\n=== 초기화 방법 ===");
        // Map.of (불변, Java 9+)
        Map<String, Integer> immutable = Map.of("a", 1, "b", 2, "c", 3);
        System.out.println("Map.of: " + immutable);

        // Map.ofEntries (10개 초과시)
        Map<String, Integer> large = Map.ofEntries(
            Map.entry("one", 1),
            Map.entry("two", 2),
            Map.entry("three", 3)
        );
        System.out.println("Map.ofEntries: " + large);

        // 가변 Map
        Map<String, Integer> mutable = new HashMap<>(Map.of("x", 1, "y", 2));
        mutable.put("z", 3);
        System.out.println("가변 Map: " + mutable);
    }
}

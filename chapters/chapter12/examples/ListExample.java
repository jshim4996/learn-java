import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Chapter 15 - ArrayList 예제
 *
 * 실행: javac ListExample.java && java ListExample
 */
public class ListExample {
    public static void main(String[] args) {
        // === 생성과 추가 ===
        System.out.println("=== 생성과 추가 ===");
        List<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        fruits.add(1, "포도");  // 인덱스 1에 삽입
        System.out.println("fruits: " + fruits);

        // === 조회 ===
        System.out.println("\n=== 조회 ===");
        System.out.println("get(0): " + fruits.get(0));
        System.out.println("size(): " + fruits.size());
        System.out.println("isEmpty(): " + fruits.isEmpty());
        System.out.println("contains(\"바나나\"): " + fruits.contains("바나나"));
        System.out.println("indexOf(\"오렌지\"): " + fruits.indexOf("오렌지"));

        // === 수정과 삭제 ===
        System.out.println("\n=== 수정과 삭제 ===");
        fruits.set(0, "청사과");
        System.out.println("set(0, \"청사과\"): " + fruits);

        fruits.remove(0);
        System.out.println("remove(0): " + fruits);

        fruits.remove("바나나");
        System.out.println("remove(\"바나나\"): " + fruits);

        // === 순회 ===
        System.out.println("\n=== for-each 순회 ===");
        fruits = new ArrayList<>(List.of("사과", "바나나", "오렌지"));
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }

        System.out.println("\n=== forEach + 람다 ===");
        fruits.forEach(fruit -> System.out.println("* " + fruit));

        System.out.println("\n=== 메소드 참조 ===");
        fruits.forEach(System.out::println);

        // === 초기화 방법 ===
        System.out.println("\n=== 초기화 방법 ===");
        // Arrays.asList (고정 크기 - add/remove 불가)
        List<String> list1 = Arrays.asList("a", "b", "c");
        System.out.println("Arrays.asList: " + list1);

        // List.of (불변 - Java 9+)
        List<String> list2 = List.of("x", "y", "z");
        System.out.println("List.of: " + list2);

        // 가변 리스트
        List<String> list3 = new ArrayList<>(List.of("1", "2", "3"));
        list3.add("4");
        System.out.println("가변 리스트: " + list3);

        // === Collections 유틸리티 ===
        System.out.println("\n=== Collections 유틸리티 ===");
        List<Integer> numbers = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2, 6));

        System.out.println("원본: " + numbers);
        Collections.sort(numbers);
        System.out.println("정렬: " + numbers);
        Collections.reverse(numbers);
        System.out.println("역순: " + numbers);
        System.out.println("최대: " + Collections.max(numbers));
        System.out.println("최소: " + Collections.min(numbers));
        System.out.println("1의 개수: " + Collections.frequency(numbers, 1));

        // === 객체 리스트 ===
        System.out.println("\n=== 객체 리스트 ===");
        List<User> users = new ArrayList<>();
        users.add(new User("홍길동", 25));
        users.add(new User("김철수", 30));
        users.add(new User("이영희", 28));

        for (User user : users) {
            System.out.println(user);
        }
    }
}

// 간단한 User 클래스
class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}

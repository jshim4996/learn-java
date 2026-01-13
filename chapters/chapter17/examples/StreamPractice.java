import java.util.*;
import java.util.stream.*;

/**
 * Chapter 17 - 스트림 실무 패턴
 *
 * 실행: javac StreamPractice.java && java StreamPractice
 */
public class StreamPractice {
    public static void main(String[] args) {
        // 샘플 데이터
        List<User> users = List.of(
            new User(1L, "홍길동", 25, "서울"),
            new User(2L, "김철수", 30, "부산"),
            new User(3L, "이영희", 28, "서울"),
            new User(4L, "박지성", 35, "대전"),
            new User(5L, "김연아", 22, "서울"),
            new User(6L, "손흥민", 27, "부산")
        );

        // === 기본 조회 패턴 ===
        System.out.println("=== 기본 조회 ===");

        // 이름만 추출
        List<String> names = users.stream()
            .map(User::getName)
            .toList();
        System.out.println("이름 목록: " + names);

        // ID만 추출
        List<Long> ids = users.stream()
            .map(User::getId)
            .toList();
        System.out.println("ID 목록: " + ids);

        // === 조건 필터링 ===
        System.out.println("\n=== 조건 필터링 ===");

        // 서울 거주자
        List<User> seoulUsers = users.stream()
            .filter(u -> u.getCity().equals("서울"))
            .toList();
        System.out.println("서울 거주자: " + seoulUsers.size() + "명");

        // 25세 이상
        List<User> adults = users.stream()
            .filter(u -> u.getAge() >= 25)
            .toList();
        System.out.println("25세 이상: " + adults.size() + "명");

        // 복합 조건
        List<String> seoulAdultNames = users.stream()
            .filter(u -> u.getCity().equals("서울"))
            .filter(u -> u.getAge() >= 25)
            .map(User::getName)
            .toList();
        System.out.println("서울, 25세 이상: " + seoulAdultNames);

        // === 정렬 ===
        System.out.println("\n=== 정렬 ===");

        // 나이순 정렬
        List<String> byAge = users.stream()
            .sorted(Comparator.comparing(User::getAge))
            .map(u -> u.getName() + "(" + u.getAge() + ")")
            .toList();
        System.out.println("나이순: " + byAge);

        // 나이 역순
        List<String> byAgeDesc = users.stream()
            .sorted(Comparator.comparing(User::getAge).reversed())
            .map(u -> u.getName() + "(" + u.getAge() + ")")
            .toList();
        System.out.println("나이 역순: " + byAgeDesc);

        // 도시순 → 나이순
        List<String> byCityThenAge = users.stream()
            .sorted(Comparator.comparing(User::getCity)
                .thenComparing(User::getAge))
            .map(u -> u.getCity() + "-" + u.getName())
            .toList();
        System.out.println("도시+나이순: " + byCityThenAge);

        // === 집계 ===
        System.out.println("\n=== 집계 ===");

        // 평균 나이
        double avgAge = users.stream()
            .mapToInt(User::getAge)
            .average()
            .orElse(0);
        System.out.println("평균 나이: " + avgAge);

        // 나이 합계
        int totalAge = users.stream()
            .mapToInt(User::getAge)
            .sum();
        System.out.println("나이 합계: " + totalAge);

        // 최연장자
        Optional<User> oldest = users.stream()
            .max(Comparator.comparing(User::getAge));
        oldest.ifPresent(u -> System.out.println("최연장자: " + u.getName()));

        // === 그룹핑 ===
        System.out.println("\n=== 그룹핑 ===");

        // 도시별 그룹
        Map<String, List<User>> byCity = users.stream()
            .collect(Collectors.groupingBy(User::getCity));

        byCity.forEach((city, list) -> {
            System.out.println(city + ": " + list.stream()
                .map(User::getName)
                .collect(Collectors.joining(", ")));
        });

        // 도시별 인원수
        Map<String, Long> countByCity = users.stream()
            .collect(Collectors.groupingBy(
                User::getCity,
                Collectors.counting()
            ));
        System.out.println("도시별 인원: " + countByCity);

        // 도시별 평균 나이
        Map<String, Double> avgAgeByCity = users.stream()
            .collect(Collectors.groupingBy(
                User::getCity,
                Collectors.averagingInt(User::getAge)
            ));
        System.out.println("도시별 평균 나이: " + avgAgeByCity);

        // === ID로 조회 (findFirst) ===
        System.out.println("\n=== ID로 조회 ===");
        Long targetId = 3L;
        Optional<User> found = users.stream()
            .filter(u -> u.getId().equals(targetId))
            .findFirst();

        found.ifPresentOrElse(
            u -> System.out.println("ID " + targetId + " 찾음: " + u.getName()),
            () -> System.out.println("ID " + targetId + " 없음")
        );

        // === 존재 여부 확인 ===
        System.out.println("\n=== 존재 여부 ===");
        boolean hasSeoul = users.stream()
            .anyMatch(u -> u.getCity().equals("서울"));
        System.out.println("서울 거주자 있음: " + hasSeoul);

        boolean allAdult = users.stream()
            .allMatch(u -> u.getAge() >= 18);
        System.out.println("모두 성인: " + allAdult);

        // === DTO 변환 패턴 ===
        System.out.println("\n=== DTO 변환 ===");
        List<UserDTO> dtos = users.stream()
            .map(u -> new UserDTO(u.getId(), u.getName()))
            .toList();
        System.out.println("DTO 변환: " + dtos);
    }
}

// User 클래스
class User {
    private Long id;
    private String name;
    private int age;
    private String city;

    public User(Long id, String name, int age, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCity() { return city; }

    @Override
    public String toString() {
        return name + "(" + age + ", " + city + ")";
    }
}

// UserDTO
class UserDTO {
    private Long id;
    private String name;

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDTO{id=" + id + ", name='" + name + "'}";
    }
}

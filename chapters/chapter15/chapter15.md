# Chapter 15. 컬렉션 자료구조

> 배열의 한계를 넘어서 - 실무 필수

---

## 학습 목표
- 컬렉션 프레임워크 이해
- List, Set, Map 사용법
- 제네릭 기초

---

## 15-1. 컬렉션 프레임워크

### 왜 컬렉션?
- 배열: 크기 고정, 타입 고정
- 컬렉션: **동적 크기**, 다양한 자료구조

### 계층 구조
```
Collection (interface)
├── List (순서O, 중복O)
│   ├── ArrayList  ★ 가장 많이 사용
│   └── LinkedList
├── Set (순서X, 중복X)
│   └── HashSet    ★
└── Queue (FIFO)

Map (interface) - 키-값 쌍
└── HashMap       ★
```

### JS와 비교
| JavaScript | Java |
|------------|------|
| `[]` Array | `ArrayList<T>` |
| `new Set()` | `HashSet<T>` |
| `{}` Object / `new Map()` | `HashMap<K,V>` |

---

## 15-2. 제네릭 (Generics) 기초

### 타입 파라미터
```java
// 제네릭 없이 (옛날 방식)
ArrayList list = new ArrayList();
list.add("문자열");
list.add(123);  // 혼합 가능 (위험)
String str = (String) list.get(0);  // 캐스팅 필요

// 제네릭 사용 (권장)
ArrayList<String> list = new ArrayList<>();
list.add("문자열");
// list.add(123);  // 컴파일 에러!
String str = list.get(0);  // 캐스팅 불필요
```

### 다이아몬드 연산자 (<>)
```java
// Java 7+
List<String> list = new ArrayList<>();  // 타입 추론
Map<String, Integer> map = new HashMap<>();
```

---

## 15-3. List - ArrayList

### 생성과 추가
```java
import java.util.ArrayList;
import java.util.List;

List<String> fruits = new ArrayList<>();
fruits.add("사과");
fruits.add("바나나");
fruits.add("오렌지");
fruits.add(1, "포도");  // 인덱스 지정
```

### 조회
```java
fruits.get(0)           // "사과"
fruits.size()           // 4
fruits.isEmpty()        // false
fruits.contains("바나나") // true
fruits.indexOf("오렌지")  // 3
```

### 수정과 삭제
```java
fruits.set(0, "청사과");       // 수정
fruits.remove(0);              // 인덱스로 삭제
fruits.remove("바나나");       // 값으로 삭제
fruits.clear();                // 전체 삭제
```

### 순회
```java
// for-each
for (String fruit : fruits) {
    System.out.println(fruit);
}

// 인덱스 접근
for (int i = 0; i < fruits.size(); i++) {
    System.out.println(fruits.get(i));
}

// forEach + 람다 (Java 8+)
fruits.forEach(fruit -> System.out.println(fruit));
fruits.forEach(System.out::println);  // 메소드 참조
```

### List 초기화 방법
```java
// Arrays.asList (고정 크기)
List<String> list1 = Arrays.asList("a", "b", "c");

// List.of (불변, Java 9+)
List<String> list2 = List.of("a", "b", "c");

// 가변 리스트로 변환
List<String> list3 = new ArrayList<>(List.of("a", "b", "c"));
```

---

## 15-4. List - LinkedList

### ArrayList vs LinkedList
| | ArrayList | LinkedList |
|--|-----------|------------|
| 구조 | 배열 기반 | 노드 연결 |
| 조회 | O(1) 빠름 | O(n) 느림 |
| 삽입/삭제 | O(n) 느림 | O(1) 빠름 (앞/뒤) |
| 사용 | 대부분의 경우 | 잦은 삽입/삭제 |

```java
LinkedList<String> linked = new LinkedList<>();
linked.addFirst("처음");
linked.addLast("마지막");
linked.removeFirst();
linked.removeLast();
```

> **결론**: 대부분 ArrayList 사용. LinkedList는 특수한 경우만.

---

## 15-5. Set - HashSet

### 특징
- **중복 불가**
- **순서 없음**
- 빠른 검색 (O(1))

### 기본 사용
```java
import java.util.HashSet;
import java.util.Set;

Set<String> fruits = new HashSet<>();
fruits.add("사과");
fruits.add("바나나");
fruits.add("사과");  // 중복 - 추가 안됨

System.out.println(fruits.size());  // 2
```

### 주요 메소드
```java
fruits.contains("사과")  // true
fruits.remove("사과")
fruits.isEmpty()
fruits.clear()
```

### 순회
```java
for (String fruit : fruits) {
    System.out.println(fruit);  // 순서 보장 안됨!
}
```

### 집합 연산
```java
Set<Integer> set1 = new HashSet<>(Set.of(1, 2, 3, 4));
Set<Integer> set2 = new HashSet<>(Set.of(3, 4, 5, 6));

// 합집합
Set<Integer> union = new HashSet<>(set1);
union.addAll(set2);  // {1, 2, 3, 4, 5, 6}

// 교집합
Set<Integer> intersection = new HashSet<>(set1);
intersection.retainAll(set2);  // {3, 4}

// 차집합
Set<Integer> difference = new HashSet<>(set1);
difference.removeAll(set2);  // {1, 2}
```

---

## 15-6. Map - HashMap

### 특징
- **키-값** 쌍
- **키 중복 불가**
- 빠른 검색 (O(1))

### 기본 사용
```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> scores = new HashMap<>();
scores.put("홍길동", 95);
scores.put("김철수", 88);
scores.put("이영희", 92);
scores.put("홍길동", 97);  // 키 중복 - 값 덮어씀
```

### 조회
```java
scores.get("홍길동")           // 97
scores.getOrDefault("없는사람", 0)  // 0 (기본값)
scores.containsKey("김철수")   // true
scores.containsValue(88)       // true
scores.size()                  // 3
scores.isEmpty()               // false
```

### 수정과 삭제
```java
scores.put("홍길동", 100);     // 수정
scores.remove("김철수");       // 삭제
scores.clear();                // 전체 삭제
```

### 순회
```java
// 키 순회
for (String name : scores.keySet()) {
    System.out.println(name + ": " + scores.get(name));
}

// 값 순회
for (Integer score : scores.values()) {
    System.out.println(score);
}

// 키-값 순회 (권장)
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// forEach + 람다 (Java 8+)
scores.forEach((name, score) -> {
    System.out.println(name + ": " + score);
});
```

### 유용한 메소드 (Java 8+)
```java
// 없으면 추가
scores.putIfAbsent("새사람", 80);

// 계산하며 추가/수정
scores.compute("홍길동", (key, val) -> val + 10);

// 없으면 계산하여 추가
scores.computeIfAbsent("새사람", key -> 50);

// merge (기존값과 병합)
scores.merge("홍길동", 5, Integer::sum);  // 기존값 + 5
```

---

## 15-7. 컬렉션 유틸리티

### Collections 클래스
```java
import java.util.Collections;

List<Integer> numbers = new ArrayList<>(List.of(3, 1, 4, 1, 5));

Collections.sort(numbers);           // 정렬 [1, 1, 3, 4, 5]
Collections.reverse(numbers);        // 역순
Collections.shuffle(numbers);        // 섞기
Collections.max(numbers);            // 최대값
Collections.min(numbers);            // 최소값
Collections.frequency(numbers, 1);   // 1의 개수
```

### 불변 컬렉션
```java
// 불변 리스트 (수정 불가)
List<String> immutable = Collections.unmodifiableList(list);

// Java 9+ 팩토리 메소드
List<String> list = List.of("a", "b", "c");
Set<String> set = Set.of("a", "b", "c");
Map<String, Integer> map = Map.of("a", 1, "b", 2);
```

---

## 실무 포인트
> **ArrayList**: 대부분의 List 상황
> **HashMap**: 대부분의 키-값 저장
> **HashSet**: 중복 제거할 때
>
> Spring에서 DTO, Entity의 필드로 자주 사용:
> `private List<Order> orders;`
> `private Map<String, Object> metadata;`

---

## 예제 파일
- `examples/ListExample.java` - ArrayList 사용
- `examples/SetExample.java` - HashSet 사용
- `examples/MapExample.java` - HashMap 사용

---

## 다음 단계
→ Chapter 16: 람다식

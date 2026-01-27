# Chapter 14. 스트림 요소 처리

> JavaScript의 map, filter, reduce와 유사

---

## 학습 목표
- Stream API 개념
- 중간 연산과 최종 연산
- 실무 활용 패턴

---

## 14-1. 스트림이란?

### 개념
- 컬렉션 데이터를 **선언적**으로 처리
- **파이프라인** 방식 (체이닝)
- **불변** - 원본 데이터 변경 안함

### JS와 비교
```javascript
// JavaScript
const result = numbers
    .filter(n => n > 0)
    .map(n => n * 2)
    .reduce((a, b) => a + b, 0);
```

```java
// Java Stream
int result = numbers.stream()
    .filter(n -> n > 0)
    .map(n -> n * 2)
    .reduce(0, Integer::sum);
```

### 스트림 특징
1. **원본 변경 X** - 새 스트림 생성
2. **지연 평가** - 최종 연산 시 실행
3. **일회용** - 한 번 사용하면 재사용 불가

---

## 14-2. 스트림 생성

### 컬렉션에서 생성
```java
List<String> list = List.of("a", "b", "c");
Stream<String> stream = list.stream();
```

### 배열에서 생성
```java
String[] arr = {"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);
```

### 직접 생성
```java
Stream<String> stream = Stream.of("a", "b", "c");
Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
```

### 범위 생성
```java
IntStream range = IntStream.range(1, 10);      // 1~9
IntStream rangeClosed = IntStream.rangeClosed(1, 10);  // 1~10
```

---

## 14-3. 중간 연산 (Intermediate)

### filter - 조건 필터링
```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .toList();
// [2, 4, 6]
```

### map - 요소 변환
```java
List<String> names = List.of("홍길동", "김철수", "이영희");

List<Integer> lengths = names.stream()
    .map(String::length)
    .toList();
// [3, 3, 3]

List<String> upperNames = names.stream()
    .map(String::toUpperCase)
    .toList();
```

### flatMap - 중첩 구조 평탄화
```java
List<List<Integer>> nested = List.of(
    List.of(1, 2),
    List.of(3, 4),
    List.of(5, 6)
);

List<Integer> flat = nested.stream()
    .flatMap(List::stream)
    .toList();
// [1, 2, 3, 4, 5, 6]
```

### sorted - 정렬
```java
List<Integer> sorted = numbers.stream()
    .sorted()
    .toList();

// 역순
List<Integer> reversed = numbers.stream()
    .sorted(Comparator.reverseOrder())
    .toList();

// 커스텀 정렬
List<String> byLength = names.stream()
    .sorted(Comparator.comparing(String::length))
    .toList();
```

### distinct - 중복 제거
```java
List<Integer> unique = List.of(1, 2, 2, 3, 3, 3).stream()
    .distinct()
    .toList();
// [1, 2, 3]
```

### limit / skip
```java
List<Integer> first3 = numbers.stream()
    .limit(3)
    .toList();

List<Integer> skip2 = numbers.stream()
    .skip(2)
    .toList();
```

### peek - 디버깅용
```java
numbers.stream()
    .filter(n -> n > 2)
    .peek(n -> System.out.println("필터 통과: " + n))
    .map(n -> n * 2)
    .forEach(System.out::println);
```

---

## 14-4. 최종 연산 (Terminal)

### collect - 컬렉션으로 수집
```java
// List로
List<String> list = stream.collect(Collectors.toList());
List<String> list2 = stream.toList();  // Java 16+

// Set으로
Set<String> set = stream.collect(Collectors.toSet());

// Map으로
Map<String, Integer> map = names.stream()
    .collect(Collectors.toMap(
        name -> name,           // 키
        name -> name.length()   // 값
    ));
```

### forEach - 각 요소 처리
```java
names.stream().forEach(System.out::println);
names.forEach(System.out::println);  // 스트림 없이도 가능
```

### count - 개수
```java
long count = numbers.stream()
    .filter(n -> n > 3)
    .count();
```

### reduce - 집계
```java
// 합계
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);

int sum2 = numbers.stream()
    .reduce(0, Integer::sum);

// 최대값
Optional<Integer> max = numbers.stream()
    .reduce(Integer::max);
```

### 집계 메소드
```java
IntStream intStream = numbers.stream().mapToInt(Integer::intValue);

int sum = intStream.sum();
OptionalDouble avg = intStream.average();
OptionalInt max = intStream.max();
OptionalInt min = intStream.min();
```

### anyMatch / allMatch / noneMatch
```java
boolean hasEven = numbers.stream()
    .anyMatch(n -> n % 2 == 0);  // 하나라도 짝수?

boolean allPositive = numbers.stream()
    .allMatch(n -> n > 0);  // 전부 양수?

boolean noNegative = numbers.stream()
    .noneMatch(n -> n < 0);  // 음수 없음?
```

### findFirst / findAny
```java
Optional<Integer> first = numbers.stream()
    .filter(n -> n > 3)
    .findFirst();

first.ifPresent(System.out::println);
```

---

## 14-5. Optional

### null 안전한 처리
```java
Optional<String> opt = Optional.of("Hello");
Optional<String> empty = Optional.empty();
Optional<String> nullable = Optional.ofNullable(null);
```

### 값 가져오기
```java
opt.get();                    // 값 (없으면 예외)
opt.orElse("기본값");          // 값 또는 기본값
opt.orElseGet(() -> "계산값");  // 값 또는 Supplier
opt.orElseThrow();            // 값 또는 예외
```

### 체이닝
```java
Optional<Integer> result = Optional.of("hello")
    .map(String::length)
    .filter(len -> len > 3);

result.ifPresent(System.out::println);
```

---

## 14-6. Collectors 유틸리티

### 문자열 조인
```java
String joined = names.stream()
    .collect(Collectors.joining(", "));
// "홍길동, 김철수, 이영희"
```

### 그룹핑
```java
Map<Integer, List<String>> byLength = names.stream()
    .collect(Collectors.groupingBy(String::length));
// {3: ["홍길동", "김철수", "이영희"]}
```

### 분할
```java
Map<Boolean, List<Integer>> partitioned = numbers.stream()
    .collect(Collectors.partitioningBy(n -> n % 2 == 0));
// {true: [2, 4, 6], false: [1, 3, 5]}
```

### 통계
```java
IntSummaryStatistics stats = numbers.stream()
    .collect(Collectors.summarizingInt(Integer::intValue));

stats.getSum();
stats.getAverage();
stats.getMax();
stats.getMin();
stats.getCount();
```

---

## 14-7. 실무 패턴

### 객체 리스트 처리
```java
List<User> users = List.of(
    new User("홍길동", 25),
    new User("김철수", 30),
    new User("이영희", 28)
);

// 이름만 추출
List<String> names = users.stream()
    .map(User::getName)
    .toList();

// 나이 합계
int totalAge = users.stream()
    .mapToInt(User::getAge)
    .sum();

// 조건 필터링 + 정렬
List<User> adults = users.stream()
    .filter(u -> u.getAge() >= 18)
    .sorted(Comparator.comparing(User::getAge))
    .toList();
```

### 중첩 컬렉션 처리
```java
List<Order> orders = ...;

// 모든 주문의 상품 추출
List<Product> allProducts = orders.stream()
    .flatMap(order -> order.getProducts().stream())
    .toList();
```

---

## 실무 포인트
> Spring에서 Repository 결과, DTO 변환 등에서 Stream 많이 사용.
> `list.stream().map(...).toList()` 패턴 익숙해지기.

---

## 예제 파일
- `examples/StreamBasic.java` - 기본 사용
- `examples/StreamCollect.java` - 수집 연산
- `examples/StreamPractice.java` - 실무 패턴

---

## 다음 단계
→ Spring Boot 학습

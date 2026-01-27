# Chapter 13. 람다식

> JavaScript 화살표 함수와 유사

---

## 학습 목표
- 람다식 문법
- 함수형 인터페이스
- 메소드 참조

---

## 13-1. 람다식이란?

### 익명 함수
- 이름 없는 함수
- 함수형 인터페이스를 간결하게 구현

### JS 화살표 함수와 비교
```javascript
// JavaScript
const add = (a, b) => a + b;
const greet = name => `Hello, ${name}`;
const sayHi = () => console.log("Hi!");
```

```java
// Java
BinaryOperator<Integer> add = (a, b) -> a + b;
Function<String, String> greet = name -> "Hello, " + name;
Runnable sayHi = () -> System.out.println("Hi!");
```

---

## 13-2. 람다식 문법

### 기본 형태
```java
(매개변수) -> { 실행문 }
```

### 변형
```java
// 매개변수 1개 - 괄호 생략 가능
x -> x * 2

// 매개변수 없음 - 빈 괄호 필수
() -> System.out.println("Hello")

// 실행문 1개 - 중괄호, return 생략 가능
(a, b) -> a + b

// 실행문 여러 개 - 중괄호, return 필수
(a, b) -> {
    int sum = a + b;
    return sum;
}
```

### 예시
```java
// 기존 방식 (익명 클래스)
Comparator<String> comp1 = new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
};

// 람다식
Comparator<String> comp2 = (s1, s2) -> s1.length() - s2.length();
```

---

## 13-3. 함수형 인터페이스

### @FunctionalInterface
- 추상 메소드가 **딱 1개**인 인터페이스
- 람다식으로 구현 가능

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(10, 5));      // 15
System.out.println(multiply.calculate(10, 5)); // 50
```

### 표준 함수형 인터페이스

| 인터페이스 | 메소드 | 용도 | 예시 |
|-----------|--------|------|------|
| `Runnable` | `run()` | 실행만 | `() -> doSomething()` |
| `Consumer<T>` | `accept(T)` | 소비 | `x -> System.out.println(x)` |
| `Supplier<T>` | `get()` | 공급 | `() -> new User()` |
| `Function<T,R>` | `apply(T)` | 변환 | `s -> s.length()` |
| `Predicate<T>` | `test(T)` | 조건 | `x -> x > 0` |
| `BiFunction<T,U,R>` | `apply(T,U)` | 2인자 변환 | `(a,b) -> a+b` |
| `Comparator<T>` | `compare(T,T)` | 비교 | `(a,b) -> a-b` |

---

## 13-4. 표준 함수형 인터페이스 사용

### Consumer - 소비 (입력만, 반환 없음)
```java
import java.util.function.Consumer;

Consumer<String> printer = s -> System.out.println(s);
printer.accept("Hello!");

// 컬렉션과 함께
List<String> names = List.of("홍길동", "김철수", "이영희");
names.forEach(name -> System.out.println("이름: " + name));
```

### Supplier - 공급 (입력 없음, 반환만)
```java
import java.util.function.Supplier;

Supplier<Double> randomSupplier = () -> Math.random();
System.out.println(randomSupplier.get());

Supplier<LocalDateTime> now = () -> LocalDateTime.now();
```

### Function - 변환 (입력 → 반환)
```java
import java.util.function.Function;

Function<String, Integer> strLength = s -> s.length();
System.out.println(strLength.apply("Hello"));  // 5

Function<Integer, String> intToStr = i -> "숫자: " + i;
System.out.println(intToStr.apply(42));  // "숫자: 42"
```

### Predicate - 조건 검사 (boolean 반환)
```java
import java.util.function.Predicate;

Predicate<Integer> isPositive = x -> x > 0;
Predicate<String> isEmpty = s -> s.isEmpty();

System.out.println(isPositive.test(10));   // true
System.out.println(isPositive.test(-5));   // false
System.out.println(isEmpty.test(""));      // true

// 조합
Predicate<Integer> isEven = x -> x % 2 == 0;
Predicate<Integer> isPositiveEven = isPositive.and(isEven);
System.out.println(isPositiveEven.test(4));  // true
```

### Comparator - 비교
```java
import java.util.Comparator;

Comparator<String> byLength = (s1, s2) -> s1.length() - s2.length();
Comparator<String> byAlpha = (s1, s2) -> s1.compareTo(s2);

List<String> words = new ArrayList<>(List.of("banana", "apple", "cherry"));
words.sort(byLength);
System.out.println(words);  // [apple, banana, cherry]
```

---

## 13-5. 메소드 참조

### 람다식을 더 간결하게
```java
// 람다식
list.forEach(s -> System.out.println(s));

// 메소드 참조
list.forEach(System.out::println);
```

### 종류

| 유형 | 람다식 | 메소드 참조 |
|------|--------|------------|
| 정적 메소드 | `x -> Integer.parseInt(x)` | `Integer::parseInt` |
| 인스턴스 메소드 | `s -> s.toUpperCase()` | `String::toUpperCase` |
| 특정 객체 메소드 | `x -> obj.method(x)` | `obj::method` |
| 생성자 | `() -> new ArrayList()` | `ArrayList::new` |

### 예시
```java
// 정적 메소드 참조
Function<String, Integer> parser = Integer::parseInt;

// 인스턴스 메소드 참조
Function<String, String> toUpper = String::toUpperCase;

// 특정 객체의 메소드
PrintStream out = System.out;
Consumer<String> printer = out::println;

// 생성자 참조
Supplier<List<String>> listFactory = ArrayList::new;
List<String> newList = listFactory.get();

// Function으로 생성자 호출
Function<String, StringBuilder> sbFactory = StringBuilder::new;
StringBuilder sb = sbFactory.apply("Hello");
```

---

## 13-6. 람다와 컬렉션

### forEach
```java
List<String> list = List.of("a", "b", "c");
list.forEach(System.out::println);
```

### sort
```java
List<String> names = new ArrayList<>(List.of("홍길동", "김", "이영희"));
names.sort((a, b) -> a.length() - b.length());
// Comparator.comparingInt 사용
names.sort(Comparator.comparingInt(String::length));
```

### removeIf
```java
List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
numbers.removeIf(n -> n % 2 == 0);  // 짝수 제거
System.out.println(numbers);  // [1, 3, 5]
```

### replaceAll
```java
List<String> words = new ArrayList<>(List.of("hello", "world"));
words.replaceAll(String::toUpperCase);
System.out.println(words);  // [HELLO, WORLD]
```

---

## 실무 포인트
> 람다식은 **Stream API**와 함께 사용할 때 진가 발휘.
> Spring에서도 `@Bean`, 콜백 등에서 자주 사용.

---

## 예제 파일
- `examples/LambdaBasic.java` - 람다 기본
- `examples/FunctionalInterface.java` - 함수형 인터페이스
- `examples/MethodReference.java` - 메소드 참조

---

## 다음 단계
→ Chapter 14: 스트림 요소 처리

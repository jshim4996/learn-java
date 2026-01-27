# Chapter 09. 제네릭 (Generics)

> 제네릭은 타입을 파라미터화하여 컴파일 시점에 타입 안전성을 보장하는 기능

---

## 제네릭이란?

### 제네릭을 사용하는 이유

```java
// 제네릭 없이 - 런타임에 에러 발생 가능
List list = new ArrayList();
list.add("문자열");
list.add(100);  // 의도치 않은 타입도 추가 가능
String str = (String) list.get(1);  // ClassCastException!

// 제네릭 사용 - 컴파일 시점에 타입 체크
List<String> list = new ArrayList<>();
list.add("문자열");
list.add(100);  // 컴파일 에러! 타입 불일치
String str = list.get(0);  // 캐스팅 불필요
```

### 제네릭의 장점
1. **컴파일 시 타입 체크** - 런타임 에러 방지
2. **불필요한 캐스팅 제거** - 코드 가독성 향상
3. **재사용성** - 다양한 타입에 동일한 코드 사용

---

## 제네릭 타입 (클래스, 인터페이스)

### 제네릭 클래스 선언

```java
// 타입 파라미터 T를 사용하는 클래스
public class Box<T> {
    private T content;

    public void set(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }
}
```

### 제네릭 클래스 사용

```java
// String 타입으로 사용
Box<String> stringBox = new Box<>();
stringBox.set("Hello");
String str = stringBox.get();

// Integer 타입으로 사용
Box<Integer> intBox = new Box<>();
intBox.set(100);
int num = intBox.get();
```

### 멀티 타입 파라미터

```java
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
}

// 사용
Pair<String, Integer> pair = new Pair<>("age", 25);
```

### 타입 파라미터 관례
| 문자 | 의미 |
|------|------|
| T | Type |
| E | Element (컬렉션) |
| K | Key |
| V | Value |
| N | Number |

---

## 제네릭 메소드

### 선언과 사용

```java
public class Util {
    // 제네릭 메소드: 리턴 타입 앞에 <T> 선언
    public static <T> Box<T> boxing(T content) {
        Box<T> box = new Box<>();
        box.set(content);
        return box;
    }
}

// 사용 - 타입 추론
Box<String> box1 = Util.boxing("Hello");
Box<Integer> box2 = Util.boxing(100);

// 명시적 타입 지정
Box<String> box3 = Util.<String>boxing("World");
```

---

## 제한된 타입 파라미터 (<T extends 상위타입>)

### 숫자만 받는 제네릭

```java
// Number의 하위 클래스만 타입으로 지정 가능
public class Calculator<T extends Number> {
    private T[] numbers;

    public Calculator(T[] numbers) {
        this.numbers = numbers;
    }

    public double sum() {
        double total = 0;
        for (T num : numbers) {
            total += num.doubleValue();  // Number의 메소드 사용 가능
        }
        return total;
    }
}

// 사용
Calculator<Integer> calc1 = new Calculator<>(new Integer[]{1, 2, 3});
Calculator<Double> calc2 = new Calculator<>(new Double[]{1.5, 2.5});
// Calculator<String> calc3 = ...;  // 컴파일 에러!
```

### 인터페이스 제한

```java
// Comparable 구현 클래스만 허용
public static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

---

## 와일드카드 타입 (<?>, <? extends>, <? super>)

### 비제한 와일드카드 <?>

```java
// 모든 타입 허용 (읽기 전용에 주로 사용)
public void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}

printList(Arrays.asList("A", "B"));
printList(Arrays.asList(1, 2, 3));
```

### 상한 제한 와일드카드 <? extends 상위타입>

```java
// Number 또는 그 하위 타입만 허용 (생산자 - 읽기용)
public double sumOfList(List<? extends Number> list) {
    double sum = 0;
    for (Number num : list) {
        sum += num.doubleValue();
    }
    return sum;
}

sumOfList(Arrays.asList(1, 2, 3));        // Integer
sumOfList(Arrays.asList(1.5, 2.5));       // Double
```

### 하한 제한 와일드카드 <? super 하위타입>

```java
// Integer 또는 그 상위 타입만 허용 (소비자 - 쓰기용)
public void addNumbers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3);
}

List<Number> numbers = new ArrayList<>();
addNumbers(numbers);  // OK

List<Object> objects = new ArrayList<>();
addNumbers(objects);  // OK
```

### PECS 원칙
> **P**roducer **E**xtends, **C**onsumer **S**uper

- 데이터를 **읽기만** 할 때 → `<? extends T>`
- 데이터를 **쓰기만** 할 때 → `<? super T>`

---

## Spring에서의 활용

```java
// Repository 인터페이스
public interface JpaRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
}

// 사용
public interface UserRepository extends JpaRepository<User, Long> {
    // User 타입, Long ID로 자동 적용
}
```

---

## 정리

| 문법 | 의미 | 예시 |
|------|------|------|
| `<T>` | 타입 파라미터 | `class Box<T>` |
| `<T extends X>` | T는 X의 하위 타입 | `<T extends Number>` |
| `<?>` | 모든 타입 | `List<?>` |
| `<? extends X>` | X 또는 하위 타입 | `List<? extends Number>` |
| `<? super X>` | X 또는 상위 타입 | `List<? super Integer>` |

---

## 다음 단계
→ Chapter 10: 예외 처리

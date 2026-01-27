# Chapter 08. 인터페이스

> Spring의 핵심 - 인터페이스 기반 설계

---

## 학습 목표
- 인터페이스 개념과 역할
- 추상 메소드, 디폴트 메소드
- 다중 구현
- 다형성 활용

---

## 08-1. 인터페이스란?

### 개념
- **규약(Contract)**: "이런 메소드를 반드시 구현해라"
- **타입 정의**: 객체의 사용 방법 정의
- **다중 구현 가능**: 클래스는 여러 인터페이스 구현 가능

### TypeScript와 비교
```typescript
// TypeScript
interface Runnable {
    run(): void;
}

class Dog implements Runnable {
    run() {
        console.log("달린다!");
    }
}
```

```java
// Java
interface Runnable {
    void run();
}

class Dog implements Runnable {
    @Override
    public void run() {
        System.out.println("달린다!");
    }
}
```

---

## 08-2. 인터페이스 선언

### 기본 구조
```java
public interface 인터페이스명 {
    // 상수 (public static final 생략 가능)
    int MAX_VALUE = 100;

    // 추상 메소드 (public abstract 생략 가능)
    void doSomething();
    String getName();

    // 디폴트 메소드 (Java 8+)
    default void printInfo() {
        System.out.println("기본 구현");
    }

    // 정적 메소드 (Java 8+)
    static void staticMethod() {
        System.out.println("정적 메소드");
    }
}
```

### 특징
- 모든 메소드는 기본적으로 `public abstract`
- 모든 필드는 `public static final` (상수)
- 생성자 없음, 객체 생성 불가

---

## 08-3. 인터페이스 구현

### implements 키워드
```java
public interface Movable {
    void move();
    void stop();
}

public class Car implements Movable {
    @Override
    public void move() {
        System.out.println("자동차가 이동합니다");
    }

    @Override
    public void stop() {
        System.out.println("자동차가 멈춥니다");
    }
}
```

### 다중 구현
```java
public interface Flyable {
    void fly();
}

public interface Swimmable {
    void swim();
}

public class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("오리가 날아갑니다");
    }

    @Override
    public void swim() {
        System.out.println("오리가 수영합니다");
    }
}
```

### 상속 + 구현
```java
public class Bird extends Animal implements Flyable {
    @Override
    public void fly() {
        System.out.println("새가 날아갑니다");
    }
}
```

---

## 08-4. 디폴트 메소드 (Java 8+)

### default 키워드
```java
public interface Vehicle {
    void start();
    void stop();

    // 디폴트 메소드 - 구현 제공
    default void honk() {
        System.out.println("빵빵!");
    }
}

public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("시동 켜기");
    }

    @Override
    public void stop() {
        System.out.println("시동 끄기");
    }
    // honk()는 구현 안 해도 됨 (필요시 오버라이드)
}
```

### 용도
- 인터페이스 확장 시 기존 구현체 깨지지 않게
- 공통 로직 제공

---

## 08-5. 인터페이스 다형성

### 인터페이스 타입 변수
```java
Movable movable = new Car();
movable.move();
movable.stop();
```

### 매개변수로 활용
```java
public class RemoteControl {
    public void operate(Movable m) {
        m.move();
        m.stop();
    }
}

RemoteControl rc = new RemoteControl();
rc.operate(new Car());
rc.operate(new Bicycle());
rc.operate(new Robot());
```

### 배열/컬렉션
```java
Movable[] vehicles = {
    new Car(),
    new Bicycle(),
    new Airplane()
};

for (Movable v : vehicles) {
    v.move();
}
```

---

## 08-6. 인터페이스 상속

### extends (인터페이스끼리)
```java
public interface Vehicle {
    void start();
}

public interface Car extends Vehicle {
    void accelerate();
}

public interface ElectricCar extends Car {
    void charge();
}

// 구현 클래스는 모든 메소드 구현
public class Tesla implements ElectricCar {
    @Override
    public void start() { }

    @Override
    public void accelerate() { }

    @Override
    public void charge() { }
}
```

---

## 08-7. 함수형 인터페이스 (Java 8+)

### @FunctionalInterface
```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}

// 람다식으로 구현
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(10, 20));      // 30
System.out.println(multiply.calculate(10, 20)); // 200
```

### 자주 쓰는 함수형 인터페이스
| 인터페이스 | 메소드 | 용도 |
|-----------|--------|------|
| `Runnable` | `run()` | 실행만 |
| `Consumer<T>` | `accept(T t)` | 소비 (반환 없음) |
| `Supplier<T>` | `get()` | 공급 (입력 없음) |
| `Function<T,R>` | `apply(T t)` | 변환 |
| `Predicate<T>` | `test(T t)` | 조건 검사 |

---

## 08-8. 실무 패턴: 인터페이스 기반 설계

### Repository 패턴 (Spring에서 자주 사용)
```java
// 인터페이스
public interface UserRepository {
    User findById(Long id);
    List<User> findAll();
    void save(User user);
    void delete(Long id);
}

// 구현체 1: 메모리 저장
public class InMemoryUserRepository implements UserRepository {
    private Map<Long, User> store = new HashMap<>();
    // ... 구현
}

// 구현체 2: DB 저장
public class JpaUserRepository implements UserRepository {
    // ... JPA 구현
}
```

### 장점
- 구현체 교체 용이
- 테스트 시 Mock 객체 주입 가능
- 의존성 역전 원칙 (DIP)

---

## 추상 클래스 vs 인터페이스

| 구분 | 추상 클래스 | 인터페이스 |
|------|------------|-----------|
| 목적 | 공통 기능 상속 | 규약 정의 |
| 상속/구현 | 단일 상속 | 다중 구현 |
| 필드 | 인스턴스 필드 가능 | 상수만 |
| 생성자 | 있음 | 없음 |
| 사용 시점 | is-a 관계 | can-do 관계 |

---

## 실무 포인트
> Spring에서 거의 모든 것이 인터페이스 기반.
> `@Service`, `@Repository` 구현체 + 인터페이스 조합이 표준 패턴.

---

## 예제 파일
- `examples/Movable.java` - 인터페이스
- `examples/Flyable.java` - 인터페이스
- `examples/Car.java` - 구현 클래스
- `examples/Bird.java` - 다중 구현
- `examples/InterfaceMain.java` - 사용 예제

---

## 다음 단계
→ Chapter 09: 제네릭

# Chapter 06. 클래스

> Java OOP의 핵심 - 모든 것이 클래스

---

## 학습 목표
- 클래스와 객체 개념
- 필드, 생성자, 메소드
- 접근 제한자
- static과 final

---

## 06-1. 객체지향 프로그래밍 (OOP)

### 핵심 개념
- **클래스 (Class)**: 설계도, 틀
- **객체 (Object)**: 클래스로 만든 실체
- **인스턴스 (Instance)**: 객체와 동의어

### JS와 비교
```javascript
// JavaScript ES6+ 클래스
class Person {
    constructor(name) {
        this.name = name;
    }
    greet() {
        console.log(`Hello, ${this.name}`);
    }
}
const person = new Person("홍길동");
```

```java
// Java 클래스
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello, " + name);
    }
}
Person person = new Person("홍길동");
```

**차이점**: Java는 타입 명시, 접근 제한자 필수

---

## 06-2. 클래스 선언

### 기본 구조
```java
public class 클래스명 {
    // 필드 (멤버 변수)
    private String name;
    private int age;

    // 생성자
    public 클래스명() { }

    // 메소드
    public void doSomething() { }
}
```

### 파일 규칙
- 파일명 = public 클래스명 + `.java`
- 파일당 public 클래스 1개
- 여러 클래스 가능하지만 public은 1개

---

## 06-3. 필드 (Field)

### 필드 선언
```java
public class Car {
    // 필드
    String model;       // 기본(default) 접근
    private int speed;  // private 접근
    public String color; // public 접근

    // 초기값 지정
    private int fuel = 100;
}
```

### 필드 vs 지역 변수
| 구분 | 필드 | 지역 변수 |
|------|------|----------|
| 위치 | 클래스 블록 | 메소드/생성자 블록 |
| 초기값 | 자동 부여 (0, null 등) | 초기화 필수 |
| 생명주기 | 객체와 함께 | 블록 종료시 소멸 |

---

## 06-4. 생성자 (Constructor)

### 기본 생성자
```java
public class Person {
    private String name;

    // 기본 생성자 (생략하면 자동 생성)
    public Person() {
        this.name = "이름없음";
    }
}
```

### 매개변수 생성자
```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// 사용
Person person = new Person("홍길동", 25);
```

### 생성자 오버로딩
```java
public class Person {
    private String name;
    private int age;

    public Person() {
        this("이름없음", 0);  // 다른 생성자 호출
    }

    public Person(String name) {
        this(name, 0);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

---

## 06-5. 메소드 (Method)

### 메소드 선언
```java
접근제한자 리턴타입 메소드명(매개변수) {
    // 실행문
    return 값;  // void면 생략
}
```

### 예시
```java
public class Calculator {
    // 리턴값 있는 메소드
    public int add(int a, int b) {
        return a + b;
    }

    // 리턴값 없는 메소드
    public void printResult(int result) {
        System.out.println("결과: " + result);
    }

    // 가변 인자
    public int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }
}

// 사용
Calculator calc = new Calculator();
int result = calc.add(10, 20);
calc.printResult(result);
calc.sum(1, 2, 3, 4, 5);
```

### 메소드 오버로딩
```java
public class Printer {
    public void print(int x) {
        System.out.println("int: " + x);
    }

    public void print(String x) {
        System.out.println("String: " + x);
    }

    public void print(int x, int y) {
        System.out.println("two ints: " + x + ", " + y);
    }
}
```

---

## 06-6. 인스턴스 멤버 vs 정적 멤버

### 인스턴스 멤버
```java
public class Counter {
    private int count;  // 인스턴스 필드

    public void increment() {  // 인스턴스 메소드
        count++;
    }
}

Counter c1 = new Counter();
Counter c2 = new Counter();
c1.increment();  // c1만 증가
```

### 정적 멤버 (static)
```java
public class Counter {
    private static int totalCount;  // 정적 필드

    public static int getTotalCount() {  // 정적 메소드
        return totalCount;
    }
}

// 클래스명으로 직접 접근
Counter.getTotalCount();
```

### static 사용 시기
- 모든 인스턴스가 공유해야 할 때
- 유틸리티 메소드 (Math.abs(), Integer.parseInt())
- 상수 (static final)

---

## 06-7. final 키워드

### final 필드
```java
public class Config {
    final int MAX_SIZE = 100;        // 선언 시 초기화
    final String name;               // 생성자에서 초기화

    public Config(String name) {
        this.name = name;  // 한 번만 가능
    }
}
```

### 상수 (static final)
```java
public class Constants {
    public static final double PI = 3.14159;
    public static final String APP_NAME = "MyApp";
}

// 사용
double area = Constants.PI * r * r;
```

---

## 06-8. 패키지 (Package)

### 패키지 선언
```java
package com.example.myapp;

public class MyClass {
    // ...
}
```

### import
```java
import java.util.ArrayList;
import java.util.List;
// 또는
import java.util.*;  // 전체 import
```

### 패키지 구조 예시
```
src/
└── com/
    └── example/
        └── myapp/
            ├── model/
            │   └── User.java
            ├── service/
            │   └── UserService.java
            └── Main.java
```

---

## 06-9. 접근 제한자

### 종류
| 제한자 | 같은 클래스 | 같은 패키지 | 자식 클래스 | 전체 |
|--------|:-----------:|:-----------:|:-----------:|:----:|
| `public` | O | O | O | O |
| `protected` | O | O | O | X |
| `default` (생략) | O | O | X | X |
| `private` | O | X | X | X |

### 권장 사용법
```java
public class User {
    private String name;     // 외부 접근 차단
    private int age;

    public String getName() {  // Getter로 접근
        return name;
    }

    public void setName(String name) {  // Setter로 수정
        this.name = name;
    }
}
```

---

## 06-10. Getter와 Setter

### 기본 패턴
```java
public class Person {
    private String name;
    private int age;

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {  // 유효성 검사 가능
            this.age = age;
        }
    }
}
```

### 왜 Getter/Setter?
- **캡슐화**: 내부 구현 숨김
- **유효성 검사**: setter에서 검증
- **읽기 전용**: getter만 제공

> **실무**: Lombok 라이브러리로 자동 생성 (Spring Boot에서 배움)

---

## 실무 포인트
> Spring Boot에서는 `@Getter`, `@Setter`, `@Builder` 등 Lombok으로 간소화.
> 지금은 수동으로 작성해서 원리 이해.

---

## 예제 파일
- `examples/Person.java` - 기본 클래스
- `examples/PersonMain.java` - 사용 예제
- `examples/Calculator.java` - 메소드 예제
- `examples/StaticExample.java` - static 예제

---

## 다음 단계
→ Chapter 07: 상속

# Chapter 07. 상속

> 코드 재사용과 다형성의 기반

---

## 학습 목표
- 상속 개념과 extends 키워드
- 메소드 오버라이딩
- 다형성
- 추상 클래스

---

## 07-1. 상속 개념

### 상속이란?
- 부모 클래스의 필드와 메소드를 자식 클래스가 물려받음
- 코드 재사용, 확장성

### JS와 비교
```javascript
// JavaScript
class Animal {
    constructor(name) { this.name = name; }
    speak() { console.log(`${this.name} makes a sound`); }
}
class Dog extends Animal {
    speak() { console.log(`${this.name} barks`); }
}
```

```java
// Java
class Animal {
    protected String name;
    public Animal(String name) { this.name = name; }
    public void speak() { System.out.println(name + " makes a sound"); }
}
class Dog extends Animal {
    public Dog(String name) { super(name); }
    @Override
    public void speak() { System.out.println(name + " barks"); }
}
```

---

## 07-2. 클래스 상속

### extends 키워드
```java
public class 자식클래스 extends 부모클래스 {
    // ...
}
```

### 예시
```java
// 부모 클래스
public class Vehicle {
    protected String brand;
    protected int speed;

    public void move() {
        System.out.println("이동 중...");
    }
}

// 자식 클래스
public class Car extends Vehicle {
    private int wheels = 4;

    public void honk() {
        System.out.println("빵빵!");
    }
}
```

### 상속 특징
- **단일 상속**: Java는 하나의 클래스만 상속 가능
- **다중 상속 X**: `class A extends B, C` 불가능
- **인터페이스로 대체**: 다중 구현은 가능 (Chapter 08)

---

## 07-3. 부모 생성자 호출 (super)

### super() 키워드
```java
public class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }
}

public class Dog extends Animal {
    private String breed;

    public Dog(String name, String breed) {
        super(name);  // 부모 생성자 호출 (첫 줄에 위치!)
        this.breed = breed;
    }
}
```

### 규칙
- `super()`는 생성자 첫 줄에 위치
- 생략하면 기본 생성자 `super()` 자동 호출
- 부모에 기본 생성자 없으면 명시적 호출 필수

---

## 07-4. 메소드 재정의 (Override)

### @Override 어노테이션
```java
public class Animal {
    public void speak() {
        System.out.println("동물이 소리를 냅니다");
    }
}

public class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("멍멍!");
    }
}

public class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("야옹~");
    }
}
```

### 오버라이딩 규칙
- 메소드 시그니처 동일 (이름, 매개변수)
- 리턴 타입 동일 (또는 하위 타입)
- 접근 제한자 같거나 더 넓게
- `@Override` 어노테이션 권장 (컴파일 체크)

### 부모 메소드 호출
```java
@Override
public void speak() {
    super.speak();  // 부모 메소드 먼저 호출
    System.out.println("멍멍!");
}
```

---

## 07-5. 타입 변환

### 자동 타입 변환 (Upcasting)
```java
Animal animal = new Dog("바둑이");  // Dog → Animal
animal.speak();  // "멍멍!" (실제 객체의 메소드)
```

### 강제 타입 변환 (Downcasting)
```java
Animal animal = new Dog("바둑이");
Dog dog = (Dog) animal;  // Animal → Dog
dog.fetch();  // Dog만의 메소드 사용 가능
```

### instanceof 연산자
```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.fetch();
}

// Java 16+ 패턴 매칭
if (animal instanceof Dog dog) {
    dog.fetch();  // 자동 캐스팅
}
```

---

## 07-6. 다형성 (Polymorphism)

### 개념
- 같은 타입이지만 다른 동작
- 부모 타입으로 다양한 자식 객체 다루기

### 예시
```java
public class AnimalShelter {
    public void makeSound(Animal animal) {
        animal.speak();  // 각 동물에 맞는 소리
    }
}

AnimalShelter shelter = new AnimalShelter();
shelter.makeSound(new Dog("바둑이"));   // 멍멍!
shelter.makeSound(new Cat("나비"));     // 야옹~
shelter.makeSound(new Bird("짹짹이"));  // 짹짹!
```

### 배열/컬렉션에서 다형성
```java
Animal[] animals = {
    new Dog("바둑이"),
    new Cat("나비"),
    new Bird("짹짹이")
};

for (Animal animal : animals) {
    animal.speak();
}
```

---

## 07-7. 추상 클래스

### abstract 키워드
```java
public abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    // 추상 메소드 - 구현 없음, 자식이 반드시 구현
    public abstract void speak();

    // 일반 메소드 - 구현 있음
    public void sleep() {
        System.out.println(name + "이(가) 잠을 잡니다");
    }
}
```

### 추상 클래스 특징
- `new`로 직접 객체 생성 불가
- 추상 메소드 0개 이상 포함 가능
- 자식 클래스가 추상 메소드 구현 필수

### 구현
```java
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + ": 멍멍!");
    }
}
```

---

## 07-8. final 클래스와 메소드

### final 클래스
```java
public final class String {  // 상속 불가
    // ...
}
```

### final 메소드
```java
public class Parent {
    public final void criticalMethod() {  // 오버라이딩 불가
        // ...
    }
}
```

---

## 상속 vs 인터페이스

| 구분 | 상속 (extends) | 인터페이스 (implements) |
|------|----------------|------------------------|
| 관계 | is-a (Dog is Animal) | can-do (Dog can Run) |
| 개수 | 1개만 | 여러 개 |
| 구현 | 있음 | 없음 (default 제외) |
| 용도 | 코드 재사용 | 규약 정의 |

---

## 실무 포인트
> Spring에서 상속보다 **인터페이스 + 구현체** 패턴을 더 많이 씀.
> 예: `UserRepository extends JpaRepository<User, Long>`

---

## 예제 파일
- `examples/Animal.java` - 부모 클래스
- `examples/Dog.java` - 자식 클래스
- `examples/Cat.java` - 자식 클래스
- `examples/InheritanceMain.java` - 사용 예제

---

## 다음 단계
→ Chapter 08: 인터페이스

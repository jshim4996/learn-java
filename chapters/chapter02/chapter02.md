# Chapter 02. 변수와 타입

> Java는 정적 타입 언어 - TypeScript보다 더 엄격함

---

## 학습 목표
- Java 기본 타입(Primitive Types) 이해
- 참조 타입과의 차이점 파악
- 타입 변환 규칙 이해

---

## 02-1. 변수 선언

### 기본 문법
```java
타입 변수명 = 값;
```

### JavaScript/TypeScript와 비교
```javascript
// JavaScript
let name = "홍길동";      // 타입 추론
const age = 25;           // 타입 추론

// TypeScript
let name: string = "홍길동";
const age: number = 25;
```

```java
// Java - 타입 필수, 더 세분화됨
String name = "홍길동";
int age = 25;            // 정수
double height = 175.5;   // 실수
```

### Java 10+ var 키워드
```java
var name = "홍길동";  // 타입 추론 (지역변수에서만 사용 가능)
var age = 25;         // int로 추론
```

---

## 02-2. 기본 타입 (Primitive Types)

### 정수 타입
| 타입 | 크기 | 범위 | 사용 예 |
|------|------|------|--------|
| `byte` | 1byte | -128 ~ 127 | 파일, 네트워크 데이터 |
| `short` | 2byte | -32,768 ~ 32,767 | 거의 안 씀 |
| `int` | 4byte | 약 ±21억 | **기본으로 사용** |
| `long` | 8byte | 매우 큼 | 큰 숫자 (L 접미사) |

```java
int count = 100;
long bigNumber = 10000000000L;  // L 필수!
```

### 실수 타입
| 타입 | 크기 | 정밀도 |
|------|------|--------|
| `float` | 4byte | 7자리 |
| `double` | 8byte | 15자리 (기본) |

```java
double price = 19.99;          // 기본
float rate = 3.14f;            // f 필수!
```

### 문자 타입
```java
char grade = 'A';              // 작은따옴표, 한 글자만
char unicode = '\uAC00';       // '가'
```

### 논리 타입
```java
boolean isActive = true;
boolean hasPermission = false;
```

### JS와 핵심 차이
| JavaScript | Java |
|------------|------|
| `number` 하나로 통일 | `int`, `long`, `float`, `double` 구분 |
| `true/false` (소문자) | `true/false` (소문자, 동일) |
| 문자열 = 문자 구분 없음 | `String` vs `char` 구분 |

---

## 02-3. 문자열 타입 (String)

### 기본 사용
```java
String message = "Hello, Java!";
String name = "홍길동";
```

### String은 참조 타입
- 기본 타입(primitive)이 아님
- 하지만 특별 취급받아 리터럴로 생성 가능

### 문자열 연결
```java
String fullName = "홍" + "길동";              // 연결
String info = "나이: " + 25;                  // 자동 변환
String template = String.format("이름: %s, 나이: %d", name, age);  // 포맷팅
```

### Java 15+ 텍스트 블록
```java
String json = """
    {
        "name": "홍길동",
        "age": 25
    }
    """;
```

---

## 02-4. 타입 변환

### 자동 타입 변환 (작은 → 큰)
```java
int intValue = 100;
long longValue = intValue;      // 자동 변환
double doubleValue = longValue; // 자동 변환
```

### 강제 타입 변환 (캐스팅)
```java
double pi = 3.14159;
int intPi = (int) pi;           // 3 (소수점 버림)

long big = 100L;
int small = (int) big;          // 명시적 캐스팅 필요
```

### 문자열 ↔ 숫자 변환
```java
// 문자열 → 숫자
String str = "123";
int num = Integer.parseInt(str);
double dbl = Double.parseDouble("3.14");

// 숫자 → 문자열
String s1 = String.valueOf(123);
String s2 = Integer.toString(123);
String s3 = "" + 123;           // 간단하지만 비효율
```

---

## 02-5. 변수 사용 범위 (Scope)

### 블록 스코프
```java
public void example() {
    int x = 10;           // 메소드 스코프

    if (true) {
        int y = 20;       // 블록 스코프
        System.out.println(x);  // OK
    }

    System.out.println(y);      // 에러! y는 if 블록 안에서만 유효
}
```

### JS와 동일
- `let`/`const`와 같은 블록 스코프
- `var` 같은 함수 스코프 개념 없음

---

## 02-6. 콘솔 출력

### 출력 메소드
```java
System.out.print("줄바꿈 없음");
System.out.println("줄바꿈 있음");
System.out.printf("포맷: %s, %d, %.2f%n", "문자열", 123, 3.14159);
```

### 포맷 지정자
| 지정자 | 설명 |
|--------|------|
| `%s` | 문자열 |
| `%d` | 정수 |
| `%f` | 실수 |
| `%.2f` | 소수점 2자리 |
| `%n` | 줄바꿈 |

---

## 실무 포인트
> 실무에서는 대부분 `int`, `long`, `double`, `boolean`, `String`만 사용.
> `byte`, `short`, `float`, `char`는 특수한 경우에만.

---

## 예제 파일
- `examples/Variables.java` - 변수 선언 기본
- `examples/TypeConversion.java` - 타입 변환
- `examples/StringExample.java` - 문자열 다루기

---

## 다음 단계
→ Chapter 03: 연산자

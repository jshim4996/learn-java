# Chapter 04. 조건문과 반복문

> JavaScript와 거의 동일 - Java 14+ 새 문법 주목

---

## 학습 목표
- if, switch, for, while 문법 확인
- Java 14+ switch 표현식 이해

---

## 04-1. if 문

### 기본 문법
```java
if (조건) {
    // 조건이 true일 때
} else if (다른조건) {
    // 다른조건이 true일 때
} else {
    // 모두 false일 때
}
```

### 예시
```java
int score = 85;

if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else if (score >= 70) {
    System.out.println("C");
} else {
    System.out.println("F");
}
```

**JS와 동일**

---

## 04-2. switch 문

### 전통적인 switch
```java
int day = 3;

switch (day) {
    case 1:
        System.out.println("월요일");
        break;
    case 2:
        System.out.println("화요일");
        break;
    case 3:
        System.out.println("수요일");
        break;
    default:
        System.out.println("기타");
}
```

### Java 14+ switch 표현식 (권장)
```java
int day = 3;

String dayName = switch (day) {
    case 1 -> "월요일";
    case 2 -> "화요일";
    case 3 -> "수요일";
    case 4 -> "목요일";
    case 5 -> "금요일";
    case 6, 7 -> "주말";    // 여러 값
    default -> "잘못된 값";
};

System.out.println(dayName);
```

### 새 switch 장점
- `break` 불필요
- 값 반환 가능
- 화살표(`->`) 문법으로 간결
- 여러 case 묶기 가능

---

## 04-3. for 문

### 기본 for
```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

### 향상된 for (for-each)
```java
int[] numbers = {1, 2, 3, 4, 5};

for (int num : numbers) {
    System.out.println(num);
}
```

**JS 비교**:
```javascript
// JavaScript
for (const num of numbers) { ... }

// Java
for (int num : numbers) { ... }   // 콜론(:) 사용
```

---

## 04-4. while 문

### while
```java
int count = 0;

while (count < 5) {
    System.out.println(count);
    count++;
}
```

### do-while
```java
int count = 0;

do {
    System.out.println(count);
    count++;
} while (count < 5);
```

**차이**: do-while은 최소 1번 실행

**JS와 동일**

---

## 04-5. break와 continue

### break - 반복 종료
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;  // 반복 종료
    }
    System.out.println(i);  // 0, 1, 2, 3, 4
}
```

### continue - 다음 반복으로
```java
for (int i = 0; i < 5; i++) {
    if (i == 2) {
        continue;  // 2 건너뜀
    }
    System.out.println(i);  // 0, 1, 3, 4
}
```

### 라벨 break (중첩 반복문 탈출)
```java
outer:
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (i == 1 && j == 1) {
            break outer;  // 바깥 반복문까지 종료
        }
        System.out.println(i + ", " + j);
    }
}
```

**JS와 차이**: JS에도 라벨 있지만 거의 안 씀. Java도 마찬가지.

---

## JS와 차이 요약

| 항목 | JavaScript | Java |
|------|------------|------|
| for-each 문법 | `for (x of arr)` | `for (x : arr)` |
| switch 표현식 | 없음 | Java 14+ 지원 |
| 변수 선언 | `let i = 0` | `int i = 0` |

---

## 실무 포인트
> Spring Boot에서 조건/반복은 주로 Stream API로 대체.
> 기본 문법은 알아야 하지만, Chapter 17 스트림 배우면 더 간결해짐.

---

## 예제 파일
- `examples/IfExample.java` - if 문
- `examples/SwitchExample.java` - switch 문
- `examples/LoopExample.java` - 반복문

---

## 다음 단계
→ Chapter 05: 참조 타입

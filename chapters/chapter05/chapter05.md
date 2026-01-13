# Chapter 05. 참조 타입

> 기본 타입 vs 참조 타입 - JS 객체 개념과 유사

---

## 학습 목표
- 기본 타입과 참조 타입 차이 이해
- 배열 사용법
- null과 NullPointerException

---

## 05-1. 기본 타입 vs 참조 타입

### 기본 타입 (Primitive)
```java
int a = 10;     // 값 자체를 저장
int b = a;      // 값 복사
b = 20;
// a = 10, b = 20 (독립적)
```

### 참조 타입 (Reference)
```java
int[] arr1 = {1, 2, 3};  // 주소를 저장
int[] arr2 = arr1;       // 주소 복사
arr2[0] = 100;
// arr1[0] = 100 (같은 배열 참조)
```

### JS와 비교
```javascript
// JavaScript - 객체/배열은 참조
const arr1 = [1, 2, 3];
const arr2 = arr1;
arr2[0] = 100;  // arr1[0]도 100
```

**동일한 개념** - 객체/배열은 참조로 전달

### 참조 타입 종류
- 배열 (Array)
- 클래스 (Class) - String 포함
- 인터페이스 (Interface)
- 열거형 (Enum)

---

## 05-2. null과 NullPointerException

### null
```java
String name = null;   // 참조 없음
int[] arr = null;     // 참조 없음
```

### NullPointerException (NPE)
```java
String name = null;
int length = name.length();  // NPE 발생!
```

### null 체크
```java
// 전통적인 방법
if (name != null) {
    System.out.println(name.length());
}

// Java 8+ Optional (나중에 배움)
```

### JS와 비교
| JavaScript | Java |
|------------|------|
| `null`, `undefined` | `null`만 있음 |
| TypeError | NullPointerException |

---

## 05-3. 문자열 (String) - 심화

### 문자열 리터럴 풀
```java
String s1 = "hello";
String s2 = "hello";
String s3 = new String("hello");

s1 == s2   // true (같은 풀의 객체)
s1 == s3   // false (다른 객체)
s1.equals(s3)  // true (내용 비교)
```

### 문자열 불변성 (Immutable)
```java
String str = "Hello";
str = str + " World";  // 새 객체 생성 (원본 변경 X)
```

### 문자열 메소드
```java
String text = "Hello, Java!";

text.length()           // 12
text.charAt(0)          // 'H'
text.indexOf("Java")    // 7
text.substring(0, 5)    // "Hello"
text.split(", ")        // ["Hello", "Java!"]
text.replace("Java", "World")
text.trim()             // 앞뒤 공백 제거
text.isEmpty()          // false
text.isBlank()          // false (Java 11+)
```

---

## 05-4. 배열 (Array)

### 배열 선언과 생성
```java
// 선언 + 생성
int[] numbers = new int[5];       // 크기 5, 기본값 0

// 선언 + 초기화
int[] scores = {90, 85, 77, 92};

// 선언 후 생성
int[] data;
data = new int[]{1, 2, 3};
```

### 배열 접근
```java
int[] arr = {10, 20, 30};

arr[0]          // 10
arr[1] = 25;    // 수정
arr.length      // 3 (메소드 아님, 속성!)
```

### JS와 차이
| JavaScript | Java |
|------------|------|
| 동적 크기 | **고정 크기** |
| `arr.length` (속성) | `arr.length` (속성) |
| `arr.push(x)` | 불가 (크기 고정) |
| 다양한 타입 혼합 | **동일 타입만** |

### 배열 순회
```java
int[] arr = {1, 2, 3, 4, 5};

// for문
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// 향상된 for문
for (int num : arr) {
    System.out.println(num);
}
```

---

## 05-5. 다차원 배열

### 2차원 배열
```java
// 선언 + 생성
int[][] matrix = new int[3][4];   // 3행 4열

// 선언 + 초기화
int[][] grid = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// 접근
grid[0][0]  // 1
grid[1][2]  // 6
grid.length        // 3 (행 개수)
grid[0].length     // 3 (열 개수)
```

### 순회
```java
for (int i = 0; i < grid.length; i++) {
    for (int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j] + " ");
    }
    System.out.println();
}

// 향상된 for
for (int[] row : grid) {
    for (int num : row) {
        System.out.print(num + " ");
    }
    System.out.println();
}
```

---

## 05-6. 열거형 (Enum)

### 기본 사용
```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

Day today = Day.WEDNESDAY;

if (today == Day.WEDNESDAY) {
    System.out.println("수요일입니다");
}
```

### switch와 함께
```java
String message = switch (today) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "평일";
    case SATURDAY, SUNDAY -> "주말";
};
```

### JS와 비교
```javascript
// JavaScript - 객체로 흉내
const Day = {
    MONDAY: 'MONDAY',
    TUESDAY: 'TUESDAY',
    // ...
};

// TypeScript - enum 있음
enum Day {
    MONDAY,
    TUESDAY,
    // ...
}
```

---

## 실무 포인트
> 배열보다 **ArrayList** (Chapter 15)를 더 많이 씀.
> 배열은 크기 고정이라 불편 → 컬렉션 프레임워크 사용.

---

## 예제 파일
- `examples/ArrayExample.java` - 배열 기본
- `examples/StringMethods.java` - 문자열 메소드
- `examples/EnumExample.java` - 열거형

---

## 다음 단계
→ Chapter 06: 클래스

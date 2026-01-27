# Chapter 10. 예외 처리

> 안정적인 프로그램을 위한 필수 기술

---

## 학습 목표
- 예외와 에러의 차이
- try-catch-finally
- throws와 throw
- 사용자 정의 예외

---

## 10-1. 예외와 예외 클래스

### 예외 계층 구조
```
Throwable
├── Error (시스템 에러, 처리 불가)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception (예외, 처리 가능)
    ├── RuntimeException (Unchecked)
    │   ├── NullPointerException
    │   ├── IndexOutOfBoundsException
    │   └── IllegalArgumentException
    └── 기타 Exception (Checked)
        ├── IOException
        └── SQLException
```

### Checked vs Unchecked

| 구분 | Checked Exception | Unchecked Exception |
|------|-------------------|---------------------|
| 처리 | 컴파일 시 강제 | 선택 |
| 예시 | IOException, SQLException | NullPointerException |
| 발생 원인 | 외부 환경 (파일, 네트워크) | 프로그래밍 실수 |

### JS와 비교
```javascript
// JavaScript - 모든 예외가 unchecked
try {
    throw new Error("에러 발생");
} catch (e) {
    console.error(e.message);
}
```

```java
// Java - checked/unchecked 구분
try {
    FileReader fr = new FileReader("file.txt");  // checked
} catch (FileNotFoundException e) {
    System.out.println("파일 없음");
}
```

---

## 10-2. try-catch-finally

### 기본 구조
```java
try {
    // 예외 발생 가능 코드
} catch (예외타입 변수) {
    // 예외 처리
} finally {
    // 항상 실행 (생략 가능)
}
```

### 예시
```java
try {
    int result = 10 / 0;
    System.out.println(result);
} catch (ArithmeticException e) {
    System.out.println("0으로 나눌 수 없습니다");
    System.out.println("에러 메시지: " + e.getMessage());
} finally {
    System.out.println("항상 실행됨");
}
```

### 다중 catch
```java
try {
    String str = null;
    System.out.println(str.length());  // NullPointerException
    int[] arr = {1, 2, 3};
    System.out.println(arr[10]);       // IndexOutOfBoundsException
} catch (NullPointerException e) {
    System.out.println("null 참조 에러");
} catch (IndexOutOfBoundsException e) {
    System.out.println("인덱스 범위 초과");
} catch (Exception e) {
    System.out.println("기타 예외: " + e.getMessage());
}
```

### 멀티 catch (Java 7+)
```java
try {
    // ...
} catch (NullPointerException | IndexOutOfBoundsException e) {
    System.out.println("예외 발생: " + e.getMessage());
}
```

---

## 10-3. try-with-resources (Java 7+)

### 자동 리소스 관리
```java
// Before (finally에서 직접 close)
FileReader fr = null;
try {
    fr = new FileReader("file.txt");
    // 사용
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (fr != null) {
        try { fr.close(); } catch (IOException e) { }
    }
}

// After (자동 close)
try (FileReader fr = new FileReader("file.txt")) {
    // 사용
} catch (IOException e) {
    e.printStackTrace();
}
// fr.close() 자동 호출
```

### 여러 리소스
```java
try (
    FileInputStream fis = new FileInputStream("input.txt");
    FileOutputStream fos = new FileOutputStream("output.txt")
) {
    // 사용
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## 10-4. 예외 떠넘기기 (throws)

### throws 키워드
```java
public void readFile(String path) throws FileNotFoundException {
    FileReader fr = new FileReader(path);
    // 예외를 호출한 쪽에서 처리하도록 떠넘김
}

// 호출하는 쪽에서 처리
public void process() {
    try {
        readFile("data.txt");
    } catch (FileNotFoundException e) {
        System.out.println("파일을 찾을 수 없습니다");
    }
}
```

### 다중 throws
```java
public void doSomething() throws IOException, SQLException {
    // ...
}
```

---

## 10-5. 예외 발생시키기 (throw)

### throw 키워드
```java
public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("나이는 0 이상이어야 합니다");
    }
    this.age = age;
}
```

### 예외 다시 던지기
```java
public void process() {
    try {
        riskyOperation();
    } catch (Exception e) {
        System.out.println("로깅: " + e.getMessage());
        throw e;  // 다시 던지기
    }
}
```

---

## 10-6. 사용자 정의 예외

### Custom Exception 만들기
```java
// Unchecked Exception (RuntimeException 상속)
public class InvalidUserException extends RuntimeException {

    public InvalidUserException() {
        super("유효하지 않은 사용자입니다");
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### 사용
```java
public void validateUser(User user) {
    if (user == null) {
        throw new InvalidUserException("사용자가 null입니다");
    }
    if (user.getName() == null || user.getName().isEmpty()) {
        throw new InvalidUserException("사용자 이름이 없습니다");
    }
}

// 호출
try {
    validateUser(user);
} catch (InvalidUserException e) {
    System.out.println("검증 실패: " + e.getMessage());
}
```

---

## 10-7. 예외 처리 Best Practices

### 1. 구체적인 예외 잡기
```java
// Bad
catch (Exception e) { }

// Good
catch (FileNotFoundException e) { }
```

### 2. 예외 무시하지 않기
```java
// Bad
catch (Exception e) { }

// Good
catch (Exception e) {
    logger.error("에러 발생", e);
}
```

### 3. 예외 메시지에 정보 담기
```java
throw new IllegalArgumentException(
    "유효하지 않은 사용자 ID: " + userId
);
```

### 4. checked vs unchecked 선택
- **Checked**: 호출자가 복구할 수 있는 경우
- **Unchecked**: 프로그래밍 오류 (권장 - Spring 스타일)

---

## 실무 포인트
> Spring에서는 대부분 **RuntimeException** 사용.
> `@ExceptionHandler`로 전역 예외 처리.
> checked exception은 거의 안 씀.

---

## 예제 파일
- `examples/TryCatchExample.java` - 기본 예외 처리
- `examples/ThrowExample.java` - throw/throws
- `examples/CustomException.java` - 사용자 정의 예외

---

## 다음 단계
→ Chapter 11: 멀티 스레드

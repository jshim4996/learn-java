# Chapter 01. 자바 시작하기

> Node.js 경험자를 위한 Java 입문

---

## 학습 목표
- Java 개발 환경 구축
- Java 컴파일/실행 과정 이해
- 기본 프로젝트 구조 파악

---

## 01-1. JDK 설치

### 핵심 개념
- **JDK (Java Development Kit)**: 개발 도구 (컴파일러 포함)
- **JRE (Java Runtime Environment)**: 실행 환경
- **JVM (Java Virtual Machine)**: 바이트코드 실행 엔진

### Node.js와 비교
| Node.js | Java |
|---------|------|
| `node` 설치하면 끝 | JDK 설치 필요 |
| 인터프리터 방식 | 컴파일 → 실행 |
| `node app.js` | `javac App.java` → `java App` |

### 설치 방법 (macOS)
```bash
# Homebrew로 설치
brew install openjdk@17

# 환경변수 설정
echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc

# 확인
java -version
javac -version
```

---

## 01-2. 바이트코드와 JVM

### 실행 과정
```
HelloWorld.java  →  javac  →  HelloWorld.class  →  java  →  실행
   (소스코드)      (컴파일)     (바이트코드)       (JVM)
```

### 왜 이렇게?
- **Write Once, Run Anywhere**: 바이트코드는 어느 OS에서든 JVM만 있으면 실행
- Node.js는 V8 엔진이 직접 JS 해석, Java는 중간에 바이트코드 단계가 있음

---

## 01-3. Hello World

### 예제 파일
`examples/HelloWorld.java`

### 실행 방법
```bash
cd examples
javac HelloWorld.java   # 컴파일 → HelloWorld.class 생성
java HelloWorld         # 실행 (.class 확장자 안 붙임)
```

### 코드 구조 설명
```java
public class HelloWorld {           // 파일명과 클래스명 일치 필수!
    public static void main(String[] args) {  // 진입점 (엔트리포인트)
        System.out.println("Hello, World!");
    }
}
```

| 요소 | 설명 |
|------|------|
| `public class` | 공개 클래스, 파일당 1개 |
| `main` 메소드 | 프로그램 시작점 (Node의 진입 파일과 유사) |
| `String[] args` | 커맨드라인 인자 (Node의 `process.argv`) |
| `System.out.println` | 콘솔 출력 (`console.log`) |

---

## 01-4. IDE 설정 (IntelliJ IDEA)

### 추천 IDE
- **IntelliJ IDEA Community** (무료) - 강력 추천
- VS Code + Java Extension Pack - 가벼움

### IntelliJ 프로젝트 생성
1. New Project
2. Language: Java
3. Build System: **Gradle** (추천) 또는 Maven
4. JDK: 17 선택
5. Create

### 프로젝트 구조
```
project/
├── src/
│   └── main/
│       └── java/
│           └── HelloWorld.java
├── build.gradle    # package.json 같은 역할
└── settings.gradle
```

---

## 01-5. 코드 기본 규칙

### 주석
```java
// 한 줄 주석

/*
 * 여러 줄 주석
 */

/**
 * JavaDoc 주석 (문서화용)
 * @param args 인자 설명
 */
```

### 규칙
- 모든 실행문은 **세미콜론(;)** 필수 (JS의 ASI 없음)
- 클래스명은 **PascalCase**
- 메소드/변수명은 **camelCase**
- 파일명 = public 클래스명

---

## 실무 포인트
> Spring Boot 프로젝트에서는 이 모든 설정이 자동화됨.
> 지금은 Java가 어떻게 동작하는지 이해하는 게 목적.

---

## 다음 단계
→ Chapter 02: 변수와 타입

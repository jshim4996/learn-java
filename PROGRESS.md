# Java 웹 백엔드 학습 목록

> 목표: Spring Boot 백엔드 개발 → 카톨릭 기도앱 백엔드 제작

---

## AI 학습 가이드

### 역할
- AI는 이 커리큘럼으로 학습을 시켜주는 **시니어 개발자이자 교수**
- 사용자는 프론트엔드 + Node.js 백엔드 6년 경험자
- 체크 안 된 첫 번째 `[ ]` 항목 = 현재 학습할 내용

### 학습 진행 흐름 (5단계)

**1단계: 진행도 확인**
```
사용자: "오늘 어디 할 차례야"
AI: 이 파일을 읽고 체크 안 된 [ ] 첫 번째 항목을 찾음
AI: "Chapter XX의 'OOO' 시작합니다"
```

**2단계: 설명**
```
사용자: "학습 내용 보여줘"
AI: 해당 챕터의 doc 파일에서 그 항목 하나만 설명
```

**3단계: 예제**
```
사용자: "예제 보여줘"
AI: examples/ 폴더의 예제 코드를 보여줌
    사용자가 직접 타이핑할 수 있도록 코드 제시
```

**4단계: 과제**
```
사용자: "과제 줘"
AI: 학습한 내용을 적용할 수 있는 과제 제시
    - 요구사항 명확히
    - 난이도는 방금 배운 내용 수준으로
```

**5단계: 평가 및 피드백**
```
사용자: [코드 제출]
AI: 제출된 코드 평가
    - 요구사항대로 동작하는가? → 통과/재시도
    - 피드백: 개선점, 잘한 점 (가볍게)
    - 통과 시 체크박스 [x] 업데이트
```

### 평가 기준

```
□ 학습한 문법/방법을 이해하고 적용했는가
```

> 변수명, 가독성, 효율성 등은 **피드백**으로만 가볍게 언급
> 초보 학습자에게 과한 기준을 요구하지 않음

### AI 행동 규칙

**해야 할 것**
- 항목 **하나씩** 진행 (step by step)
- 간단명료하게 설명
- 실행 가능한 예제 코드 제공
- 실무에서 어떻게 쓰이는지 한 줄 설명
- 과제 피드백은 구체적으로
- Node.js/JavaScript와 비교 설명 (필요시)

**하지 말아야 할 것**
- 한 번에 여러 항목 설명
- 챕터 전체 내용을 한 번에 설명
- 너무 긴 설명
- 사용자가 요청하지 않은 내용 추가
- 초보자에게 과한 기준 요구

### 파일 구조
```
chapters/
├── chapter01/
│   ├── chapter01.md      ← doc (개념 설명)
│   └── examples/         ← 예제 코드
│       ├── HelloWorld.java
│       └── ...
├── chapter02/
│   ├── chapter02.md
│   └── examples/
└── ...
```

### 진행도 추적
- `[ ]` = 미완료
- `[x]` = 완료
- 체크 안 된 첫 번째 항목 = 현재 학습할 내용

---

## STEP 1. Java 기초 문법 (필수)

### Chapter 01. 자바 시작하기
> **doc**: `chapters/chapter01/chapter01.md`
> **examples**: `chapters/chapter01/examples/`
> - `HelloWorld.java` - 첫 번째 프로그램
> - `CommandLineArgs.java` - 커맨드라인 인자

- [ ] JDK 설치
- [ ] 환경 변수 설정
- [ ] 바이트코드 파일과 자바 가상 머신
- [ ] 소스 작성부터 실행까지
- [ ] IDE 설치 및 프로젝트 생성
- [ ] 코드 용어 이해
- [ ] 코드 주석
- [ ] 실행문과 세미콜론

### Chapter 02. 변수와 타입
> **doc**: `chapters/chapter02/chapter02.md`
> **examples**: `chapters/chapter02/examples/`
> - `Variables.java` - 변수 선언 기본
> - `TypeConversion.java` - 타입 변환
> - `StringExample.java` - 문자열 다루기

- [ ] 변수 선언
- [ ] 정수 타입 (byte, short, int, long)
- [ ] 문자 타입 (char)
- [ ] 실수 타입 (float, double)
- [ ] 논리 타입 (boolean)
- [ ] 문자열 타입 (String)
- [ ] 자동 타입 변환
- [ ] 강제 타입 변환
- [ ] 변수 사용 범위
- [ ] 콘솔로 변수값 출력

### Chapter 03. 연산자
> **doc**: `chapters/chapter03/chapter03.md`
> **examples**: `chapters/chapter03/examples/`
> - `Operators.java` - 연산자 종합

- [ ] 부호/증감 연산자
- [ ] 산술 연산자
- [ ] 비교 연산자
- [ ] 논리 연산자
- [ ] 대입 연산자
- [ ] 삼항(조건) 연산자
- [ ] 연산의 방향과 우선순위

### Chapter 04. 조건문과 반복문
> **doc**: `chapters/chapter04/chapter04.md`
> **examples**: `chapters/chapter04/examples/`
> - `IfExample.java` - if 조건문
> - `SwitchExample.java` - switch 문
> - `LoopExample.java` - 반복문

- [ ] if 문
- [ ] switch 문
- [ ] for 문
- [ ] while 문
- [ ] break 문
- [ ] continue 문

---

## STEP 2. 객체지향 (필수)

### Chapter 05. 참조 타입
> **doc**: `chapters/chapter05/chapter05.md`
> **examples**: `chapters/chapter05/examples/`
> - `ArrayExample.java` - 배열 기본
> - `StringMethods.java` - 문자열 메소드
> - `EnumExample.java` - 열거형

- [ ] 기본 타입 vs 참조 타입
- [ ] null과 NullPointerException
- [ ] 문자열(String) 타입
- [ ] 배열(Array) 타입
- [ ] 다차원 배열
- [ ] 향상된 for 문
- [ ] 열거(Enum) 타입

### Chapter 06. 클래스
> **doc**: `chapters/chapter06/chapter06.md`
> **examples**: `chapters/chapter06/examples/`
> - `Person.java` - 기본 클래스
> - `PersonMain.java` - 사용 예제
> - `Calculator.java` - 메소드 예제
> - `StaticExample.java` - static 예제

- [ ] 객체지향 프로그래밍
- [ ] 객체와 클래스
- [ ] 클래스 선언
- [ ] 객체 생성과 클래스 변수
- [ ] 필드 선언과 사용
- [ ] 생성자 선언과 호출
- [ ] 메소드 선언과 호출
- [ ] 인스턴스 멤버
- [ ] 정적 멤버
- [ ] final 필드와 상수
- [ ] 패키지
- [ ] 접근 제한자
- [ ] Getter와 Setter

### Chapter 07. 상속
> **doc**: `chapters/chapter07/chapter07.md`
> **examples**: `chapters/chapter07/examples/`
> - `Animal.java` - 부모 클래스 (추상)
> - `Dog.java` - 자식 클래스
> - `Cat.java` - 자식 클래스
> - `InheritanceMain.java` - 사용 예제

- [ ] 상속 개념
- [ ] 클래스 상속
- [ ] 부모 생성자 호출
- [ ] 메소드 재정의 (Override)
- [ ] 타입 변환
- [ ] 다형성
- [ ] 추상 클래스

### Chapter 08. 인터페이스
> **doc**: `chapters/chapter08/chapter08.md`
> **examples**: `chapters/chapter08/examples/`
> - `Movable.java` - 인터페이스
> - `Flyable.java` - 인터페이스
> - `Car.java` - 구현 클래스
> - `Bird.java` - 다중 구현
> - `InterfaceMain.java` - 사용 예제

- [ ] 인터페이스의 역할
- [ ] 인터페이스와 구현 클래스 선언
- [ ] 추상 메소드
- [ ] 디폴트 메소드
- [ ] 다중 인터페이스 구현
- [ ] 다형성

### Chapter 09. 제네릭
> **doc**: `chapters/chapter09/chapter09.md`
> **examples**: `chapters/chapter09/examples/`
> - `GenericBox.java` - 제네릭 클래스
> - `GenericMethod.java` - 제네릭 메소드
> - `WildcardExample.java` - 와일드카드

- [ ] 제네릭이란?
- [ ] 제네릭 타입 (클래스, 인터페이스)
- [ ] 제네릭 메소드
- [ ] 제한된 타입 파라미터 (<T extends 상위타입>)
- [ ] 와일드카드 타입 (<?>, <? extends>, <? super>)

---

## STEP 3. 실무 필수 (필수)

### Chapter 10. 예외 처리
> **doc**: `chapters/chapter10/chapter10.md`
> **examples**: `chapters/chapter10/examples/`
> - `TryCatchExample.java` - 기본 예외 처리
> - `ThrowExample.java` - throw/throws
> - `CustomException.java` - 사용자 정의 예외

- [ ] 예외와 예외 클래스
- [ ] 예외 처리 코드 (try-catch-finally)
- [ ] 예외 종류에 따른 처리
- [ ] 예외 떠넘기기 (throws)
- [ ] 사용자 정의 예외

### Chapter 11. 멀티 스레드
> **doc**: `chapters/chapter11/chapter11.md`
> **examples**: `chapters/chapter11/examples/`
> - `ThreadBasic.java` - 스레드 기본
> - `RunnableExample.java` - Runnable 구현
> - `SynchronizedExample.java` - 동기화
> - `ExecutorExample.java` - 스레드 풀

- [ ] 프로세스와 스레드
- [ ] 메인 스레드
- [ ] 스레드 생성 (Thread 상속, Runnable 구현)
- [ ] 스레드 이름과 상태
- [ ] 동기화 (synchronized)
- [ ] 스레드 풀 (ExecutorService)

### Chapter 12. 컬렉션 자료구조
> **doc**: `chapters/chapter12/chapter12.md`
> **examples**: `chapters/chapter12/examples/`
> - `ListExample.java` - ArrayList 사용
> - `SetExample.java` - HashSet 사용
> - `MapExample.java` - HashMap 사용

- [ ] 컬렉션 프레임워크
- [ ] List 컬렉션 (ArrayList, LinkedList)
- [ ] Set 컬렉션 (HashSet)
- [ ] Map 컬렉션 (HashMap)

### Chapter 13. 람다식
> **doc**: `chapters/chapter13/chapter13.md`
> **examples**: `chapters/chapter13/examples/`
> - `LambdaBasic.java` - 람다 기본
> - `FunctionalInterface.java` - 함수형 인터페이스
> - `MethodReference.java` - 메소드 참조

- [ ] 람다식이란?
- [ ] 매개변수가 있는 람다식
- [ ] 리턴값이 있는 람다식
- [ ] 메소드 참조

### Chapter 14. 스트림 요소 처리
> **doc**: `chapters/chapter14/chapter14.md`
> **examples**: `chapters/chapter14/examples/`
> - `StreamBasic.java` - 기본 사용
> - `StreamCollect.java` - 수집 연산
> - `StreamPractice.java` - 실무 패턴

- [ ] 스트림이란?
- [ ] 요소 걸러내기 (filter)
- [ ] 요소 변환 (map)
- [ ] 요소 수집 (collect)

---

## STEP 4. Spring Boot + 실전 프로젝트 (카톨릭 기도앱 백엔드)
> **doc**: `chapters/spring-boot/spring-boot.md`
>
> 카톨릭 기도앱 백엔드를 직접 만들면서 Spring Boot를 학습합니다.
> 각 항목을 실제 프로젝트에 적용하며 진행합니다.

### Spring 기초
- [ ] Spring과 Spring Boot 차이
- [ ] Spring Boot 프로젝트 생성
- [ ] 프로젝트 구조 이해
- [ ] application.properties / yml

### Spring MVC
- [ ] @Controller, @RestController
- [ ] @RequestMapping, @GetMapping, @PostMapping
- [ ] @RequestBody, @ResponseBody
- [ ] @PathVariable, @RequestParam
- [ ] DTO (Data Transfer Object)

### 의존성 주입 (DI)
- [ ] @Component, @Service, @Repository
- [ ] @Autowired
- [ ] 생성자 주입

### Spring Data JPA
- [ ] JPA 개념
- [ ] Entity 클래스
- [ ] @Entity, @Id, @GeneratedValue
- [ ] Repository 인터페이스
- [ ] 기본 CRUD 메소드
- [ ] 쿼리 메소드
- [ ] @Query (JPQL)

### 기타
- [ ] Lombok (@Getter, @Setter, @Builder 등)
- [ ] 유효성 검사 (@Valid)
- [ ] 예외 처리 (@ExceptionHandler)

### 트랜잭션
- [ ] @Transactional 개념
- [ ] 트랜잭션 전파 (Propagation)
- [ ] 롤백 규칙 (rollbackFor)
- [ ] 읽기 전용 트랜잭션 (readOnly)

### 테스트
- [ ] JUnit 기본 (@Test, @BeforeEach, @AfterEach)
- [ ] 단언문 (Assertions)
- [ ] @SpringBootTest
- [ ] MockMvc (Controller 테스트)
- [ ] @MockBean (Service 모킹)
- [ ] @DataJpaTest (Repository 테스트)

### 인증/보안
- [ ] Spring Security 기본 개념
- [ ] JWT (JSON Web Token) 개념
- [ ] 로그인/로그아웃 구현
- [ ] 토큰 발급/검증

### 파일 처리
- [ ] MultipartFile (파일 업로드)
- [ ] 파일 저장/조회
- [ ] 이미지 리사이징 (선택)

### 푸시 알림
- [ ] Firebase Cloud Messaging (FCM) 설정
- [ ] FCM 토큰 관리
- [ ] 푸시 알림 발송

### 캐싱
- [ ] Redis 기초
- [ ] Spring Data Redis
- [ ] 캐시 적용 (@Cacheable)

### 배포
- [ ] AWS EC2 또는 NCP
- [ ] Docker 기초
- [ ] HTTPS (SSL 인증서)
- [ ] CI/CD (GitHub Actions)

---

## 나중에 필요할 때

| 주제 | 언제 |
|------|------|
| 중첩 클래스 | 콜백, 이벤트 처리할 때 |
| WebSocket | 실시간 채팅 기능 만들 때 |

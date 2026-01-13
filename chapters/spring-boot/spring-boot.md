# Spring Boot 학습 가이드

> Node.js/Express 경험자를 위한 Spring Boot 입문

---

## 학습 목표
- Spring Boot 프로젝트 구조
- REST API 개발
- 의존성 주입 (DI)
- JPA 데이터베이스 연동

---

## 1. Spring vs Spring Boot

### Express와 비교
| Express.js | Spring Boot |
|------------|-------------|
| `npm init` | Spring Initializr |
| `package.json` | `build.gradle` |
| `app.use()` 미들웨어 | `@Component` 빈 |
| `app.get('/api')` | `@GetMapping("/api")` |
| `req, res` | `@RequestBody`, `@ResponseBody` |

### Spring Boot 장점
- **자동 설정**: 복잡한 XML 설정 불필요
- **내장 서버**: Tomcat 내장 (별도 설치 X)
- **의존성 관리**: 버전 호환성 자동 처리

---

## 2. 프로젝트 생성

### Spring Initializr
1. https://start.spring.io 접속
2. 설정:
   - Project: **Gradle - Groovy**
   - Language: **Java**
   - Spring Boot: **3.x** (최신 안정 버전)
   - Packaging: **Jar**
   - Java: **17**

### 주요 의존성
```
- Spring Web
- Spring Data JPA
- H2 Database (개발용)
- Lombok
- Validation
```

### 프로젝트 구조
```
src/
├── main/
│   ├── java/
│   │   └── com/example/demo/
│   │       ├── DemoApplication.java    # 메인
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── entity/
│   │       └── dto/
│   └── resources/
│       ├── application.yml
│       └── static/
└── test/
```

---

## 3. 핵심 어노테이션

### 컴포넌트 등록
```java
@Component      // 일반 빈
@Controller     // 웹 컨트롤러 (뷰 반환)
@RestController // REST API 컨트롤러 (@Controller + @ResponseBody)
@Service        // 비즈니스 로직
@Repository     // 데이터 접근
@Configuration  // 설정 클래스
```

### 의존성 주입
```java
@Autowired      // 자동 주입 (필드/생성자/세터)
@RequiredArgsConstructor  // Lombok - 생성자 주입 (권장)
```

---

## 4. REST Controller

### 기본 구조
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 생성자 주입 (권장)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 전체 조회
    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.findAll();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    // 생성
    @PostMapping
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

    // 수정
    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserRequest request) {
        return userService.update(id, request);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

### Express 비교
```javascript
// Express
app.get('/api/users', (req, res) => {...});
app.get('/api/users/:id', (req, res) => {...});
app.post('/api/users', (req, res) => {...});

// Spring
@GetMapping
@GetMapping("/{id}")
@PostMapping
```

---

## 5. 요청/응답 처리

### @PathVariable - 경로 변수
```java
// GET /api/users/123
@GetMapping("/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}

// Express: req.params.id
```

### @RequestParam - 쿼리 파라미터
```java
// GET /api/users?page=1&size=10
@GetMapping
public List<User> getUsers(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size) {
    return userService.findAll(page, size);
}

// Express: req.query.page
```

### @RequestBody - 요청 본문
```java
// POST /api/users
// Body: {"name": "홍길동", "email": "hong@test.com"}
@PostMapping
public User createUser(@RequestBody CreateUserRequest request) {
    return userService.create(request);
}

// Express: req.body
```

---

## 6. DTO (Data Transfer Object)

### Request DTO
```java
@Getter
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;

    @Min(value = 0, message = "나이는 0 이상이어야 합니다")
    private Integer age;
}
```

### Response DTO
```java
@Getter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
```

---

## 7. Service 계층

```java
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::from)
                .toList();
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse create(CreateUserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
        return UserResponse.from(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
```

---

## 8. Entity와 JPA

### Entity 클래스
```java
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true)
    private String email;

    private Integer age;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
```

### Repository
```java
public interface UserRepository extends JpaRepository<User, Long> {

    // 쿼리 메소드
    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String name);
    List<User> findByAgeGreaterThan(int age);

    // JPQL
    @Query("SELECT u FROM User u WHERE u.age >= :age")
    List<User> findAdults(@Param("age") int age);
}
```

### Sequelize/Prisma 비교
```javascript
// Sequelize
User.findAll({ where: { age: { [Op.gt]: 18 } } });

// Spring JPA
userRepository.findByAgeGreaterThan(18);
```

---

## 9. Lombok

### 자주 쓰는 어노테이션
```java
@Getter              // getter 자동 생성
@Setter              // setter 자동 생성
@NoArgsConstructor   // 기본 생성자
@AllArgsConstructor  // 전체 필드 생성자
@RequiredArgsConstructor  // final 필드 생성자 (DI용)
@Builder             // 빌더 패턴
@Data                // Getter + Setter + toString + equals + hashCode
@ToString            // toString
@EqualsAndHashCode   // equals, hashCode
```

### 예시
```java
// Before
public class User {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

// After (Lombok)
@Getter @Setter
public class User {
    private String name;
    private int age;
}
```

---

## 10. 예외 처리

### 전역 예외 핸들러
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("USER_NOT_FOUND", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("VALIDATION_ERROR", message));
    }
}

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}
```

### 커스텀 예외
```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("사용자를 찾을 수 없습니다: " + id);
    }
}
```

---

## 11. application.yml 설정

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: create  # 개발시 create, 운영시 validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true

  h2:
    console:
      enabled: true
      path: /h2-console

server:
  port: 8080
```

---

## 12. 실행과 테스트

### 실행
```bash
./gradlew bootRun
```

### API 테스트 (curl)
```bash
# 전체 조회
curl http://localhost:8080/api/users

# 단건 조회
curl http://localhost:8080/api/users/1

# 생성
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"홍길동","email":"hong@test.com"}'

# 삭제
curl -X DELETE http://localhost:8080/api/users/1
```

---

## 실무 포인트

### 계층 구조
```
Controller → Service → Repository → Database
    ↓           ↓           ↓
   DTO        Entity      Entity
```

### 핵심 원칙
1. **Controller**: 요청/응답만 처리
2. **Service**: 비즈니스 로직
3. **Repository**: 데이터 접근
4. **DTO**: 계층 간 데이터 전달
5. **Entity**: DB 테이블 매핑

---

## 예제 파일
- `examples/` 폴더에 실습용 예제 코드

---

## 다음 단계
→ 실전 프로젝트: 카톨릭 기도앱 백엔드

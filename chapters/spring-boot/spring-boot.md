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

## 13. 트랜잭션 (@Transactional)

### 기본 사용
```java
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    @Transactional  // 메소드 내 모든 DB 작업이 하나의 트랜잭션
    public void createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        paymentService.processPayment(order);  // 실패 시 order도 롤백
    }
}
```

### 읽기 전용 트랜잭션
```java
@Transactional(readOnly = true)  // 조회 전용 - 성능 최적화
public List<Order> findOrders() {
    return orderRepository.findAll();
}
```

### 롤백 설정
```java
// 특정 예외에서만 롤백
@Transactional(rollbackFor = Exception.class)

// 특정 예외에서 롤백 안함
@Transactional(noRollbackFor = CustomException.class)
```

### 전파 옵션 (Propagation)
```java
@Transactional(propagation = Propagation.REQUIRED)   // 기본값: 기존 트랜잭션 사용 or 새로 생성
@Transactional(propagation = Propagation.REQUIRES_NEW)  // 항상 새 트랜잭션
```

---

## 14. 테스트

### JUnit 기본
```java
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    @BeforeEach
    void setUp() {
        // 각 테스트 전 실행
    }

    @Test
    @DisplayName("1 + 1 = 2")
    void addTest() {
        Calculator calc = new Calculator();
        assertThat(calc.add(1, 1)).isEqualTo(2);
    }

    @Test
    void divideByZeroThrowsException() {
        Calculator calc = new Calculator();
        assertThatThrownBy(() -> calc.divide(1, 0))
            .isInstanceOf(ArithmeticException.class);
    }
}
```

### Service 테스트 (MockBean)
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void findById_success() {
        // given
        User user = new User(1L, "홍길동");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // when
        UserResponse result = userService.findById(1L);

        // then
        assertThat(result.getName()).isEqualTo("홍길동");
    }
}
```

### Controller 테스트 (MockMvc)
```java
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUser_success() throws Exception {
        when(userService.findById(1L))
            .thenReturn(new UserResponse(1L, "홍길동"));

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("홍길동"));
    }

    @Test
    void createUser_success() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"홍길동\",\"email\":\"hong@test.com\"}"))
            .andExpect(status().isOk());
    }
}
```

### Repository 테스트
```java
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_success() {
        // given
        userRepository.save(new User("홍길동", "hong@test.com"));

        // when
        Optional<User> result = userRepository.findByEmail("hong@test.com");

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("홍길동");
    }
}
```

---

## 15. 인증/보안 (Spring Security + JWT)

### 의존성 추가
```gradle
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
```

### JWT 토큰 생성/검증
```java
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private final long tokenValidTime = 1000L * 60 * 60 * 24;  // 24시간

    // 토큰 생성
    public String createToken(Long userId, String email) {
        Date now = new Date();
        return Jwts.builder()
            .setSubject(String.valueOf(userId))
            .claim("email", email)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenValidTime))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

    // 토큰에서 userId 추출
    public Long getUserId(String token) {
        return Long.parseLong(
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject()
        );
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
```

### Security 설정
```java
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()  // 로그인, 회원가입
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### JWT 필터
```java
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            Long userId = jwtTokenProvider.getUserId(token);
            // SecurityContext에 인증 정보 저장
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userId, null, List.of());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
```

### 로그인 API
```java
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest request) {
        authService.signup(request);
    }
}
```

---

## 16. 파일 처리

### 파일 업로드
```java
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public FileResponse upload(@RequestParam("file") MultipartFile file) {
        return fileService.upload(file);
    }

    @PostMapping("/uploads")
    public List<FileResponse> uploadMultiple(
            @RequestParam("files") List<MultipartFile> files) {
        return fileService.uploadMultiple(files);
    }
}
```

### 파일 저장 Service
```java
@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileResponse upload(MultipartFile file) {
        try {
            // 파일명 생성 (UUID + 원본 확장자)
            String originalName = file.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String savedName = UUID.randomUUID() + extension;

            // 저장 경로
            Path path = Paths.get(uploadDir, savedName);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path);

            return new FileResponse(savedName, "/files/" + savedName);
        } catch (IOException e) {
            throw new FileUploadException("파일 업로드 실패", e);
        }
    }
}
```

### 파일 조회 (정적 리소스)
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
            .addResourceLocations("file:" + uploadDir + "/");
    }
}
```

### application.yml 설정
```yaml
file:
  upload-dir: /uploads

spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
```

---

## 17. 푸시 알림 (FCM)

### 의존성 추가
```gradle
implementation 'com.google.firebase:firebase-admin:9.2.0'
```

### Firebase 초기화
```java
@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            FileInputStream serviceAccount =
                new FileInputStream("firebase-service-account.json");

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException("Firebase 초기화 실패", e);
        }
    }
}
```

### FCM 토큰 저장
```java
@Entity
public class FcmToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String token;
    private String deviceType;  // iOS, Android

    @CreatedDate
    private LocalDateTime createdAt;
}
```

### 푸시 발송 Service
```java
@Service
@RequiredArgsConstructor
public class FcmService {

    private final FcmTokenRepository fcmTokenRepository;

    // 단일 발송
    public void sendToUser(Long userId, String title, String body) {
        List<FcmToken> tokens = fcmTokenRepository.findByUserId(userId);

        for (FcmToken fcmToken : tokens) {
            Message message = Message.builder()
                .setToken(fcmToken.getToken())
                .setNotification(Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build())
                .build();

            try {
                FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                // 토큰 만료 시 삭제
                if (e.getMessagingErrorCode() == MessagingErrorCode.UNREGISTERED) {
                    fcmTokenRepository.delete(fcmToken);
                }
            }
        }
    }

    // 데이터 포함 발송
    public void sendWithData(Long userId, String title, String body,
            Map<String, String> data) {
        List<FcmToken> tokens = fcmTokenRepository.findByUserId(userId);

        for (FcmToken fcmToken : tokens) {
            Message message = Message.builder()
                .setToken(fcmToken.getToken())
                .setNotification(Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build())
                .putAllData(data)  // 커스텀 데이터
                .build();

            try {
                FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                log.error("FCM 발송 실패: {}", e.getMessage());
            }
        }
    }
}
```

### 토큰 등록 API
```java
@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FcmController {

    private final FcmTokenRepository fcmTokenRepository;

    @PostMapping("/token")
    public void registerToken(@RequestBody FcmTokenRequest request,
            @AuthenticationPrincipal Long userId) {
        // 기존 토큰 삭제 후 새로 저장
        fcmTokenRepository.deleteByUserIdAndDeviceType(userId, request.getDeviceType());
        fcmTokenRepository.save(new FcmToken(userId, request.getToken(), request.getDeviceType()));
    }
}
```

---

## 18. 캐싱 (Redis)

### 의존성 추가
```gradle
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
```

### Redis 설정
```java
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10))  // 기본 TTL 10분
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair
                    .fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair
                    .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(config)
            .build();
    }
}
```

### application.yml
```yaml
spring:
  redis:
    host: localhost
    port: 6379
```

### 캐시 어노테이션 사용
```java
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 캐시 조회 (없으면 DB 조회 후 캐시 저장)
    @Cacheable(value = "users", key = "#id")
    public UserResponse findById(Long id) {
        return userRepository.findById(id)
            .map(UserResponse::from)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    // 캐시 갱신
    @CachePut(value = "users", key = "#id")
    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
        user.update(request);
        return UserResponse.from(user);
    }

    // 캐시 삭제
    @CacheEvict(value = "users", key = "#id")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // 전체 캐시 삭제
    @CacheEvict(value = "users", allEntries = true)
    public void clearCache() {
    }
}
```

### RedisTemplate 직접 사용
```java
@Service
@RequiredArgsConstructor
public class SessionService {

    private final RedisTemplate<String, Object> redisTemplate;

    // 저장
    public void save(String key, Object value, long minutes) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(minutes));
    }

    // 조회
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 삭제
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // TTL 확인
    public Long getTtl(String key) {
        return redisTemplate.getExpire(key);
    }
}
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

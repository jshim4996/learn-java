/**
 * Chapter 06 - 기본 클래스 예제
 *
 * 클래스의 구성요소: 필드, 생성자, 메소드
 */
public class Person {
    // === 필드 (멤버 변수) ===
    private String name;
    private int age;
    private String email;

    // === 생성자 ===

    // 기본 생성자
    public Person() {
        this.name = "이름없음";
        this.age = 0;
    }

    // 매개변수 생성자
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 전체 매개변수 생성자
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // === Getter / Setter ===

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // === 일반 메소드 ===

    public void introduce() {
        System.out.println("안녕하세요, " + name + "입니다. " + age + "살입니다.");
    }

    public boolean isAdult() {
        return age >= 18;
    }

    // toString 오버라이드 (객체 출력용)
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

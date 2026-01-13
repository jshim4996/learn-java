/**
 * Chapter 06 - Person 클래스 사용 예제
 *
 * 실행: javac Person.java PersonMain.java && java PersonMain
 */
public class PersonMain {
    public static void main(String[] args) {
        // === 객체 생성 ===
        System.out.println("=== 객체 생성 ===");

        // 기본 생성자
        Person person1 = new Person();
        System.out.println("person1: " + person1);

        // 매개변수 생성자
        Person person2 = new Person("홍길동", 25);
        System.out.println("person2: " + person2);

        // 전체 매개변수 생성자
        Person person3 = new Person("김철수", 30, "kim@email.com");
        System.out.println("person3: " + person3);

        // === Getter 사용 ===
        System.out.println("\n=== Getter 사용 ===");
        System.out.println("이름: " + person2.getName());
        System.out.println("나이: " + person2.getAge());

        // === Setter 사용 ===
        System.out.println("\n=== Setter 사용 ===");
        person1.setName("이영희");
        person1.setAge(28);
        person1.setEmail("lee@email.com");
        System.out.println("수정된 person1: " + person1);

        // 유효성 검사 (음수 나이)
        person1.setAge(-5);  // 무시됨
        System.out.println("음수 나이 설정 시도 후: " + person1.getAge());

        // === 메소드 호출 ===
        System.out.println("\n=== 메소드 호출 ===");
        person2.introduce();
        System.out.println("성인 여부: " + person2.isAdult());

        // === 참조 비교 ===
        System.out.println("\n=== 참조 비교 ===");
        Person personA = new Person("테스트", 20);
        Person personB = new Person("테스트", 20);
        Person personC = personA;

        System.out.println("personA == personB: " + (personA == personB));  // false
        System.out.println("personA == personC: " + (personA == personC));  // true
    }
}

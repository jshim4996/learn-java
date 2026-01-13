/**
 * Chapter 07 - 부모 클래스 (추상 클래스)
 */
public abstract class Animal {
    protected String name;
    protected int age;

    // 생성자
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 추상 메소드 - 자식이 반드시 구현
    public abstract void speak();

    // 일반 메소드 - 상속됨
    public void sleep() {
        System.out.println(name + "이(가) 잠을 잡니다. Zzz...");
    }

    public void eat(String food) {
        System.out.println(name + "이(가) " + food + "을(를) 먹습니다.");
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // 정보 출력
    public void printInfo() {
        System.out.println("이름: " + name + ", 나이: " + age + "살");
    }
}

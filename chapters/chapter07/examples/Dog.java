/**
 * Chapter 07 - 자식 클래스 (Dog)
 */
public class Dog extends Animal {
    private String breed;

    // 생성자 - super()로 부모 생성자 호출
    public Dog(String name, int age, String breed) {
        super(name, age);  // 부모 생성자 호출 (첫 줄!)
        this.breed = breed;
    }

    // 추상 메소드 구현 (필수)
    @Override
    public void speak() {
        System.out.println(name + ": 멍멍! 왈왈!");
    }

    // Dog만의 메소드
    public void fetch() {
        System.out.println(name + "이(가) 공을 물어옵니다!");
    }

    public void wagTail() {
        System.out.println(name + "이(가) 꼬리를 흔듭니다~");
    }

    // Getter
    public String getBreed() {
        return breed;
    }

    // 오버라이드된 정보 출력
    @Override
    public void printInfo() {
        super.printInfo();  // 부모 메소드 호출
        System.out.println("견종: " + breed);
    }
}

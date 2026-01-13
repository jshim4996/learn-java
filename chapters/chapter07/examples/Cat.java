/**
 * Chapter 07 - 자식 클래스 (Cat)
 */
public class Cat extends Animal {
    private boolean isIndoor;

    // 생성자
    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }

    // 추상 메소드 구현
    @Override
    public void speak() {
        System.out.println(name + ": 야옹~ 냐옹~");
    }

    // Cat만의 메소드
    public void scratch() {
        System.out.println(name + "이(가) 스크래처를 긁습니다!");
    }

    public void purr() {
        System.out.println(name + "이(가) 그르릉 그르릉...");
    }

    // Getter
    public boolean isIndoor() {
        return isIndoor;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("실내 고양이: " + (isIndoor ? "예" : "아니오"));
    }
}

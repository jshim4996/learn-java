/**
 * Chapter 08 - 인터페이스 구현 클래스
 */
public class Car implements Movable {
    private String name;
    private int speed;

    public Car(String name) {
        this.name = name;
        this.speed = 0;
    }

    @Override
    public void move() {
        speed = 60;
        System.out.println(name + " 자동차가 이동합니다. 속도: " + speed + "km/h");
    }

    @Override
    public void stop() {
        speed = 0;
        System.out.println(name + " 자동차가 멈춥니다.");
    }

    // 디폴트 메소드 오버라이드
    @Override
    public void showSpeed() {
        System.out.println(name + " 현재 속도: " + speed + "km/h");
    }

    public void accelerate(int amount) {
        speed += amount;
        System.out.println(name + " 가속! 속도: " + speed + "km/h");
    }
}

/**
 * Chapter 08 - 다중 인터페이스 구현
 */
public class Bird implements Movable, Flyable {
    private String name;

    public Bird(String name) {
        this.name = name;
    }

    // Movable 구현
    @Override
    public void move() {
        System.out.println(name + " 새가 걸어갑니다.");
    }

    @Override
    public void stop() {
        System.out.println(name + " 새가 멈춥니다.");
    }

    // Flyable 구현
    @Override
    public void fly() {
        System.out.println(name + " 새가 날아갑니다! 파닥파닥~");
    }

    @Override
    public void land() {
        System.out.println(name + " 새가 착지합니다.");
    }

    public String getName() {
        return name;
    }
}

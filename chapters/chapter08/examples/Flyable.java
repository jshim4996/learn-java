/**
 * Chapter 08 - 인터페이스 예제
 */
public interface Flyable {
    void fly();
    void land();

    default void glide() {
        System.out.println("활공 중...");
    }
}

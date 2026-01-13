/**
 * Chapter 08 - 인터페이스 예제
 */
public interface Movable {
    // 추상 메소드 (구현 필수)
    void move();
    void stop();

    // 디폴트 메소드 (구현 선택)
    default void showSpeed() {
        System.out.println("속도 정보 없음");
    }

    // 정적 메소드
    static void printInfo() {
        System.out.println("Movable 인터페이스: 이동 가능한 객체");
    }
}

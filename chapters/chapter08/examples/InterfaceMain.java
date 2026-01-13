/**
 * Chapter 08 - 인터페이스 예제 실행
 *
 * 실행: javac Movable.java Flyable.java Car.java Bird.java InterfaceMain.java && java InterfaceMain
 */
public class InterfaceMain {
    public static void main(String[] args) {
        // === 인터페이스 구현 ===
        System.out.println("=== 단일 구현 ===");
        Car car = new Car("테슬라");
        car.move();
        car.showSpeed();
        car.accelerate(40);
        car.showSpeed();
        car.stop();

        // === 다중 구현 ===
        System.out.println("\n=== 다중 구현 ===");
        Bird bird = new Bird("참새");
        bird.move();      // Movable
        bird.fly();       // Flyable
        bird.glide();     // Flyable 디폴트 메소드
        bird.land();      // Flyable
        bird.stop();      // Movable

        // === 인터페이스 타입으로 참조 ===
        System.out.println("\n=== 인터페이스 타입 ===");
        Movable movable = new Car("BMW");
        movable.move();
        movable.stop();
        // movable.accelerate(50);  // 에러! Movable에 없는 메소드

        // === 다형성 ===
        System.out.println("\n=== 다형성 (Movable 배열) ===");
        Movable[] movables = {
            new Car("아우디"),
            new Bird("독수리"),
            new Car("벤츠")
        };

        for (Movable m : movables) {
            m.move();
            m.stop();
            System.out.println("---");
        }

        // === 정적 메소드 ===
        System.out.println("\n=== 정적 메소드 ===");
        Movable.printInfo();

        // === 매개변수로 인터페이스 ===
        System.out.println("\n=== 매개변수로 인터페이스 ===");
        operate(new Car("포르쉐"));
        operate(new Bird("비둘기"));

        // === 함수형 인터페이스 (람다) ===
        System.out.println("\n=== 함수형 인터페이스 ===");
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        Calculator subtract = (a, b) -> a - b;

        System.out.println("10 + 5 = " + add.calculate(10, 5));
        System.out.println("10 * 5 = " + multiply.calculate(10, 5));
        System.out.println("10 - 5 = " + subtract.calculate(10, 5));
    }

    // 인터페이스를 매개변수로
    static void operate(Movable m) {
        System.out.println("--- 조작 시작 ---");
        m.move();
        m.stop();
    }
}

// 함수형 인터페이스 (같은 파일에 정의)
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

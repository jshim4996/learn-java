/**
 * Chapter 06 - static과 final 예제
 *
 * 실행: javac StaticExample.java && java StaticExample
 */
public class StaticExample {

    // === 상수 (static final) ===
    public static final double PI = 3.14159;
    public static final String APP_NAME = "MyApp";

    // === 정적 필드 ===
    private static int instanceCount = 0;

    // === 인스턴스 필드 ===
    private String name;
    private int id;

    // === 생성자 ===
    public StaticExample(String name) {
        this.name = name;
        instanceCount++;
        this.id = instanceCount;
    }

    // === 정적 메소드 ===
    public static int getInstanceCount() {
        return instanceCount;
    }

    public static double circleArea(double radius) {
        return PI * radius * radius;
    }

    // === 인스턴스 메소드 ===
    public void printInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }

    // === main ===
    public static void main(String[] args) {
        // === 상수 사용 ===
        System.out.println("=== 상수 ===");
        System.out.println("PI: " + StaticExample.PI);
        System.out.println("APP_NAME: " + StaticExample.APP_NAME);

        // === 정적 메소드 (객체 없이 호출) ===
        System.out.println("\n=== 정적 메소드 ===");
        double area = StaticExample.circleArea(5.0);
        System.out.println("반지름 5인 원의 넓이: " + area);

        // === 인스턴스 생성 ===
        System.out.println("\n=== 인스턴스 생성 ===");
        System.out.println("현재 인스턴스 수: " + StaticExample.getInstanceCount());

        StaticExample obj1 = new StaticExample("첫번째");
        System.out.println("obj1 생성 후: " + StaticExample.getInstanceCount());
        obj1.printInfo();

        StaticExample obj2 = new StaticExample("두번째");
        System.out.println("obj2 생성 후: " + StaticExample.getInstanceCount());
        obj2.printInfo();

        StaticExample obj3 = new StaticExample("세번째");
        System.out.println("obj3 생성 후: " + StaticExample.getInstanceCount());
        obj3.printInfo();

        // === static 필드는 공유됨 ===
        System.out.println("\n=== 모든 인스턴스가 static 공유 ===");
        System.out.println("obj1에서 조회: " + StaticExample.getInstanceCount());
        System.out.println("obj2에서 조회: " + StaticExample.getInstanceCount());
        System.out.println("obj3에서 조회: " + StaticExample.getInstanceCount());
        // 모두 같은 값 (3)
    }
}

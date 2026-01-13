/**
 * Chapter 06 - 메소드 예제
 *
 * 실행: javac Calculator.java && java Calculator
 */
public class Calculator {

    // === 기본 메소드 ===
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다");
        }
        return (double) a / b;
    }

    // === 메소드 오버로딩 ===
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }

    // === 가변 인자 ===
    public int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // === void 메소드 ===
    public void printResult(String operation, int result) {
        System.out.println(operation + " = " + result);
    }

    // === main 메소드 ===
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // 기본 메소드
        System.out.println("=== 기본 연산 ===");
        System.out.println("10 + 20 = " + calc.add(10, 20));
        System.out.println("30 - 15 = " + calc.subtract(30, 15));
        System.out.println("5 * 6 = " + calc.multiply(5, 6));
        System.out.println("10 / 3 = " + calc.divide(10, 3));

        // 오버로딩
        System.out.println("\n=== 오버로딩 ===");
        System.out.println("add(1, 2): " + calc.add(1, 2));
        System.out.println("add(1, 2, 3): " + calc.add(1, 2, 3));
        System.out.println("add(1.5, 2.5): " + calc.add(1.5, 2.5));

        // 가변 인자
        System.out.println("\n=== 가변 인자 ===");
        System.out.println("sum(1, 2, 3): " + calc.sum(1, 2, 3));
        System.out.println("sum(1, 2, 3, 4, 5): " + calc.sum(1, 2, 3, 4, 5));

        // void 메소드
        System.out.println("\n=== void 메소드 ===");
        calc.printResult("100 + 200", calc.add(100, 200));
    }
}

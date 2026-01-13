/**
 * Chapter 04 - 반복문
 *
 * 실행: javac LoopExample.java && java LoopExample
 */
public class LoopExample {
    public static void main(String[] args) {
        // === 기본 for ===
        System.out.println("=== 기본 for ===");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // === 향상된 for (for-each) ===
        System.out.println("\n=== 향상된 for ===");
        int[] numbers = {10, 20, 30, 40, 50};
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        String[] fruits = {"사과", "바나나", "오렌지"};
        for (String fruit : fruits) {
            System.out.println("과일: " + fruit);
        }

        // === while ===
        System.out.println("\n=== while ===");
        int count = 0;
        while (count < 3) {
            System.out.println("count: " + count);
            count++;
        }

        // === do-while ===
        System.out.println("\n=== do-while ===");
        int num = 0;
        do {
            System.out.println("num: " + num);
            num++;
        } while (num < 3);

        // === break ===
        System.out.println("\n=== break ===");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println("(5에서 종료)");

        // === continue ===
        System.out.println("\n=== continue ===");
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println("(2 건너뜀)");

        // === 중첩 반복문 + 라벨 break ===
        System.out.println("\n=== 라벨 break ===");
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    System.out.println("(1,1)에서 전체 종료");
                    break outer;
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
    }
}

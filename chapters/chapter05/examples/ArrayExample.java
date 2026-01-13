/**
 * Chapter 05 - 배열 기본
 *
 * 실행: javac ArrayExample.java && java ArrayExample
 */
public class ArrayExample {
    public static void main(String[] args) {
        // === 배열 생성 ===
        System.out.println("=== 배열 생성 ===");
        int[] numbers = new int[5];           // 기본값 0
        int[] scores = {90, 85, 77, 92, 88};  // 초기화

        System.out.println("numbers[0]: " + numbers[0]);  // 0
        System.out.println("scores[0]: " + scores[0]);    // 90

        // === 배열 수정 ===
        System.out.println("\n=== 배열 수정 ===");
        numbers[0] = 100;
        System.out.println("numbers[0]: " + numbers[0]);

        // === 배열 길이 ===
        System.out.println("\n=== 배열 길이 ===");
        System.out.println("scores.length: " + scores.length);

        // === 배열 순회 (for) ===
        System.out.println("\n=== for문 순회 ===");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("scores[" + i + "] = " + scores[i]);
        }

        // === 배열 순회 (for-each) ===
        System.out.println("\n=== for-each 순회 ===");
        for (int score : scores) {
            System.out.println("점수: " + score);
        }

        // === 참조 복사 vs 값 복사 ===
        System.out.println("\n=== 참조 복사 ===");
        int[] arr1 = {1, 2, 3};
        int[] arr2 = arr1;       // 참조 복사
        arr2[0] = 100;
        System.out.println("arr1[0]: " + arr1[0]);  // 100 (같이 변경됨!)

        // 값 복사하려면
        int[] arr3 = arr1.clone();
        arr3[0] = 999;
        System.out.println("arr1[0]: " + arr1[0]);  // 100 (변경 안됨)
        System.out.println("arr3[0]: " + arr3[0]);  // 999

        // === 2차원 배열 ===
        System.out.println("\n=== 2차원 배열 ===");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("matrix[1][2]: " + matrix[1][2]);  // 6
        System.out.println("행 개수: " + matrix.length);       // 3
        System.out.println("열 개수: " + matrix[0].length);    // 3

        // 2차원 배열 순회
        System.out.println("\n2차원 배열 출력:");
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

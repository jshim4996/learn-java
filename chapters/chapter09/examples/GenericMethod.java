// 제네릭 메소드 예제
public class GenericMethod {

    // 제네릭 메소드: 리턴 타입 앞에 <T> 선언
    public static <T> T getFirst(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    // 두 값을 비교하는 제네릭 메소드
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    // 배열 출력 제네릭 메소드
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 문자열 배열
        String[] names = {"Alice", "Bob", "Charlie"};
        String firstName = getFirst(names);  // 타입 추론
        System.out.println("First name: " + firstName);

        // 정수 배열
        Integer[] numbers = {10, 20, 30};
        Integer firstNum = getFirst(numbers);
        System.out.println("First number: " + firstNum);

        // max 메소드
        System.out.println("Max of 10, 20: " + max(10, 20));
        System.out.println("Max of A, B: " + max("A", "B"));

        // 배열 출력
        System.out.print("Names: ");
        printArray(names);
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 와일드카드 예제
public class WildcardExample {

    // <?> 비제한 와일드카드 - 모든 타입 허용 (읽기 전용)
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // <? extends Number> 상한 제한 - Number 또는 하위 타입 (읽기용)
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    // <? super Integer> 하한 제한 - Integer 또는 상위 타입 (쓰기용)
    public static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public static void main(String[] args) {
        // 비제한 와일드카드
        List<String> strings = Arrays.asList("A", "B", "C");
        List<Integer> integers = Arrays.asList(1, 2, 3);

        System.out.print("Strings: ");
        printList(strings);
        System.out.print("Integers: ");
        printList(integers);

        // 상한 제한 와일드카드
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);

        System.out.println("Sum of integers: " + sumOfList(intList));
        System.out.println("Sum of doubles: " + sumOfList(doubleList));

        // 하한 제한 와일드카드
        List<Number> numbers = new ArrayList<>();
        addNumbers(numbers);
        System.out.print("Added numbers: ");
        printList(numbers);

        // PECS 원칙: Producer Extends, Consumer Super
        // - 데이터를 읽기만 할 때 → <? extends T>
        // - 데이터를 쓰기만 할 때 → <? super T>
    }
}

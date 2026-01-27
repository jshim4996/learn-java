// 제네릭 클래스 기본 예제
public class GenericBox<T> {
    private T content;

    public void set(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }

    public static void main(String[] args) {
        // String 타입 박스
        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.set("Hello Generic!");
        String str = stringBox.get();  // 캐스팅 불필요
        System.out.println("String Box: " + str);

        // Integer 타입 박스
        GenericBox<Integer> intBox = new GenericBox<>();
        intBox.set(100);
        int num = intBox.get();
        System.out.println("Integer Box: " + num);

        // 제네릭 없이 사용하면? (Raw Type - 권장하지 않음)
        // GenericBox rawBox = new GenericBox();
        // rawBox.set("문자열");
        // rawBox.set(123);  // 타입 체크 안됨 - 위험!
    }
}

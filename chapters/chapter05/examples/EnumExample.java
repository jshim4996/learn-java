/**
 * Chapter 05 - 열거형 (Enum)
 *
 * 실행: javac EnumExample.java && java EnumExample
 */
public class EnumExample {
    // 열거형 정의
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // 값이 있는 열거형
    enum Status {
        PENDING("대기중"),
        APPROVED("승인됨"),
        REJECTED("거부됨");

        private final String korean;

        Status(String korean) {
            this.korean = korean;
        }

        public String getKorean() {
            return korean;
        }
    }

    public static void main(String[] args) {
        // === 기본 사용 ===
        System.out.println("=== 기본 사용 ===");
        Day today = Day.WEDNESDAY;
        System.out.println("오늘: " + today);

        // === 비교 ===
        System.out.println("\n=== 비교 ===");
        if (today == Day.WEDNESDAY) {
            System.out.println("수요일입니다!");
        }

        // === switch와 함께 ===
        System.out.println("\n=== switch ===");
        String type = switch (today) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "평일";
            case SATURDAY, SUNDAY -> "주말";
        };
        System.out.println("오늘은 " + type);

        // === 열거형 메소드 ===
        System.out.println("\n=== 열거형 메소드 ===");
        System.out.println("name(): " + today.name());
        System.out.println("ordinal(): " + today.ordinal());  // 인덱스 (0부터)

        // === 모든 값 순회 ===
        System.out.println("\n=== 모든 요일 ===");
        for (Day day : Day.values()) {
            System.out.println(day.ordinal() + ": " + day);
        }

        // === 문자열 → 열거형 ===
        System.out.println("\n=== 문자열 → 열거형 ===");
        Day friday = Day.valueOf("FRIDAY");
        System.out.println("valueOf('FRIDAY'): " + friday);

        // === 값이 있는 열거형 ===
        System.out.println("\n=== 값이 있는 열거형 ===");
        Status status = Status.PENDING;
        System.out.println("상태: " + status);
        System.out.println("한글: " + status.getKorean());

        for (Status s : Status.values()) {
            System.out.println(s.name() + " = " + s.getKorean());
        }
    }
}

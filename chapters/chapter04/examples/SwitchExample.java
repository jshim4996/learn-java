/**
 * Chapter 04 - switch 문
 *
 * 실행: javac SwitchExample.java && java SwitchExample
 */
public class SwitchExample {
    public static void main(String[] args) {
        int day = 3;

        // === 전통적인 switch ===
        System.out.println("=== 전통적인 switch ===");
        switch (day) {
            case 1:
                System.out.println("월요일");
                break;
            case 2:
                System.out.println("화요일");
                break;
            case 3:
                System.out.println("수요일");
                break;
            default:
                System.out.println("기타");
        }

        // === Java 14+ switch 표현식 (권장) ===
        System.out.println("\n=== switch 표현식 (Java 14+) ===");
        String dayName = switch (day) {
            case 1 -> "월요일";
            case 2 -> "화요일";
            case 3 -> "수요일";
            case 4 -> "목요일";
            case 5 -> "금요일";
            case 6, 7 -> "주말";
            default -> "잘못된 값";
        };
        System.out.println("오늘은 " + dayName);

        // === 문자열 switch ===
        System.out.println("\n=== 문자열 switch ===");
        String command = "start";

        String result = switch (command) {
            case "start" -> "시작합니다";
            case "stop" -> "중지합니다";
            case "pause" -> "일시정지";
            default -> "알 수 없는 명령";
        };
        System.out.println(result);

        // === 블록이 필요한 경우 yield 사용 ===
        System.out.println("\n=== yield 사용 ===");
        int score = 85;
        String grade = switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> {
                System.out.println("재시험 필요");
                yield "F";  // 블록에서 값 반환
            }
        };
        System.out.println("학점: " + grade);
    }
}

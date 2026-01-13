/**
 * Chapter 02 - 타입 변환
 *
 * 실행: javac TypeConversion.java && java TypeConversion
 */
public class TypeConversion {
    public static void main(String[] args) {
        // === 자동 타입 변환 (Promotion) ===
        System.out.println("=== 자동 타입 변환 ===");
        int intVal = 100;
        long longVal = intVal;        // int → long (자동)
        double doubleVal = longVal;   // long → double (자동)
        System.out.println("int → long → double: " + doubleVal);

        // === 강제 타입 변환 (Casting) ===
        System.out.println("\n=== 강제 타입 변환 ===");
        double pi = 3.14159;
        int intPi = (int) pi;         // 소수점 버림
        System.out.println("double → int: " + pi + " → " + intPi);

        long big = 1000L;
        int small = (int) big;        // long → int (명시적)
        System.out.println("long → int: " + big + " → " + small);

        // === 문자열 → 숫자 ===
        System.out.println("\n=== 문자열 → 숫자 ===");
        String numStr = "123";
        int parsed = Integer.parseInt(numStr);
        System.out.println("parseInt: \"123\" → " + parsed);

        String doubleStr = "3.14";
        double parsedDouble = Double.parseDouble(doubleStr);
        System.out.println("parseDouble: \"3.14\" → " + parsedDouble);

        // === 숫자 → 문자열 ===
        System.out.println("\n=== 숫자 → 문자열 ===");
        int num = 456;
        String str1 = String.valueOf(num);
        String str2 = Integer.toString(num);
        String str3 = "" + num;       // 간단하지만 비효율
        System.out.println("valueOf: " + str1);
        System.out.println("toString: " + str2);
        System.out.println("연결: " + str3);
    }
}

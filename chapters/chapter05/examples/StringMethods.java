/**
 * Chapter 05 - 문자열 메소드
 *
 * 실행: javac StringMethods.java && java StringMethods
 */
public class StringMethods {
    public static void main(String[] args) {
        String text = "  Hello, Java World!  ";

        // === 기본 메소드 ===
        System.out.println("=== 기본 메소드 ===");
        System.out.println("원본: [" + text + "]");
        System.out.println("length(): " + text.length());
        System.out.println("trim(): [" + text.trim() + "]");
        System.out.println("toUpperCase(): " + text.toUpperCase());
        System.out.println("toLowerCase(): " + text.toLowerCase());

        // === 검색 메소드 ===
        System.out.println("\n=== 검색 메소드 ===");
        String str = "Hello, Java World!";
        System.out.println("charAt(0): " + str.charAt(0));
        System.out.println("indexOf('Java'): " + str.indexOf("Java"));
        System.out.println("lastIndexOf('o'): " + str.lastIndexOf("o"));
        System.out.println("contains('Java'): " + str.contains("Java"));
        System.out.println("startsWith('Hello'): " + str.startsWith("Hello"));
        System.out.println("endsWith('!'): " + str.endsWith("!"));

        // === 추출 메소드 ===
        System.out.println("\n=== 추출 메소드 ===");
        System.out.println("substring(0, 5): " + str.substring(0, 5));
        System.out.println("substring(7): " + str.substring(7));

        // === 변환 메소드 ===
        System.out.println("\n=== 변환 메소드 ===");
        System.out.println("replace('Java', 'Python'): " + str.replace("Java", "Python"));
        System.out.println("replaceAll 정규식: " + str.replaceAll("[aeiou]", "*"));

        // === 분리 메소드 ===
        System.out.println("\n=== 분리 메소드 ===");
        String csv = "apple,banana,orange";
        String[] fruits = csv.split(",");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }

        // === 비교 메소드 ===
        System.out.println("\n=== 비교 메소드 ===");
        String a = "hello";
        String b = "Hello";
        System.out.println("equals(): " + a.equals(b));                    // false
        System.out.println("equalsIgnoreCase(): " + a.equalsIgnoreCase(b)); // true
        System.out.println("compareTo(): " + a.compareTo(b));              // 양수 (사전순 뒤)

        // === 검사 메소드 ===
        System.out.println("\n=== 검사 메소드 ===");
        System.out.println("isEmpty(''): " + "".isEmpty());
        System.out.println("isBlank('   '): " + "   ".isBlank());  // Java 11+

        // === 조인 (Java 8+) ===
        System.out.println("\n=== String.join ===");
        String joined = String.join(", ", "a", "b", "c");
        System.out.println("join: " + joined);

        String[] words = {"Java", "is", "fun"};
        String sentence = String.join(" ", words);
        System.out.println("join array: " + sentence);
    }
}

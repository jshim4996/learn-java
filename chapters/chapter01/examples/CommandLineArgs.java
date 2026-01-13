/**
 * Chapter 01 - 커맨드라인 인자 다루기
 *
 * 실행 방법:
 * javac CommandLineArgs.java
 * java CommandLineArgs hello world
 *
 * Node.js 비교:
 * - Node: process.argv[0] = node경로, [1] = 파일경로, [2]부터 인자
 * - Java: args[0]부터 바로 인자
 */
public class CommandLineArgs {
    public static void main(String[] args) {
        System.out.println("전달된 인자 개수: " + args.length);

        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
    }
}

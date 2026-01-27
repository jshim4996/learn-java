// Runnable 인터페이스 구현 방식 (권장)
public class RunnableExample {

    public static void main(String[] args) {
        // 방법 1: Runnable 구현 클래스
        Runnable task1 = new MyRunnable("작업1");
        Thread thread1 = new Thread(task1);

        // 방법 2: 익명 클래스
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    System.out.println("작업2 - " + i);
                    sleep(300);
                }
            }
        });

        // 방법 3: 람다 표현식 (가장 간단, 권장)
        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("작업3 - " + i);
                sleep(300);
            }
        });

        // 스레드 시작
        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("모든 스레드 시작됨");
    }

    // 유틸 메소드
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Runnable 구현 클래스
class MyRunnable implements Runnable {
    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(name + " - " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

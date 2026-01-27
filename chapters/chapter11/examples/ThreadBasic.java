// Thread 클래스 상속 방식
public class ThreadBasic extends Thread {

    private String taskName;

    public ThreadBasic(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        // 스레드가 실행할 코드
        for (int i = 1; i <= 5; i++) {
            System.out.println(taskName + " - " + i + " (Thread: " + getName() + ")");
            try {
                Thread.sleep(500);  // 0.5초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("메인 스레드 시작: " + Thread.currentThread().getName());

        // 스레드 생성
        ThreadBasic thread1 = new ThreadBasic("작업A");
        ThreadBasic thread2 = new ThreadBasic("작업B");

        // 스레드 이름 설정
        thread1.setName("Thread-A");
        thread2.setName("Thread-B");

        // 스레드 시작 - run()이 아닌 start() 호출!
        thread1.start();
        thread2.start();

        System.out.println("메인 스레드 종료");
        // 메인 스레드가 종료되어도 다른 스레드는 계속 실행됨
    }
}

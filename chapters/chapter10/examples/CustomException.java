/**
 * Chapter 11 - 사용자 정의 예외
 *
 * 실행: javac CustomException.java && java CustomException
 */

// 사용자 정의 예외 클래스
class InvalidUserException extends RuntimeException {
    private String userId;

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, String userId) {
        super(message);
        this.userId = userId;
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getUserId() {
        return userId;
    }
}

// 또 다른 사용자 정의 예외
class InsufficientBalanceException extends RuntimeException {
    private int balance;
    private int amount;

    public InsufficientBalanceException(int balance, int amount) {
        super("잔액 부족: 잔액=" + balance + ", 요청=" + amount);
        this.balance = balance;
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public int getAmount() {
        return amount;
    }
}

// 간단한 User 클래스
class User {
    private String id;
    private String name;
    private int balance;

    public User(String id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getBalance() { return balance; }

    public void withdraw(int amount) {
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }
        balance -= amount;
    }
}

// 메인 클래스
public class CustomException {
    public static void main(String[] args) {
        // === 사용자 정의 예외 사용 ===
        System.out.println("=== InvalidUserException ===");
        try {
            validateUser(null);
        } catch (InvalidUserException e) {
            System.out.println("예외: " + e.getMessage());
        }

        try {
            validateUser(new User("", "이름없음", 0));
        } catch (InvalidUserException e) {
            System.out.println("예외: " + e.getMessage());
            System.out.println("문제 사용자 ID: " + e.getUserId());
        }

        // === 잔액 부족 예외 ===
        System.out.println("\n=== InsufficientBalanceException ===");
        User user = new User("user1", "홍길동", 10000);
        System.out.println("현재 잔액: " + user.getBalance());

        try {
            user.withdraw(5000);
            System.out.println("5000원 출금 성공. 잔액: " + user.getBalance());

            user.withdraw(10000);  // 잔액 부족!
        } catch (InsufficientBalanceException e) {
            System.out.println("출금 실패: " + e.getMessage());
            System.out.println("현재 잔액: " + e.getBalance());
            System.out.println("요청 금액: " + e.getAmount());
        }

        System.out.println("\n=== 프로그램 정상 종료 ===");
    }

    static void validateUser(User user) {
        if (user == null) {
            throw new InvalidUserException("사용자가 null입니다");
        }
        if (user.getId() == null || user.getId().isEmpty()) {
            throw new InvalidUserException("사용자 ID가 없습니다", user.getId());
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new InvalidUserException("사용자 이름이 없습니다", user.getId());
        }
    }
}

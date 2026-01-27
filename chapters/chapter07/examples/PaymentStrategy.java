/**
 * 다형성 실무 예제 - 전략 패턴 (Strategy Pattern)
 *
 * 결제 방식을 다형성으로 처리하는 실무적인 예제
 */

// 결제 전략 추상 클래스
abstract class PaymentStrategy {
    protected String name;

    public PaymentStrategy(String name) {
        this.name = name;
    }

    // 추상 메소드 - 각 결제 방식이 구현
    public abstract void pay(int amount);

    // 공통 메소드
    public void printReceipt(int amount) {
        System.out.println("결제 완료: " + amount + "원 (" + name + ")");
    }
}

// 카드 결제
class CardPayment extends PaymentStrategy {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        super("신용카드");
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("카드번호 " + maskCardNumber() + "로 " + amount + "원 결제");
        printReceipt(amount);
    }

    private String maskCardNumber() {
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}

// 계좌이체
class BankTransfer extends PaymentStrategy {
    private String bankName;
    private String accountNumber;

    public BankTransfer(String bankName, String accountNumber) {
        super("계좌이체");
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println(bankName + " " + accountNumber + "에서 " + amount + "원 이체");
        printReceipt(amount);
    }
}

// 카카오페이
class KakaoPay extends PaymentStrategy {
    private String phoneNumber;

    public KakaoPay(String phoneNumber) {
        super("카카오페이");
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("카카오페이 (" + phoneNumber + ")로 " + amount + "원 결제");
        printReceipt(amount);
    }
}

// 주문 처리 클래스 - 다형성 활용
class OrderProcessor {

    // PaymentStrategy 타입으로 모든 결제 방식 처리 (다형성)
    public void processPayment(PaymentStrategy payment, int amount) {
        System.out.println("=== 결제 처리 시작 ===");
        payment.pay(amount);
        System.out.println("=== 결제 처리 완료 ===\n");
    }
}

// 메인 클래스
public class PaymentStrategy {
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();

        // 다양한 결제 방식을 동일한 방식으로 처리
        PaymentStrategy card = new CardPayment("1234-5678-9012-3456");
        PaymentStrategy bank = new BankTransfer("국민은행", "123-456-789");
        PaymentStrategy kakao = new KakaoPay("010-1234-5678");

        processor.processPayment(card, 50000);
        processor.processPayment(bank, 30000);
        processor.processPayment(kakao, 15000);

        // 배열로 다형성 활용
        System.out.println("=== 배열로 다형성 활용 ===");
        PaymentStrategy[] payments = {card, bank, kakao};
        int[] amounts = {10000, 20000, 30000};

        for (int i = 0; i < payments.length; i++) {
            payments[i].pay(amounts[i]);
        }
    }
}

/**
 * Chapter 07 - 상속 예제 실행
 *
 * 실행: javac Animal.java Dog.java Cat.java InheritanceMain.java && java InheritanceMain
 */
public class InheritanceMain {
    public static void main(String[] args) {
        // === 객체 생성 ===
        System.out.println("=== 객체 생성 ===");
        Dog dog = new Dog("바둑이", 3, "진돗개");
        Cat cat = new Cat("나비", 2, true);

        // === 상속받은 메소드 ===
        System.out.println("\n=== 상속받은 메소드 ===");
        dog.sleep();
        dog.eat("사료");
        cat.sleep();
        cat.eat("참치");

        // === 오버라이드된 메소드 ===
        System.out.println("\n=== 오버라이드된 메소드 ===");
        dog.speak();
        cat.speak();

        // === 각 클래스 고유 메소드 ===
        System.out.println("\n=== 고유 메소드 ===");
        dog.fetch();
        dog.wagTail();
        cat.scratch();
        cat.purr();

        // === 정보 출력 ===
        System.out.println("\n=== 정보 출력 ===");
        dog.printInfo();
        System.out.println();
        cat.printInfo();

        // === 다형성 (Polymorphism) ===
        System.out.println("\n=== 다형성 ===");
        Animal animal1 = new Dog("멍멍이", 5, "푸들");
        Animal animal2 = new Cat("야옹이", 1, false);

        // 부모 타입으로 선언했지만 실제 객체의 메소드 호출
        animal1.speak();  // 멍멍!
        animal2.speak();  // 야옹~

        // === 배열에서 다형성 ===
        System.out.println("\n=== 배열에서 다형성 ===");
        Animal[] animals = {
            new Dog("강아지1", 2, "비글"),
            new Cat("고양이1", 3, true),
            new Dog("강아지2", 4, "시바"),
            new Cat("고양이2", 1, false)
        };

        for (Animal animal : animals) {
            animal.speak();
        }

        // === instanceof와 다운캐스팅 ===
        System.out.println("\n=== instanceof ===");
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                Dog d = (Dog) animal;
                d.fetch();
            } else if (animal instanceof Cat) {
                Cat c = (Cat) animal;
                c.scratch();
            }
        }

        // Java 16+ 패턴 매칭
        System.out.println("\n=== 패턴 매칭 (Java 16+) ===");
        for (Animal animal : animals) {
            if (animal instanceof Dog d) {
                System.out.println(d.getName() + " - " + d.getBreed());
            } else if (animal instanceof Cat c) {
                System.out.println(c.getName() + " - 실내: " + c.isIndoor());
            }
        }
    }
}

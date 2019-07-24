package exercises.initialization;

public class E05_Dog {
    void bark(int i) {
        System.out.println("Int barking");
    }

    void bark(long l) {
        System.out.println("Long barking");
    }

    void bark(float f) {
        System.out.println("Float barking");
    }

    public static void main(String[] args) {
        E05_Dog ref = new E05_Dog();
        ref.bark(0);
        ref.bark(0l);
        ref.bark(0f);
    }
}

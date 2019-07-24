package exercises.initialization;

public class E06_Dog {
    void bark(int i, long l) {
        System.out.println("Int and long barking");
    }

    void bark(long l, int i) {
        System.out.println("Long and int barking");
    }

    public static void main(String[] args) {
        E06_Dog ref = new E06_Dog();
        ref.bark(0, 0l);
        ref.bark(0l, 0);
    }
}

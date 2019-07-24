package exercises.initialization;

public class E09 {
    public E09() {
        System.out.println("First constructor");
    }

    public E09(String s) {
        this();
        System.out.println(s);
    }

    public static void main(String[] args) {
        new E09("creating E09");
    }
}

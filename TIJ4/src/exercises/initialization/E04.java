package exercises.initialization;

public class E04 {
    public E04() {
        System.out.println("Inside E04()");
    }

    public E04(String s) {
        System.out.println("Inside E04(), and " + s);
    }

    public static void main(String[] args) {
        new E04("creating an object");
    }
}

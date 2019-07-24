package exercises.initialization;

public class E01 {
    String uninitialized;

    public static void main(String[] args) {
        E01 ref = new E01();
        System.out.println(ref.uninitialized);
    }
}

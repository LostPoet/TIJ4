package exercises.initialization;

public class E02 {
    String initString1 = "abcdefg";
    String initString2;

    public E02() {
        initString2 = "hijklmn";
    }

    public static void main(String[] args) {
        E02 ref = new E02();
        System.out.println(ref.initString1);
        System.out.println(ref.initString2);
    }
}

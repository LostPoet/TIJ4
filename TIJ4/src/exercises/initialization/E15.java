package exercises.initialization;

public class E15 {
    String str;
    {
        str = "str";
    }

    public static void main(String[] args) {
        System.out.println(new E15().str);
    }
}

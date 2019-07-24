package exercises.object;

public class E06 {
    public static void main(String[] args) {
        String s = "test";
        E06 obj = new E06();
        System.out.println(obj.storage(s));
    }

    int storage(String s) {
        return s.length() * 2;
    }
}

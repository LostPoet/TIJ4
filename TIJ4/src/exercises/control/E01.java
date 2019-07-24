package exercises.control;

public class E01 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            if (i % 10 == 9)
                System.out.println(i + 1);
            else
                System.out.print((i + 1) + " ");
        }
    }
}

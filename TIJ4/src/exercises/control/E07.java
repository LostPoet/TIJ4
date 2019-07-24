package exercises.control;

public class E07 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            if (i == 98)
                return;
            System.out.print((i + 1) + " ");
        }

    }
}

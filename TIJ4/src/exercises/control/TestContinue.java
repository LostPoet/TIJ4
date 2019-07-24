package exercises.control;

public class TestContinue {
    public static void main(String[] args) {
        outer: for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (i == 1 && j == 2)
                    continue outer;
                // 'break' has the same effect here:
                // break;
                System.out.println("Current location: (" + i + ", " + j + ")");
            }
        }
    }
}

package exercises.control;

import static net.mindview.util.Print.print;

public class E06 {
    static boolean test(int testval, int begin, int end) {
        if (testval >= begin && testval <= end)
            return true;
        return false;
    }

    public static void main(String[] args) {
        print(test(5, 1, 10));
        print(test(5, 10, 100));
    }
}

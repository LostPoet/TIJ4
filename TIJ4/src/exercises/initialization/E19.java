package exercises.initialization;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

public class E19 {
    static void str1(String... list) {
        for (String s : list) {
            printnb(s + " ");
        }
        print();
    }

    public static void main(String[] args) {
        str1("1", "2", "3");
        str1(new String[] { "4", "5", "6" });
    }
}

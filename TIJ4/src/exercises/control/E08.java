package exercises.control;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;
import static net.mindview.util.Range.range;

public class E08 {
    public static void main(String[] args) {
        for (int i : range(1, 5)) {
            switch (i) {
            case 1:
                print("Integer: " + 1);
                break;
            case 2:
                print("Integer: " + 2);
                break;
            case 3:
                print("Integer: " + 3);
                break;
            case 4:
                print("Integer: " + 4);// break;
            default:
                printnb("Default");
            }
        }
    }
}

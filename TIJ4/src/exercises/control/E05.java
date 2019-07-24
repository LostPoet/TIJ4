package exercises.control;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

public class E05 {
    static void showBinary(int a) {
        int cursor = a;
        int index = 0;
        while (cursor != 0) {
            index++;
            cursor >>>= 1;
        }
        for (int i = index; i > 0; --i)
            printnb((((a >>> (i - 1)) & 0x01) == 0x01) ? 1 : 0);
        print();
    }

    public static void main(String[] args) {
        int val1 = 0x72;
        int val2 = 0x31;
        showBinary(val1);
        showBinary(val2);
    }
}

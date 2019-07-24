package exercises.control;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

public class E09 {
	static void fibonacci(int num) {
		switch (num) {
		case 0:
			break;
		case 1:
			print(1);
			break;
		case 2:
			print("1, 1");
			break;
		default:
			if (num < 0)
				break;
			printnb("1, 1, ");
			int a = 1, b = 1, temp = 0;
			for (int i = 0; i < num - 2; ++i) {
				if (i == num - 3)
					print(a + b);
				else
					printnb((a + b) + ", ");
				temp = a + b;
				a = b;
				b = temp;
			}
		}
	}

	public static void main(String[] args) {
		fibonacci(2);
	}
}

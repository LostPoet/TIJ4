package exercises.initialization;

import java.util.Random;

public class E17 {
	public E17(String string) {
		System.out.println(string);
	}

	public static void main(String[] args) {
		Random random = new Random(47);
		@SuppressWarnings("unused")
		E17[] e17s = new E17[random.nextInt(20)];
	}
}

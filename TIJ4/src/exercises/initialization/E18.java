package exercises.initialization;

import java.util.Random;

public class E18 {
	public E18(String string) {
		System.out.println(string);
	}

	public static void main(String[] args) {
		Random random = new Random(47);
		E18[] e18s = new E18[random.nextInt(20)];
		for (@SuppressWarnings("unused")
		E18 e18 : e18s) {
			e18 = new E18("e18");
		}
	}
}

package exercises.reusing;

class Base {
	public Base() {
		System.out.println("Base()");
	}
}

public class E04 extends Base {
	public E04() {
		System.out.println("E04()");
	}

	E04(String s) {
		System.out.println("E04 (String s) " + s);
	}

	public static void main(String[] args) {
		new E04();
		new E04("whatever");
	}

}

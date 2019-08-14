package exercises.reusing;

class E07_A {
	public E07_A(int i) {
		System.out.println("A");
	}
}

class E07_B {
	public E07_B(int i) {
		System.out.println("B");
	}
}

public class E07_C extends E07_A {
	@SuppressWarnings("unused")
	private E07_B b = new E07_B(1);

	public E07_C() {
		super(2);
	}

	public static void main(String[] args) {
		new E07_C();
	}
}

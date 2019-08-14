package exercises.reusing;

class A {
	public A() {
		System.out.println("A");
	}
}

class B {
	public B() {
		System.out.println("B");
	}
}

public class E05_C extends A {
	@SuppressWarnings("unused")
	private B b = new B();
	static {
		System.out.println("static");
	}

	public static void main(String[] args) {
		new E05_C();
	}
}

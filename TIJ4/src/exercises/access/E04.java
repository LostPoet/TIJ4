package exercises.access;

import exercises.access.mypackage.*;

class E04_Protected2 {
	protected void protectedMethod2() {
		System.out.println("protected method2");
	}
}

public class E04 {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		// if E04_Protected1 is not public, there'll be an error
		E04_Protected1 protected1 = new E04_Protected1();
		E04_Protected2 protected2 = new E04_Protected2();
		// member method not visible unless extends that
		// protected1.protectedMethod1();
		protected2.protectedMethod2();
	}
}

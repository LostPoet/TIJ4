// Exercise 1, 3, 5
package exercises.innerclasses;

public class Outer {
	private String field;
	Outer() {
		field = "Outer";
	}
	class Inner {
		public String toString() {
			return field;
		}
	}
	public Inner getInner() {
		return new Inner();
	}
	private class PrivateInner {
		public String toString() {
			return field;
		}
	}
	public PrivateInner getPrivateInner() {
		return new PrivateInner();
	}
	public static void main(String[] args) {
		Outer outer = new Outer();
		Outer.Inner inner1 = outer.getInner();
		// .new syntax to create an inner object: 
		Outer.Inner inner2 = outer.new Inner();
		// If creating an instance of class 'Inner' without an object of 'Outer':
		// !Outer.Inner inner3 = new Outer.Inner();
		
		Outer.PrivateInner inner3 = outer.getPrivateInner();
		Outer.PrivateInner inner4 = outer.new PrivateInner();
		
		System.out.println("" + inner1);
		System.out.println("" + inner2);
		System.out.println("" + inner3);
		System.out.println("" + inner4);
	}
}

class TestOuter {
	public static void main(String[] args) {
		Outer outer = new Outer();
		Outer.Inner inner1 = outer.getInner();
		Outer.Inner inner2 = outer.new Inner();
		System.out.println("" + inner1);
		System.out.println("" + inner2);
		// Compile-time error:'Outer.PrivateInner' is not visible
		// Outer.PrivateInner inner3 = outer.getPrivateInner();
		// Outer.PrivateInner inner4 = outer.new PrivateInner();
	}
}
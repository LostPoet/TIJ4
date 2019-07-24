// Exercise 7, 8
package exercises.innerclasses;

public class Phone {
	private String name = "Phone name";
	private String method() {
		return "Phone method";
	}
	private class Apple {
		private int data = 47; 
		private void anApp() {
			// Visiting the private elements of 'Phone':
			Phone.this.name = "Apple";
			Phone.this.method();
			System.out.println(Phone.this.name);
			System.out.println(Phone.this.method());
		}
	}
	// This method can be considered as the only interface of 'Phone':
	void runApple() {
		Apple apple = Phone.this.new Apple();
		// Visiting the private elements of 'Apple':
		System.out.println("Visiting private data: " + apple.data);
		apple.anApp();
	}
	public static void main(String[] args) {
		Phone a = new Phone();
		a.runApple();
	}
}

/* Conclusion:
 * There is no gap between the elements of the outer class and its inner classes,
 * even the elements are defined 'private' (to outside classes, not to them).
 */
// Exercise 12
package exercises.innerclasses;

interface Apple {
	void anApp();
}

public class Phone2 {
	private String name = "Phone name";
	private String method() {
		return "Phone method";
	}
	Apple createApple() {
		return new Apple() {
			public void anApp() {
				// Visiting the private elements of 'Phone2':
				Phone2.this.name = "Apple";
				Phone2.this.method();
				System.out.println(Phone2.this.name);
				System.out.println(Phone2.this.method());
			}
		};
	}
	public static void main(String[] args) {
		Phone2 phone = new Phone2();
		Apple apple = phone.createApple();
		apple.anApp();
	}
}

/* Conclusion:
 * An apply of anonymous inner class.
 */

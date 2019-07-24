// Exercise 13
package exercises.innerclasses;

public class MethodInnerClass2 {
	CommonInterface anonymousMethod() {
		return new CommonInterface() {
			public void generalMethod() {
				System.out.println("Inside generalMethod");
			}
		};
	}
	public static void main(String[] args) {
		MethodInnerClass2 mc2 = new MethodInnerClass2();
		mc2.anonymousMethod().generalMethod();
	}
}

/* Conclusion:
 * An apply of anonymous inner class.
 */
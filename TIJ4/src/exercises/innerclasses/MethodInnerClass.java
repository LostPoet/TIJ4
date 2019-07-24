// Exercise 9
package exercises.innerclasses;

//public interface CommonInterface {
//void generalMethod();  
//}

public class MethodInnerClass {
	CommonInterface getInterface() {
		class AnImplementation implements CommonInterface {
			public void generalMethod() {
				System.out.println("Inside generalMethod()");
			}
		}
		return new AnImplementation();
	}
	public static void main(String[] args) {
		new MethodInnerClass().getInterface().generalMethod();
	}
}

/* Conclusion:
 * Inner class inside methods.
 */

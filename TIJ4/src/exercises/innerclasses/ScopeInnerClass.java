// Exercise 10
package exercises.innerclasses;

//public interface CommonInterface {
//void generalMethod();  
//}

public class ScopeInnerClass {
	CommonInterface getInterface(boolean confirm) {
		if(confirm) {
			class AnImplementation implements CommonInterface {
				public void generalMethod() {
					System.out.println("Inside generalMethod()");
				}
			}
			return new AnImplementation();
		}
		System.out.println("Nothing to return!");
		return null;
	}
	public static void main(String[] args) {
		new ScopeInnerClass().getInterface(true).generalMethod();
	}
}

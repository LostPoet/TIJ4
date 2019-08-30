package exercises.innerclasses;

//public interface CommonInterface {
//void generalMethod();  
//}

public class E09_MethodInnerClass {
    CommonInterface getInterface() {
	class AnImplementation implements CommonInterface {
	    public void generalMethod() {
		System.out.println("Inside generalMethod()");
	    }
	}
	return new AnImplementation();
    }

    public static void main(String[] args) {
	new E09_MethodInnerClass().getInterface().generalMethod();
    }
}

/*
 * Conclusion: Inner class inside methods.
 */

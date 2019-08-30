package exercises.innerclasses;

public class E13_AnonymousInnerClass {
    CommonInterface anonymousMethod() {
	return new CommonInterface() {
	    public void generalMethod() {
		System.out.println("Inside generalMethod");
	    }
	};
    }

    public static void main(String[] args) {
	E13_AnonymousInnerClass mc2 = new E13_AnonymousInnerClass();
	mc2.anonymousMethod().generalMethod();
    }
}

/*
 * Conclusion: An apply of anonymous inner class.
 */
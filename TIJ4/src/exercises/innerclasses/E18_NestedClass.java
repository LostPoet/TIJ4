package exercises.innerclasses;

public class E18_NestedClass {
    private int nonStatic = 1;

    static class Nested {
	void f() {
	    System.out.println("Nested.f");
	    // Cannot make a static reference to the non-static field nonStatic
	    // System.out.println(nonStatic);
	    System.out.println(new E18_NestedClass().nonStatic);
	}
    }

    public static void main(String args[]) {
	Nested ne = new Nested();
	ne.f();
    }
}

class Other {
    // Specifying the nested type outside
    // the scope of the class:
    void f() {
	@SuppressWarnings("unused")
	E18_NestedClass.Nested ne = new E18_NestedClass.Nested();
    }
}
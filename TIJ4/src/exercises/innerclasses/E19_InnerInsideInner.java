package exercises.innerclasses;

public class E19_InnerInsideInner {
    class Inner1 {
	// The field data cannot be declared static in a non-static inner type,
	// unless initialized with a constant expression
	// static int data = 1;

	// The method f cannot be declared static; static methods can only
	// be declared in a static or top level type
	// static void f() {}
	class Inner2 {
	    void f() {
	    }
	}

	Inner2 makeInner2() {
	    return new Inner2();
	}
    }

    Inner1 makeInner1() {
	return new Inner1();
    }

    static class Nested1 {
	static class Nested2 {
	    void f() {
	    }
	}

	void f() {
	}
    }

    public static void main(String args[]) {
	new E19_InnerInsideInner.Nested1().f();
	new E19_InnerInsideInner.Nested1.Nested2().f();
	E19_InnerInsideInner x1 = new E19_InnerInsideInner();
	E19_InnerInsideInner.Inner1 x2 = x1.makeInner1();
	E19_InnerInsideInner.Inner1.Inner2 x3 = x2.makeInner2();
	x3.f();
    }
}

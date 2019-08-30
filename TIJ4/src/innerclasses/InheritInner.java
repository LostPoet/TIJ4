//: innerclasses/InheritInner.java
package innerclasses; /* Added by Eclipse.py */

// Inheriting an inner class.

class WithInner {
    class Inner {
    }

    // test inherit within the outer class
    class InnerInherit extends Inner {
    }
}

public class InheritInner extends WithInner.Inner {
    // ! InheritInner() {} // Won't compile
    InheritInner(WithInner wi) {
	wi.super();
    }

    public static void main(String[] args) {
	WithInner wi = new WithInner();
	InheritInner ii = new InheritInner(wi);
    }
} /// :~

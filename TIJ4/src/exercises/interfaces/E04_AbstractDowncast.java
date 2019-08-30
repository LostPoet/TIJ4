package exercises.interfaces;

abstract class Base {
    abstract void aMethod();
}

class Derived extends Base {
    void aMethod() {
	System.out.println("Derived Method");
    }
}

public class E04_AbstractDowncast {
    static void apply(Base b) {
	// ((Derived) b).aMethod();
	// abstract methods eliminates the need for downcast
	b.aMethod();
    }

    public static void main(String[] args) {
	apply(new Derived());
    }
}

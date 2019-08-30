package exercises.interfaces;

interface StoreValue {
    int I = 1;
}

class Test implements StoreValue {
}

public class E17_InterfaceFields {
    public static void main(String[] args) {
	// final
	// StoreValue.i = 2;
	// static, no need to create an object
	System.out.println(StoreValue.I);
	// inheritance
	System.out.println(Test.I);
    }
}

package exercises.innerclasses;

public class E01_Outer {
    class Inner {
    }

    public Inner getInner() {
	return new Inner();
    }

    public static void main(String[] args) {
	E01_Outer outer = new E01_Outer();
	@SuppressWarnings("unused")
	E01_Outer.Inner inner = outer.getInner();
    }
}

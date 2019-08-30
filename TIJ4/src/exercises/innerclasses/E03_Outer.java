package exercises.innerclasses;

public class E03_Outer {
    private String field;

    public E03_Outer() {
	field = "Outer";
    }

    class Inner {
	@Override
	public String toString() {
	    return field;
	}
    }

    public Inner getInner() {
	return new Inner();
    }

    public static void main(String[] args) {
	E03_Outer outer = new E03_Outer();
	E03_Outer.Inner inner = outer.getInner();
	System.out.println(inner);
    }
}

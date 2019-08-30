package exercises.interfaces;

abstract class BasePrint {
    public BasePrint() {
	print();
    }

    abstract void print();
}

class DerivedPrint extends BasePrint {
    private int value = 1;

    void print() {
	System.out.println(value);
    }
}

public class E03_AbstractPrint {
    public static void main(String[] args) {
	new DerivedPrint().print();
    }
}

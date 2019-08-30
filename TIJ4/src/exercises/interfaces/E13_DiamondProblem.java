package exercises.interfaces;

interface Fork {
    void method();
}

interface Branch1 extends Fork {
    void method();
}

interface Branch2 extends Fork {
    void method();
}

interface Merge extends Branch1, Branch2 {
    void method();
}

public class E13_DiamondProblem implements Merge {
    @Override
    public void method() {
	System.out.println("method()");
    }

    public static void main(String[] args) {
	new E13_DiamondProblem().method();
    }
}

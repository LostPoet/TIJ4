package exercises.innerclasses;

class NonDefault {
    protected int pi;

    NonDefault(int i) {
	pi = i;
    }
}

public class E15_AnonymousTest {
    static NonDefault getNonDefault(int i) {
	return new NonDefault(i) {
	    {
		System.out.println("pi = " + pi++);
		System.out.println("Instance initialization");
		pi = i;
		System.out.println("pi = " + pi);
	    }
	};
    }

    public static void main(String[] args) {
	getNonDefault(1);
    }
}

/*
 * Conclusion: 1.Pass args for the base-type non-default constructor. 2.Using
 * primative args can be without 'final'
 */
package exercises.innerclasses;

public class E07_Phone {
    private String name = "Phone name";

    private String method() {
	return "Phone method";
    }

    private class Apple {
	private int data = 47;

	private void anApp() {
	    // Visiting the private elements of 'Phone':
	    E07_Phone.this.name = "Apple";
	    System.out.println(E07_Phone.this.name);
	    System.out.println(E07_Phone.this.method());
	}
    }

    // This method can be considered as the only interface of 'Phone':
    void runApple() {
	Apple apple = this.new Apple();
	// Visiting the private elements of 'Apple':
	System.out.println("Visiting private data: " + apple.data);
	apple.anApp();
    }

    public static void main(String[] args) {
	E07_Phone a = new E07_Phone();
	a.runApple();
    }
}

/*
 * Conclusion: There is no gap between the elements of the outer class and its
 * inner classes, even the elements are defined 'private' (to outside classes,
 * not to them).
 */
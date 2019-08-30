package exercises.innerclasses;

interface Apple {
    void anApp();
}

public class E12_AnonymousPhone {
    private String name = "Phone name";

    private String method() {
	return "Phone method";
    }

    Apple createApple() {
	return new Apple() {
	    public void anApp() {
		// Visiting the private elements of 'Phone2':
		E12_AnonymousPhone.this.name = "Apple";
		E12_AnonymousPhone.this.method();
		System.out.println(E12_AnonymousPhone.this.name);
		System.out.println(E12_AnonymousPhone.this.method());
	    }
	};
    }

    public static void main(String[] args) {
	E12_AnonymousPhone phone = new E12_AnonymousPhone();
	Apple apple = phone.createApple();
	apple.anApp();
    }
}

/*
 * Conclusion: An apply of anonymous inner class.
 */

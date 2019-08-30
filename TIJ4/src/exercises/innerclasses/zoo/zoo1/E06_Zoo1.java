package exercises.innerclasses.zoo.zoo1;

import exercises.innerclasses.zoo.animal.*;

public class E06_Zoo1 {
    protected class Dog implements E06_Animal {
	// Without declaring 'public', Dog() will default to protected which is
	// unreachable outside this package
	public Dog() {
	}

	public String action() {
	    return "Burk!";
	}
    }
}

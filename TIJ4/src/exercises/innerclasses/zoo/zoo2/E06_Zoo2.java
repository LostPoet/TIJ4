package exercises.innerclasses.zoo.zoo2;

import exercises.innerclasses.zoo.animal.*;
import exercises.innerclasses.zoo.zoo1.*;

public class E06_Zoo2 extends E06_Zoo1 {
    E06_Animal getDog() {
	return new Dog();
	// Same effect with next lines:
	// return this.new Dog();
	// return new E06_Zoo1().new Dog();
    }

    public static void main(String[] args) {
	E06_Zoo2 e06_Zoo2 = new E06_Zoo2();
	System.out.println(e06_Zoo2.getDog().action());
    }
}

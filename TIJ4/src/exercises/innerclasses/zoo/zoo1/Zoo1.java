// Exercise 6
package exercises.innerclasses.zoo.zoo1;
import exercises.innerclasses.zoo.animal.*;

public class Zoo1 {
	protected class Dog implements Animal{
		// Without declaring 'public', Dog() can't be used in 'Zoo2.java'! 
		public Dog() {}
		public String action() { return "Burk!"; }
	}
}

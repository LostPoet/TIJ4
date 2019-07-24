// Exercise 6
package exercises.innerclasses.zoo.zoo2;
import exercises.innerclasses.zoo.animal.*;
import exercises.innerclasses.zoo.zoo1.*;

public class Zoo2 extends Zoo1{
	Animal getDog() { 
		return new Zoo1().new Dog();
		// Same effect with next line:
		//return this.new Dog();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Zoo2 zoo2 = new Zoo2();
		System.out.println(zoo2.getDog().action());
	}
}

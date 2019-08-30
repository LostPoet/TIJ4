package exercises.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Bag {
}

public class E07_TestList {
    public static void main(String[] args) {
	Bag[] bagArray = { new Bag(), new Bag(), new Bag(), new Bag() };
	List<Bag> list = new ArrayList<Bag>(Arrays.asList(bagArray));
	List<Bag> sub = list.subList(0, 2);
	System.out.println(list);
	System.out.println(sub);
	// The semantics of the sub list become undefined if the backing list is
	// structurally modified!
	// trigger a runtime exception:
	// list.removeAll(sub);
	sub.clear();
	// Avoid exception by making a copy of sub (Seems failed!!!)
	// List<Bag> copy = new ArrayList<Bag>(sub);
	// list.removeAll(copy);
	System.out.println(list);
	System.out.println(sub);
    }
}

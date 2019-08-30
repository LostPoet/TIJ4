package exercises.holding;

import java.util.ArrayList;
import java.util.Iterator;

public class E08_GerbilIterator {
    public static void main(String[] args) {
	ArrayList<E01_Gerbil> al = new ArrayList<E01_Gerbil>();
	for (int i = 0; i < 10; ++i)
	    al.add(new E01_Gerbil());
	for (Iterator<E01_Gerbil> it = al.iterator(); it.hasNext(); it.next().hop())
	    ;
    }
}

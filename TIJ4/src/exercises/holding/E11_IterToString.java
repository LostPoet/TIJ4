package exercises.holding;

import java.util.*;

public class E11_IterToString {
    public static void printToStrings(Iterator<?> it) {
	while (it.hasNext())
	    System.out.println(it.next().toString());
    }

    public static void main(String args[]) {
	List<Collection<String>> ca = Arrays.<Collection<String>>asList(new ArrayList<String>(),
		new LinkedList<String>(), new HashSet<String>(), new TreeSet<String>());
	for (Collection<String> c : ca)
	    // 5 elements each container
	    E04_MovieNameGenerator.fill(c);
	for (Collection<String> c : ca)
	    printToStrings(c.iterator());
    }
}

package exercises.holding;

import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

class FlimGenerator implements Generator<String> {
    // remember to initialize the reference 'filmlist'
    private String[] characters = { "Grumpy", "Happy", "Sleepy", "Dopey", "Doc", "Sneezy", "Bashful", "Snow White",
	    "Witch Queen", "Prince" };
    int next;

    public String next() {
	String r = characters[next];
	next = (next + 1) % characters.length;
	return r;
    }
}

public class E04_MovieNameGenerator {
    private static final FlimGenerator fg = new FlimGenerator();

    static Collection<String> fill(Collection<String> collection) {
	for (int i = 0; i < 5; ++i) {
	    collection.add(fg.next());
	}
	return collection;
    }

    static String[] fill(String[] array) {
	for (int i = 0; i < array.length; i++) {
	    array[i] = fg.next();
	}
	return array;
    }

    public static void main(String[] args) {
	// using Arrays.toString()
	print(Arrays.toString(fill(new String[5])));
	print(fill(new ArrayList<String>()));
	print(fill(new LinkedList<String>()));
	// note the order
	print(fill(new HashSet<String>()));
	print(fill(new LinkedHashSet<String>()));
	print(fill(new TreeSet<String>()));
    }
}

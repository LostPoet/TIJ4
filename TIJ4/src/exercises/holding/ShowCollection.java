// Exercise 4
package exercises.holding;

import java.util.*;
import static net.mindview.util.Print.*;

class FlimGenerator {
    // remember to initialize the reference 'filmlist' 
    private List<String> filmList = new ArrayList<String>();
    private int index = 0;
    FlimGenerator() {
	Collections.addAll(filmList,"Snow White", "Star Wars", "Iron Man");
    }
    String next() {
	if(index == filmList.size())
	    index = 0;
	return ((List<String>) filmList).get(index++);
    }
}

public class ShowCollection {

    static Collection<String> fillCollection(Collection<String> collection) {
	FlimGenerator fg = new FlimGenerator();
	for(int i = 0; i < 6; ++i) {
	    collection.add(fg.next());
	}
	return collection;
    }
    static String[] fillArray(String[] str) {
	FlimGenerator fg = new FlimGenerator();
	for(int i = 0; i < str.length; i++) {
	    str[i] = fg.next();
	}
	return str;
    }
    public static void main(String[] args) {
	String[] str = fillArray(new String[6]);
	for(int i = 0; i < str.length; ++i) {
	    printnb(str[i] + ", ");
	}
	print();
	print(fillCollection(new ArrayList<String>()));
	print(fillCollection(new LinkedList<String>()));
	print(fillCollection(new HashSet<String>()));
	print(fillCollection(new LinkedHashSet<String>()));
	print(fillCollection(new TreeSet<String>()));
    }

}

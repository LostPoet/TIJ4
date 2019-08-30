package exercises.holding;

import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E16_Vowels {
    // change String to Character may be better
    static Set<String> vowels = new HashSet<String>(Arrays.asList("a", "e", "i", "o", "u", "A", "E", "I", "O", "U"));

    public static void display(List<String> list) {
	List<String> temp = new ArrayList<String>();
	// avoid repeat words
	HashSet<String> processedString = new HashSet<String>();
	long count = 0;
	long total = 0;
	for (String s : list) {
	    Collections.addAll(temp, s.split(""));
	    // could use toArray()
	    for (String c : temp)
		if (vowels.contains(c))
		    count++;
	    if (!processedString.contains(s)) {
		processedString.add(s);
		print(s + ": " + count);
	    }
	    total += count;
	    temp.clear();
	    count = 0;
	}
	print("Total vowels: " + total);
    }

    public static void main(String[] args) {
	List<String> input = new ArrayList<String>(new TextFile("src/holding/UniqueWords.java", "\\W+"));
	display(input);
    }
}

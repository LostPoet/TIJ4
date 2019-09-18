package exercises.strings;

import java.util.*;
import java.util.regex.*;

public class E12_Groups2 {
    static public final String POEM = "Twas brillig, and the slithy toves\n" + "Did gyre and gimble in the wabe.\n"
	    + "All mimsy were the borogoves,\n" + "And the mome raths outgrabe.\n\n"
	    + "Beware the Jabberwock, my son,\n" + "The jaws that bite, the claws that catch.\n"
	    + "Beware the Jubjub bird, and shun\n" + "The frumious Bandersnatch.";

    public static void main(String[] args) {
	Set<String> words = new HashSet<String>();
	// The expression "\\b((?![A-Z])\\w+)\\b" can also be used via zero-width
	// negative lookahead
	Matcher m = Pattern.compile("\\b[[^[A-Z]]&&\\w]\\w*").matcher(POEM);
	while (m.find())
	    words.add(m.group());
	System.out.println("Unique words: " + words.size());
	System.out.println(words);
    }
}

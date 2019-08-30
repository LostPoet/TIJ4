package exercises.holding;

import java.util.*;
import net.mindview.util.*;

public class E21_WordsInfo {
    public static void main(String[] args) {
	Map<String, Integer> wordsStat = new HashMap<String, Integer>();
	for (String word : new TextFile("src/exercises/holding/E21_WordsInfo.java", "\\W+")) {
	    Integer freq = wordsStat.get(word);
	    wordsStat.put(word, freq == null ? 1 : freq + 1);
	}
	List<String> keys = new ArrayList<String>(wordsStat.keySet());
	Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
	for (String key : keys)
	    System.out.println(key + " => " + wordsStat.get(key));
    }
}

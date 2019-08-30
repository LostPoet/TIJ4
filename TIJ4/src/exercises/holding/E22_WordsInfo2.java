package exercises.holding;

import java.util.*;
import net.mindview.util.TextFile;

class WordCounter {
    // self-defined comparator
    public static final Comparator<WordCounter> CASE_INSENSITIVE_ORDER = new Comparator<WordCounter>() {
	public int compare(WordCounter o1, WordCounter o2) {
	    return o1.word.compareToIgnoreCase(o2.word);
	}
    };
    // final
    private final String word;
    private int frequency;

    WordCounter(String word) {
	this.word = word;
	frequency = 1;
    }

    void incFrequency() {
	++frequency;
    }

    String getWord() {
	return word;
    }

    int getFrequency() {
	return frequency;
    }

    // self-defined equals()
    public boolean equals(Object o) {
	// instanceof and equals()
	return o instanceof WordCounter && word.equals(((WordCounter) o).word);
    }

    public int hashCode() {
	return word.hashCode();
    }
}

public class E22_WordsInfo2 {
    static void updateStat(Iterator<WordCounter> it, WordCounter wc) {
	while (it.hasNext()) {
	    WordCounter currentWC = it.next();
	    if (currentWC.equals(wc))
		currentWC.incFrequency();
	}
    }

    public static void main(String[] args) {
	Set<WordCounter> stat = new HashSet<WordCounter>();
	for (String word : new TextFile("src/exercises/holding/E22_WordsInfo2.java", "\\W+")) {
	    WordCounter wc = new WordCounter(word);
	    if (stat.contains(wc))
		updateStat(stat.iterator(), wc);
	    else
		stat.add(wc);
	}
	List<WordCounter> l = new ArrayList<WordCounter>(stat);
	Collections.sort(l, WordCounter.CASE_INSENSITIVE_ORDER);
	for (WordCounter wc : l)
	    System.out.println(wc.getWord() + " => " + wc.getFrequency());
    }
}

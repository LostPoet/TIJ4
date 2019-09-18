package exercises.strings;

import java.util.regex.*;

public class E10_CheckForMatch {
    public static void main(String[] args) {
	String input = "Java now has regular expressions";
	String[] regexs = { "^Java", "\\Breg.*", "n.w\\s+h(a|i)s", "s?", "s*", "s+", "s{4}", "s{1}.", "s{0,3}" };
	for (String regex : regexs) {
	    Pattern p = Pattern.compile(regex);
	    System.out.println("Pattern: \"" + regex + "\"");
	    Matcher m = p.matcher(input);
	    while (m.find())
		System.out.println("Match \"" + m.group() + "\" at positions " + m.start() + "-" + (m.end() - 1));
	}
    }
}

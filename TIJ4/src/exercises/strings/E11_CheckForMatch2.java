package exercises.strings;

import java.util.regex.*;

public class E11_CheckForMatch2 {
    public static void main(String[] args) {
	String input = "Arline ate eight apples and one orange while Anita hadn't any";
	String regex = "(?i)((^[aeiou])|(\\s+[aeiou]))\\w+[aeiou]\\b";
	Pattern p = Pattern.compile(regex);
	System.out.println("Pattern: \"" + regex + "\"");
	Matcher m = p.matcher(input);
	while (m.find())
	    System.out.println("Match \"" + m.group() + "\" at positions " + m.start() + "-" + (m.end() - 1));
    }
}

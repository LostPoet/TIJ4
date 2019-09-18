package exercises.strings;

import java.util.regex.*;
import net.mindview.util.*;

public class E15_JGrep2 {
    public static Pattern p = null;

    public static int parseFlag(String[] args) throws Exception {
        int result = 0;
        for (int i = 2; i < args.length; ++i) {
            String s = args[i];
            boolean startWithPattern = s.regionMatches(0, "Pattern.", 0, 8);
            if (!startWithPattern) {
                System.out.println("Flag format error!");
                System.exit(0);
            }
            Class<?> pattern = Pattern.class;
            result |= pattern.getField(s.substring(8)).getInt(p);

        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java JGrep file regex flag");
            System.exit(0);
        } else if (args.length == 2) {
            p = Pattern.compile(args[1]);
        } else {
            p = Pattern.compile(args[1], parseFlag(args));
        }

        // Iterate through the lines of the input file:
        int index = 0;
        int currentLine = 1;
        Matcher m = p.matcher("");
        for (String line : new TextFile(args[0])) {
            m.reset(line);
            while (m.find())
                System.out
                        .println(index++ + ": " + m.group() + ": at line " + currentLine + " starts from " + m.start());
            currentLine++;
        }
    }
}
package exercises.strings;

import java.io.*;
import java.util.regex.*;
import net.mindview.util.*;

public class E16_JGrep3 {
    public static void parseOneFile(String name, Pattern p) {
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        for (String line : new TextFile(name)) {
            m.reset(line);
            while (m.find())
                System.out.println(index++ + ": " + m.group() + ": " + m.start());
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        File f = new File(args[0]);
        if (f.isFile())
            parseOneFile(f.getCanonicalPath(), p);
        else if (f.isDirectory()) {
            File[] files = new File(f.getCanonicalPath()).listFiles();
            for (File file : files) {
                System.out.println("****** Current file path: " + file.getCanonicalPath() + " ******");
                parseOneFile(file.getCanonicalPath(), p);
                System.out.println("****** End of parsing" + " ******");
                System.out.println();
            }
        } else {
            System.out.println("Invalid Path!!!");
            System.exit(0);
        }
    }
}

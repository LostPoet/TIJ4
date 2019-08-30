package exercises.interfaces;

import java.nio.*;
import java.util.*;

class CharProducer {
    private Random rand = new Random();
    private static final int length = 10;
    private static final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private char[] ch = new char[length];

    public char[] next() {
	for (int i = 0; i < length; ++i) {
	    ch[i] = lowers[rand.nextInt(lowers.length)];
	}
	return ch;
    }
}

class AdaptedCharProducer implements Readable {
    private int count;
    private CharProducer cp = new CharProducer();

    public AdaptedCharProducer(int count) {
	this.count = count;
    }

    public int read(CharBuffer cb) {
	if (count-- == 0)
	    return -1;
	for (char c : cp.next()) {
	    cb.append(c);
	}
	cb.append('\n');
	return 10;
    }
}

public class E16_Adapter {
    public static void main(String[] args) {
	Scanner s = new Scanner(new AdaptedCharProducer(5));
	while (s.hasNext()) {
	    System.out.println(s.next());
	}
	s.close();
    }
}

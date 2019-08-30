package exercises.interfaces;

import interfaces.interfaceprocessor.*;

class SwapString {
    public String swapping(String s) {
	char[] ch = s.toCharArray();
	for (int i = 0, j = s.length() - 1; i != j; ++i, --j) {
	    char temp = ch[i];
	    ch[i] = ch[j];
	    ch[j] = temp;
	}
	// produce a new string
	return new String(ch);
    }
}

class SwapAdapter implements Processor {
    SwapString ss = new SwapString();

    @Override
    public String name() {
	return getClass().getSimpleName();
    }

    @Override
    // covariant return type
    public String process(Object input) {
	return ss.swapping((String) input);
    }
}

public class E11_StringAdapter {
    public static void main(String[] args) {
	Apply.process(new SwapAdapter(), "abcdefg");
    }
}

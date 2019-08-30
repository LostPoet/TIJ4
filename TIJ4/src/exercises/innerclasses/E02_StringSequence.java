package exercises.innerclasses;

import innerclasses.Selector;
import innerclasses.Sequence;

// Selector is an interface to manipulate seqence
// Seqence is a class to hold Objects
public class E02_StringSequence {
    private String str = "str";

    public String toString() {
	return str;
    }

    public static void main(String[] args) {
	Sequence seq = new Sequence(3);
	for (int i = 0; i < 3; ++i)
	    seq.add(new E02_StringSequence());
	Selector sel = seq.selector();
	for (; !sel.end(); sel.next())
	    System.out.println(sel.current());
    }
}

// Exercise 2
package exercises.innerclasses;
import innerclasses.*;

public class StringSequence {
	private String str = "str";
	public String toString() {
		return str;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sequence seq = new Sequence(3);
		for(int i = 0; i < 3; ++i)
			seq.add(new StringSequence());
		Selector sel = seq.selector();
		for(; !sel.end(); sel.next())
			System.out.println(sel.current());
	}
}

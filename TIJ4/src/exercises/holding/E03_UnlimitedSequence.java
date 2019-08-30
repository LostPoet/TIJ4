package exercises.holding;

import java.util.*;
import innerclasses.Selector;

public class E03_UnlimitedSequence {
    private List<Object> items;

    public E03_UnlimitedSequence() {
	items = new ArrayList<Object>();
    }

    public void add(Object x) {
	items.add(x);
    }

    private class SequenceSelector implements Selector {
	private int i = 0;

	public boolean end() {
	    return i == items.size();
	}

	public Object current() {
	    return items.get(i);
	}

	public void next() {
	    if (i < items.size())
		i++;
	}
    }

    public Selector selector() {
	return new SequenceSelector();
    }

    public static void main(String[] args) {
	E03_UnlimitedSequence sequence = new E03_UnlimitedSequence();
	for (int i = 0; i < 10; i++)
	    sequence.add(Integer.toString(i));
	Selector selector = sequence.selector();
	while (!selector.end()) {
	    System.out.print(selector.current() + " ");
	    selector.next();
	}
    }
}

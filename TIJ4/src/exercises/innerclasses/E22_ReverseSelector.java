package exercises.innerclasses;

import innerclasses.Selector;

//Modified to get a public 'Selector' interface
//interface Selector {
//boolean end();
//Object current();
//void next();
//}

public class E22_ReverseSelector {
    private Object[] items;
    private int next = 0;

    public E22_ReverseSelector(int size) {
	items = new Object[size];
    }

    public void add(Object x) {
	if (next < items.length)
	    items[next++] = x;
    }

    public class ReverseSelector implements Selector {
	private int i = items.length - 1;

	@Override
	public boolean end() {
	    return i == -1;
	}

	@Override
	public Object current() {
	    return items[i];
	}

	@Override
	public void next() {
	    if (i > -1)
		--i;
	}
    }

    public Selector reverseSelector() {
	return new ReverseSelector();
    }

    public static void main(String[] args) {
	E22_ReverseSelector sequence = new E22_ReverseSelector(10);
	for (int i = 0; i < 10; i++)
	    sequence.add(Integer.toString(i));
	Selector selector = sequence.reverseSelector();
	while (!selector.end()) {
	    System.out.print(selector.current() + " ");
	    selector.next();
	}
    }

}

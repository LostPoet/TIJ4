// Exercise 3
package exercises.holding;

import java.util.*;
import innerclasses.Selector;

public class Sequence2 {
    
    private List<Object> items;
    public Sequence2() { items = new ArrayList<Object>(); }
    
    public void add(Object x) { items.add(x); }
    
    private class SequenceSelector implements Selector {
	private int i = 0;
	public boolean end() { return i == items.size(); }
	public Object current() { return items.get(i); }
	public void next() { if(i < items.size()) i++; }
    }
    
    public Selector selector() {
	return new SequenceSelector();
    }	
    
    public static void main(String[] args) {
	Sequence2 sequence = new Sequence2();
	for(int i = 0; i < 10; i++)
	    sequence.add(Integer.toString(i));
	Selector selector = sequence.selector();
	while(!selector.end()) {
	    System.out.print(selector.current() + " ");
	    selector.next();
	}
    }

}

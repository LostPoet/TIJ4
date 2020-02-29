package exercises.holding;

import java.util.Iterator;
import static net.mindview.util.Print.*;

class Sequence {
  private Object[] items;
  private int next = 0;
  
  public Sequence(int size) {
    items = new Object[size];
  }
  
  public void add(Object x) {
    if (next < items.length)
      items[next++] = x;
  }
  
  public Iterator<Object> iterator() {
    return new Iterator<Object>() {
      private int index = 0;
      
      @Override
      public boolean hasNext() {
        return index < next;
      }
      
      @Override
      public Object next() {
        return items[index++];
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}

public class E09_SequenceIterator {
  public static void main(String[] args) {
    Sequence s = new Sequence(10);
    for (int i = 0; i < 10; ++i)
      s.add(Integer.toString(i));
    for (Iterator<Object> it = s.iterator(); it
        .hasNext(); print(it.next()))
      ;
  }
}

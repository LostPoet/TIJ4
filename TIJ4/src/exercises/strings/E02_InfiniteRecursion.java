package exercises.strings;

import java.util.*;

public class E02_InfiniteRecursion {
    public String toString() {
	StringBuilder s = new StringBuilder(" InfiniteRecursion address: ");
	s.append(super.toString());
	s.append("\n");
	return s.toString();
    }

    public static void main(String[] args) {
	List<E02_InfiniteRecursion> v = new ArrayList<E02_InfiniteRecursion>();
	for (int i = 0; i < 10; i++)
	    v.add(new E02_InfiniteRecursion());
	System.out.println(v);
    }
}

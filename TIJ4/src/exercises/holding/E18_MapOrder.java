package exercises.holding;

import java.util.*;
import net.mindview.util.*;

public class E18_MapOrder {
    public static void main(String[] args) {
	Map<String, String> m1 = new HashMap<String, String>(Countries.capitals(25));
	System.out.println(m1);
	// Use array to store keys
	String[] keys = m1.keySet().toArray(new String[0]);
	Arrays.sort(keys);
	Map<String, String> m2 = new LinkedHashMap<String, String>();
	for (String key : keys)
	    m2.put(key, m1.get(key));
	System.out.println(m2);
    }
}

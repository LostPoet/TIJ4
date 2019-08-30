package exercises.holding;

import java.util.*;
import static net.mindview.util.Print.*;

public class E17_GerbilMap {
    public static void main(String[] args) {
	Map<String, E01_Gerbil> gerbils = new HashMap<String, E01_Gerbil>();
	gerbils.put("Fuzzy", new E01_Gerbil());
	gerbils.put("Spot", new E01_Gerbil());
	gerbils.put("Joe", new E01_Gerbil());
	gerbils.put("Ted", new E01_Gerbil());
	gerbils.put("Heather", new E01_Gerbil());
	// The book uses entrySet() and its iterator here
	for (Iterator<String> it = gerbils.keySet().iterator(); it.hasNext();) {
	    String name = it.next();
	    print(name);
	    gerbils.get(name).hop();
	}
    }
}

package exercises.holding;

import java.util.*;

public class E01_Gerbil {

    static int gerbilNumber;
    int id;

    public E01_Gerbil() {
	id = gerbilNumber++;
    }

    void hop() {
	System.out.println(id + " is hopping.");
    }

    public static void main(String[] args) {
	ArrayList<E01_Gerbil> e01_Gerbils = new ArrayList<E01_Gerbil>();
	for(int i = 0; i < 3; ++i)
	    e01_Gerbils.add(new E01_Gerbil());
	for(int i = 0; i < e01_Gerbils.size(); ++i) {
	    e01_Gerbils.get(i).hop();
	}
	for(E01_Gerbil g : e01_Gerbils) {
	    g.hop();
	}
    }

}

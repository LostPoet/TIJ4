package exercises.innerclasses;

interface Monster {
    void menace();
}

interface DangerousMonster extends Monster {
    void destroy();
}

interface Lethal {
    void kill();
}

//class DragonZilla implements DangerousMonster {
//	public void menace() {}
//	public void destroy() {}
//}	

interface Vampire extends DangerousMonster, Lethal {
    void drinkBlood();
}

//class VeryBadVampire implements Vampire {
//	public void menace() {}
//	public void destroy() {}
//	public void kill() {}
//	public void drinkBlood() {}
//}	

public class E14_HorrorShow {
    static void u(Monster b) {
	b.menace();
    }

    static void v(DangerousMonster d) {
	d.menace();
	d.destroy();
    }

    static void w(Lethal l) {
	l.kill();
    }

    public static void main(String[] args) {
	DangerousMonster barney = new DangerousMonster() {
	    public void menace() {
	    }

	    public void destroy() {
	    }
	};
	u(barney);
	v(barney);
	Vampire vlad = new Vampire() {
	    public void menace() {
	    }

	    public void destroy() {
	    }

	    public void kill() {
	    }

	    public void drinkBlood() {
	    }
	};
	u(vlad);
	v(vlad);
	w(vlad);
    }
}

/*
 * Conclusion: Define less classes thanks to anonymous inner class, extracting
 * implementation from interface.
 */

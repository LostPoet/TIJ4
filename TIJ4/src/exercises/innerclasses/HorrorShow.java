// Exercise 14
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

public class HorrorShow {
	static void u(Monster b) { b.menace(); }
	static void v(DangerousMonster d) {
		d.menace();
		d.destroy();
	}
	static void w(Lethal l) { l.kill(); }
	static DangerousMonster createDangerousMonster() {
		return new DangerousMonster() {
			public void menace() {}
			public void destroy() {}
		};
	}
	static Vampire createVampire() {
		return new Vampire() {
			public void menace() {}
			public void destroy() {}
			public void kill() {}
			public void drinkBlood() {}
		};
	}
	public static void main(String[] args) {
		DangerousMonster barney = createDangerousMonster();
		u(barney);
		v(barney);
		Vampire vlad = createVampire();
		u(vlad);
		v(vlad);
		w(vlad);
	}
}

/* Conclusion:
 * 1.Define less classes thanks to anonymous inner class, 
 * extracting implementation from interface.
 * 2.'Static' can be made to anonymous method.
 */

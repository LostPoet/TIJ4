package exercises.interfaces;

interface CanClimb {
    void climb();
}

interface CanFight {
    void fight();
}

interface CanSwim {
    void swim();
}

interface CanFly {
    void fly();
}

class ActionCharacter {
    public void fight() {
    }
}

class Hero extends ActionCharacter implements CanFight, CanSwim, CanFly, CanClimb {
    public void climb() {
    }

    public void swim() {
    }

    public void fly() {
    }
}

public class E12_Adventure {
    public static void s(CanClimb x) {
	x.climb();
    }

    public static void t(CanFight x) {
	x.fight();
    }

    public static void u(CanSwim x) {
	x.swim();
    }

    public static void v(CanFly x) {
	x.fly();
    }

    public static void w(ActionCharacter x) {
	x.fight();
    }

    public static void main(String[] args) {
	Hero h = new Hero();
	s(h);
	t(h); // Treat it as a CanFight
	u(h); // Treat it as a CanSwim
	v(h); // Treat it as a CanFly
	w(h); // Treat it as an ActionCharacter
    }
}

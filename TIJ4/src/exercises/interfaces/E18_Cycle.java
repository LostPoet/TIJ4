package exercises.interfaces;

interface Cycle {
    void f();
}

interface CycleFactory {
    Cycle create();
}

class Unicycle implements Cycle {
    public void f() {
    }
}

class Bicycle implements Cycle {
    public void f() {
    }
}

class Tricycle implements Cycle {
    public void f() {
    }
}

class UnicycleFactory implements CycleFactory {
    public Cycle create() {
	return new Unicycle();
    }
}

class BicycleFactory implements CycleFactory {
    public Cycle create() {
	return new Bicycle();
    }
}

class TricycleFactory implements CycleFactory {
    public Cycle create() {
	return new Tricycle();
    }
}

public class E18_Cycle {
    static void ride(CycleFactory cf) {
	Cycle cycle = cf.create();
	cycle.f();
    }

    public static void main(String[] args) {
	ride(new UnicycleFactory());
	ride(new BicycleFactory());
	ride(new TricycleFactory());
    }
}

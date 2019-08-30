package exercises.polymorphism;

class Cycle {
    void ridden() {
    }
}

class Unicycle extends Cycle {
    void ridden() {
        System.out.println("riding unicycle");
    }
}

class Bicycle extends Cycle {
    void ridden() {
        System.out.println("riding bicycle");
    }
}

class Tricycle extends Cycle {
    void ridden() {
        System.out.println("riding tricycle");
    }
}

public class E01_Cycle {
    static void ride(Cycle cycle) {
        cycle.ridden();
    }

    public static void main(String[] args) {
        ride(new Unicycle());
        ride(new Bicycle());
        ride(new Tricycle());
    }
}

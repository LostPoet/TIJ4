package exercises.interfaces;

import static net.mindview.util.Print.print;

interface FastFood {
    void fastFood();
}

class Meal {
    Meal() {
	print("Meal()");
    }
}

class Bread {
    Bread() {
	print("Bread()");
    }
}

class Cheese {
    Cheese() {
	print("Cheese()");
    }
}

class Lettuce {
    Lettuce() {
	print("Lettuce()");
    }
}

class Lunch extends Meal {
    Lunch() {
	print("Lunch()");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
	print("PortableLunch()");
    }
}

@SuppressWarnings("unused")
public class E08_Sandwich extends PortableLunch implements FastFood {
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();

    public E08_Sandwich() {
	print("Sandwich()");
    }

    @Override
    public void fastFood() {
	print("fastFood()");
    }

    public static void main(String[] args) {
	new E08_Sandwich().fastFood();
    }
}

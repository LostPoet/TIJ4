package experiment;

class Base {
    static int i = 1;

    static void display() {
        System.out.println(i);
    }
}

class Derived extends Base {
}

public class InheritanceStatics {
    public static void main(String[] args) {
        Derived.display();
        System.out.println(Derived.i);
    }
}

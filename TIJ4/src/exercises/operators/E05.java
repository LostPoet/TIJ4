package exercises.operators;

import static net.mindview.util.Print.print;

class Dog {
    String name;
    String says;
}

public class E05 {
    public static void main(String[] args) {

        // code for exercise 5
        Dog obj1 = new Dog();
        Dog obj2 = new Dog();
        obj1.name = "spot";
        obj1.says = "Ruff!";
        obj2.name = "scruffy";
        obj2.says = "Wurf!";
        print(obj1.name);
        print(obj1.says);
        print(obj2.name);
        print(obj2.says);

        // code for exercise 6
        Dog refer = obj1;
        print(obj1 == obj2);
        print(obj1 == refer);
        print(obj2 == refer);
        print(obj1.equals(obj2));
        print(obj1.equals(refer));
        print(obj2.equals(refer));
    }
}

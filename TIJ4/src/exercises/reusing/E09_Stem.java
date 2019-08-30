package exercises.reusing;

class Component1 {
    public Component1() {
        System.out.println("Component1()");
    }
}

class Component2 {
    public Component2() {
        System.out.println("Component2()");
    }
}

class Component3 {
    public Component3() {
        System.out.println("Component3()");
    }
}

@SuppressWarnings("unused")
class Root {
    private Component1 component1 = new Component1();
    private Component2 component2 = new Component2();
    private Component3 component3 = new Component3();

    public Root() {
        System.out.println("Root()");
    }
}

@SuppressWarnings("unused")
public class E09_Stem extends Root {
    private Component1 component1 = new Component1();
    private Component2 component2 = new Component2();
    private Component3 component3 = new Component3();

    public E09_Stem() {
        System.out.println("E09_Stem()");
    }

    public static void main(String[] args) {
        new E09_Stem();
    }
}

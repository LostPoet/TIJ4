package exercises.interfaces;

interface Interface1 {
    void f1();

    void f2();
}

interface Interface2 {
    void g1();

    void g2();
}

interface Interface3 {
    void t1();

    void t2();
}

interface Interface4 extends Interface1, Interface2, Interface3 {
    void h();
}

class Concrete {
    void concreteMethod() {
	System.out.println("concreteMethod()");
    }
}

class Combine extends Concrete implements Interface4 {
    @Override
    public void f1() {
    }

    @Override
    public void f2() {
    }

    @Override
    public void g1() {
    }

    @Override
    public void g2() {
    }

    @Override
    public void t1() {
    }

    @Override
    public void t2() {
    }

    @Override
    public void h() {
    }
}

public class E14_InterfaceInheritance {
    public static void w(Interface1 i) {
	i.f1();
	i.f2();
    }

    public static void x(Interface2 i) {
	i.g1();
	i.g2();
    }

    public static void y(Interface3 i) {
	i.t1();
	i.t2();
    }

    public static void z(Interface4 i) {
	i.f1();
	i.f2();
	i.g1();
	i.g2();
	i.t1();
	i.t2();
	i.h();
    }

    public static void main(String[] args) {
	Combine c = new Combine();
	w(c);
	x(c);
	y(c);
	z(c);
    }
}

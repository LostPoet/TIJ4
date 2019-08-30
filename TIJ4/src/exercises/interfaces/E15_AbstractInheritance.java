package exercises.interfaces;

abstract class Abstract {
    String s;

    public Abstract(String s) {
	this.s = s;
    }

    abstract void af();
}

class Combine2 extends Abstract implements Interface4 {
    public Combine2() {
	super("Combine2");
    }

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

    @Override
    void af() {
    }
}

public class E15_AbstractInheritance {
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

    public static void a(Abstract a) {
	a.af();
    }

    public static void main(String[] args) {
	Combine2 c = new Combine2();
	w(c);
	x(c);
	y(c);
	z(c);
	a(c);
    }
}

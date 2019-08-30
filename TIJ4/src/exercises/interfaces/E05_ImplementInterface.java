package exercises.interfaces;

import exercises.interfaces.interfaces.E05_Interfaces;

public class E05_ImplementInterface implements E05_Interfaces {
    @Override
    public void method1() {
	System.out.println("method1");
    }

    @Override
    public void method2() {
	System.out.println("method2");
    }

    @Override
    public void method3() {
	System.out.println("method3");
    }

    public static void main(String[] args) {
	E05_ImplementInterface e = new E05_ImplementInterface();
	e.method1();
	e.method2();
	e.method3();
    }
}

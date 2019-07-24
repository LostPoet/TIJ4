package exercises.initialization;

public class E08 {
    void method1() {
        method2();
        this.method2();
    }

    void method2() {
        System.out.println("method2()");
    }

    public static void main(String[] args) {
        E08 ref = new E08();
        ref.method1();
    }
}

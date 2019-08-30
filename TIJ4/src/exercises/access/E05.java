package exercises.access;

class AccessTest {
    public int field1 = 1;
    protected int field2 = 2;
    int field3 = 3;
    // unused compile warning which others will not get even unused
    // private int field4 = 4;

    public void method1() {
        System.out.println("method1()");
    }

    protected void method2() {
        System.out.println("method2()");
    }

    void method3() {
        System.out.println("method3()");
    }

    // unused compile warning which others will not get even unused
    // private void method4() {
    // System.out.println("method4()");
    // }
}

public class E05 {
    public static void main(String[] args) {
        AccessTest aTest = new AccessTest();
        System.out.println(aTest.field1);
        System.out.println(aTest.field2);
        System.out.println(aTest.field3);
        // not visible
        // System.out.println(aTest.field4);
        aTest.method1();
        aTest.method2();
        aTest.method3();
        // not visible
        // aTest.method4();
    }

}

package exercises.object;

public class E08 {
    static int theOne = 1;

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        E08 obj1 = new E08();
        E08 obj2 = new E08();
        System.out.println(obj1.theOne);
        System.out.println(obj2.theOne);
        obj1.theOne++;
        System.out.println(obj1.theOne);
        System.out.println(obj2.theOne);
        obj2.theOne--;
        System.out.println(obj1.theOne);
        System.out.println(obj2.theOne);
    }
}

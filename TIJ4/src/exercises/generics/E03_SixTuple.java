package exercises.generics;

import net.mindview.util.FiveTuple;

class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {
    public final F sixth;

    public SixTuple(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        sixth = f;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ", " + fifth + ", " + sixth + ")";
    }
}

public class E03_SixTuple {
    public static void main(String[] args) {
        SixTuple<String, Integer, String, Integer, String, Integer> six = new SixTuple<String, Integer, String, Integer, String, Integer>(
                "A", 1, "B", 2, "C", 3);
        System.out.println(six);
    }
}

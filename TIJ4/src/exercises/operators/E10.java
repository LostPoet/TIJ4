package exercises.operators;

public class E10 {
    public static void main(String[] args) {
        int val1 = 0x72;
        int val2 = 0x31;
        System.out.println("val1: " + Integer.toBinaryString(val1));
        System.out.println("val2: " + Integer.toBinaryString(val2));
        System.out.println("~val1: " + Integer.toBinaryString(~val1));
        System.out.println("~val2: " + Integer.toBinaryString(~val2));
        System.out.println("val1 & val1: " + Integer.toBinaryString(val1 & val1));
        System.out.println("val1 | val1: " + Integer.toBinaryString(val1 | val1));
        System.out.println("val1 ^ val1: " + Integer.toBinaryString(val1 ^ val1));
        System.out.println("val1 & val2: " + Integer.toBinaryString(val1 & val2));
        System.out.println("val1 | val2: " + Integer.toBinaryString(val1 | val2));
        System.out.println("val1 ^ val2: " + Integer.toBinaryString(val1 ^ val2));
    }
}

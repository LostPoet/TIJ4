package exercises.innerclasses;

import exercises.innerclasses.E01_Outer;

public class E05_NewInnerClass {
    public static void main(String[] args) {
	E01_Outer outer = new E01_Outer();
	outer.new Inner();
    }
}

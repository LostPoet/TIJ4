//: typeinfo/Shapes.java
package typeinfo; /* Added by Eclipse.py */

import java.util.*;

abstract class Shape {
    void draw() {
        System.out.println(this + ".draw()");
    }

    abstract public String toString();
}

class Circle extends Shape {
    public String toString() {
        return "Circle";
    }
}

class Square extends Shape {
    public String toString() {
        return "Square";
    }
}

class Triangle extends Shape {
    public String toString() {
        return "Triangle";
    }
}

class TestCast {
}

public class Shapes {
    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(new Circle(), new Square(), new Triangle());
        for (Shape shape : shapeList)
            shape.draw();
        Shape s = new Circle();
        // trigger an error:
        // System.out.println((TestCast)s);
        // get an exception:
        System.out.println((Square) s);
    }
} /*
   * Output: Circle.draw() Square.draw() Triangle.draw()
   */// :~

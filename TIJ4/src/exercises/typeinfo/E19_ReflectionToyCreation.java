package exercises.typeinfo;

import java.lang.reflect.*;

// typeinfo.toys.ToyTest
// class Toy {
// Toy() {}
// Toy(int i) {}
// }

public class E19_ReflectionToyCreation {
    public static void main(String[] args) throws Exception {
        Class<?> toy = Class.forName("typeinfo.toys.Toy");
        Constructor<?>[] constructors = toy.getDeclaredConstructors();
        // The code below assume that constructors of Toy are already known 
        for (Constructor<?> constructor : constructors) {
            constructor.setAccessible(true);
            Class<?>[] argsArray = constructor.getParameterTypes();
            // when encountering non-default contructor...
            if (argsArray.length != 0) {
                System.out.println(constructor.newInstance(1));
            }
        }
    }
}

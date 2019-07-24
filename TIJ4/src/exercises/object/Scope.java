package exercises.object;

public class Scope {
    int i = 1;
    String str = "a string";
    // Duplicate field Scope.i
    // for(int i;;) { }

    {
        System.out.println(i);
        System.out.println(str);
        // If you aren't initialize 'i' here, there'll be an erroe.
        int i = 2;
        String str = "another string";
        System.out.println(i);
        System.out.println(str);
        // An interesting test:
        // return;
    }

    {
        int i = 3;
        @SuppressWarnings("unused")
        String str = "another string";
        System.out.println(i);
        {
            // Duplicate local variable i
            // int i = 4;
            // Duplicate local variable str
            // String str = "another string";
        }
    }

    void method() {
        System.out.println(i);

        int i = 5;
        {
            // Duplicate local variable i
            // int i = 6;
        }

        System.out.println("In method()");
        System.out.println(i++);
        System.out.println("i Modified:");
        System.out.println(i);
    }

    static public void main(String[] args) {
        Scope s = new Scope();
        s.method();
    }
}

/*
 * Conclusion: (for both primitives and references) 1.In the individual scopes,
 * you can't define a variable twice. 2.If a class field has the same name with
 * a local variable, the local variable will cover the field after it's been
 * defined.
 */

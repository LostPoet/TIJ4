package exercises.initialization;

class Tank {
    boolean isFull = false;

    Tank fill() {
        isFull = true;
        return this;
    }

    Tank empty() {
        isFull = false;
        return this;
    }

    protected void finalize() {
        if (isFull)
            System.out.println("error: Tank is full.");
    }
}

public class E12 {
    public static void main(String[] args) {
        new Tank().fill().empty();
        // if give the object below a reference, finalize() won't get executed
        new Tank().fill();
        System.gc();
    }
}

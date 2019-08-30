package exercises.access.local;

class E09_PackagedClass {
    public E09_PackagedClass() {
        System.out.println("Creating a packaged class");
    }

    public static void aStatic() {
        System.out.println("public static");
    }

    public static void main(String[] args) {
        System.out.println("Inside local main");
    }
}

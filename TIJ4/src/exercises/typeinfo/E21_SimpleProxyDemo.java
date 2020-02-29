package exercises.typeinfo;

import static net.mindview.util.Print.*;

interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject implements Interface {
    public void doSomething() {
        print("doSomething");
    }

    public void somethingElse(String arg) {
        print("somethingElse " + arg);
    }
}

class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    public void doSomething() {
        long startTime = System.nanoTime();
        print("SimpleProxy doSomething");
        proxied.doSomething();
        long elapsedNanos = System.nanoTime() - startTime;
        print("elapsedNanos: " + elapsedNanos);
    }

    public void somethingElse(String arg) {
        long startTime = System.nanoTime();
        print("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
        long elapsedNanos = System.nanoTime() - startTime;
        print("elapsedNanos: " + elapsedNanos);
    }
}

public class E21_SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        print("---------------------------");
        consumer(new SimpleProxy(new RealObject()));
    }
}

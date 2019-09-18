package exercises.typeinfo;

import static net.mindview.util.Print.*;
import static exercises.typeinfo.E21_SimpleProxyDemo_Deprecated.consumer;

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
    public static void main(String[] args) {
        consumer(new RealObject());
        print("---------------------------");
        consumer(new SimpleProxy(new RealObject()));
    }
}

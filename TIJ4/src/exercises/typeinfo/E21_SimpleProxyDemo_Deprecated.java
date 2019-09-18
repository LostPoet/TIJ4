package exercises.typeinfo;

import static net.mindview.util.Print.*;

import java.util.HashMap;
import java.util.Map;

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

class DeprecatedSimpleProxy implements Interface {
    private static Map<String, Integer> callTimes = new HashMap<String, Integer>();
    private static String[] methodNames = { "doSomething()", "somethingElse()" };
    private Interface proxied;

    static {
        callTimes.put(methodNames[0], 0);
        callTimes.put(methodNames[1], 0);
    }

    static void callOnce(String methodName) {
        int times = callTimes.get(methodName);
        callTimes.replace(methodName, ++times);
    }

    public DeprecatedSimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    public void doSomething() {
        callOnce(methodNames[0]);
        print("SimpleProxy doSomething");
        proxied.doSomething();
        print("Calltimes through proxy: " + callTimes);
    }

    public void somethingElse(String arg) {
        callOnce(methodNames[1]);
        print("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
        print("Calltimes through proxy: " + callTimes);
    }
}

public class E21_SimpleProxyDemo_Deprecated {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        print("---------------------------");
        consumer(new DeprecatedSimpleProxy(new RealObject()));
    }
}

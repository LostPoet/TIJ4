package exercises.typeinfo;

import static net.mindview.util.Print.print;
import static exercises.typeinfo.E21_SimpleProxyDemo_Deprecated.consumer;
import java.lang.reflect.*;

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if (args != null)
            for (Object arg : args)
                System.out.println("  " + arg);
        long startTime = System.nanoTime();
        Object result = method.invoke(proxied, args);
        long elapsedNanos = System.nanoTime() - startTime;
        print("elapsedNanos: " + elapsedNanos);
        return result;
    }
}

public class E22_SimpleDynamicProxyDemo {
    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        // Insert a proxy and call again:
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class<?>[] { Interface.class }, new DynamicProxyHandler(real));
        consumer(proxy);
    }
}

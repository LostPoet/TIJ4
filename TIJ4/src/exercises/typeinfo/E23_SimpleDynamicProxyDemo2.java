package exercises.typeinfo;

import java.lang.reflect.*;

class DynamicProxyHandler2 implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler2(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy: " + proxy);
        return method.invoke(proxied, args);
    }
}

public class E23_SimpleDynamicProxyDemo2 {
    public static void main(String[] args) {
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class<?>[] { Interface.class }, new DynamicProxyHandler2(new RealObject()));
        proxy.toString();
    }
}

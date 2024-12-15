package javaproxies.app.dynamicjdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ISubjectProxyHandler implements InvocationHandler {
    private final ISubject subject;

    public ISubjectProxyHandler(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method execution" + method.getName());
        Object result = method.invoke(subject, args);
        System.out.println("After method execution" + method.getName());
        return result;
    }
}

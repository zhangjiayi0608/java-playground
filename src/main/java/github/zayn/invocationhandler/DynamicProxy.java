package github.zayn.invocationhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangjiayi
 */
public class DynamicProxy {
    public static void main(String[] args) {
        IHelloImpl hello = new IHelloImpl();
        MyInvocation handler = new MyInvocation(hello);
        IHello proxyHello = (IHello) Proxy.newProxyInstance(IHelloImpl.class.getClassLoader(), IHello.class.getInterfaces(), handler);
        proxyHello.sayHello();
    }
}

interface IHello {
    void sayHello();
}

class IHelloImpl implements IHello {
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}

class MyInvocation implements InvocationHandler {
    private Object target;

    public MyInvocation(Object target) {
        this.target = target;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke method");
        System.out.println("Method name : " + method.getName());
        Object result = method.invoke(target, args);
        return result;

    }
}

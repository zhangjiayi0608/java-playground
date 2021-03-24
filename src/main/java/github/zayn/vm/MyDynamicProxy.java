package github.zayn.vm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangjiayi
 */

public class MyDynamicProxy {
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代码实例
        Hello proxyHello = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler);
        // 调用代理方法
        proxyHello.sayHello("zhangjiayi");
        proxyHello.sayGoodBye("zhangjiayi1");
    }
}

interface Hello {
    void sayHello(String name);

    void sayGoodBye(String name);
}

class HelloImpl implements Hello {
    @Override
    public void sayHello(String name) {
        System.out.println(name + " Hello World");
    }


    @Override
    public void sayGoodBye(String name) {
        System.out.println(name + " GoodBye World");
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        String methodName = method.getName();
        if (methodName.equals("sayHello")) {
            System.out.println("Invoking sayHello");
        } else {
            System.out.println("invoking sayGoodBye");
        }
        Object result = method.invoke(target, args);
        return result;
    }
}


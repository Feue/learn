package Advanced.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的示例
 *
 * @author Feue
 * @create 2021-06-17 22:04
 */
interface Human {
    String getNation();
    void eat(String food);
}

// 被代理类
class SuperMan implements Human {
    @Override
    public String getNation() {
        return "I am from America";
    }

    @Override
    public void eat(String food) {
        System.out.println("I like eat "+food);
    }
}

/*
动态代理需要解决的问题：
1. 如何根据加到到内存中的被代理类，动态的创建一个代理类及其对象。
2. 当通过代理类的对象调用方法时，如何动态的去调用被代理类中的同名方法。
 */
class ProxyFactory {
    // 调用此方法，返回一个代理类对象。解决问题1
    // obj 为被代理类对象
    public static Object getProxyInstance(Object obj) {
        Class clazz = obj.getClass();
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    // 需要使用被代理类的对象赋值
    private Object obj;

    public void bind(Object obj) {
        this.obj = obj;
    }

    // 当我们通过代理类的对象，调用方法 a 时，就会自动的调用如下方法：invoke()
    // 将被代理类要执行的方法 a 的功能声明在 invoke() 中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // method 即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法。
        // obj 为被代理类对象
        Object returnValue = method.invoke(obj, args);
        //
        return returnValue;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        // proxyInstance: 代理类对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        String nation = proxyInstance.getNation();
        System.out.println(nation);
        proxyInstance.eat("麻辣香锅");

        System.out.println("******************************************");

        NikeClothFactory nike = new NikeClothFactory();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nike);
        proxyClothFactory.produceCloth();
    }
}

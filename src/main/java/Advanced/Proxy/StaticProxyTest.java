package Advanced.Proxy;

/**
 * 静态代理举例
 *
 * 特点：代理类和被代理类在编译期间就确定好了
 *
 * @author Feue
 * @create 2021-06-17 16:24
 */
interface ClothFactory {
    void produceCloth();
}

// 代理类
class ProxyClothFactory implements ClothFactory {
    // 使用被代理类对象进行实例化
    private ClothFactory factory;

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备");
        factory.produceCloth();
        System.out.println("代理工厂做一些后续的收尾工作");
    }
}

// 被代理类
class NikeClothFactory implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("nike");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        // 创建被代理类对象
        NikeClothFactory nike = new NikeClothFactory();
        // 创建代理类对象
        ProxyClothFactory proxy = new ProxyClothFactory(nike);
        // 代理类调用方法
        proxy.produceCloth();
    }
}

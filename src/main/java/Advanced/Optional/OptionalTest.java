package Advanced.Optional;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional 类：为了在程序中避免空指针异常
 *
 * @author Feue
 * @create 2021-06-22 21:11
 */
public class OptionalTest {
    /*
    创建 Optional 类对象的方法：
        Optional.of(T t) : 创建一个 Optional 实例， t 必须非空
        Optional.empty() : 创建一个空的 Optional 实例
        Optional.ofNullable(T t) t 可以为 null
     */
    @Test
    public void test1() {
        Girl girl = new Girl();
//        girl = null;

//        Optional<Girl> girlOptional = Optional.of(girl);
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        System.out.println(girlOptional);
        // T orElse(T other) 如果有值则将其返回，否则返回指定的 other 对象。
        Girl girl1 = girlOptional.orElse(new Girl("LaLa"));
        System.out.println(girl1);
    }

    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }
    @Test
    public void test2() {
        Boy boy = new Boy();
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }
    // 优化后的 getGirlName(Boy boy)
    public String getGirlName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }
    @Test
    public void test3() {
        Boy boy = new Boy();
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }
    // 使用 Optional 类的 getGirlName(Boy boy)
    public String getGirlName2(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("1")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("2"));
        return girl1.getName();
    }
    @Test
    public void test4() {
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("123"));
        System.out.println(getGirlName2(boy));
    }
}

package github.zayn.java8;

/**
 * @InterfaceName NewInterface
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/9/13 10:57 上午
 **/
public interface AnotherInterface {
    void anotherMethod();
    default void doSomething(){
        System.out.println("AnotherInterface doSomething");
    }
}

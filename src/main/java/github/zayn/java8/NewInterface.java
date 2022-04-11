package github.zayn.java8;

/**
 * @InterfaceName NewInterface
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/9/13 10:57 上午
 **/
public interface NewInterface {
    void otherMethod();
    default void doSomething(){
        System.out.println("NewInterface doSomething");
    }
}

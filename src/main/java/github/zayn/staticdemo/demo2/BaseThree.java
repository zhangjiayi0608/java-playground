package github.zayn.staticdemo.demo2;

/**
 * @ClassName BaseOne
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2023/1/12 4:13 PM
 **/
public class BaseThree extends BaseTwo {
    public BaseThree() {
        System.out.println("BaseThree构造器");
    }

    {
        System.out.println("BaseThree初始化块");
        System.out.println();
    }

    static {
        System.out.println("BaseThree静态初始化块");

    }
}

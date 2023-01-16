package github.zayn.staticdemo.demo2;

/**
 * @ClassName BaseOne
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2023/1/12 4:13 PM
 **/
public class BaseOne {
    public BaseOne() {
        System.out.println("BaseOne构造器");
    }

    {
        System.out.println("BaseOne初始化块");
        System.out.println();
    }

    static {
        System.out.println("BaseOne静态初始化块");

    }
}

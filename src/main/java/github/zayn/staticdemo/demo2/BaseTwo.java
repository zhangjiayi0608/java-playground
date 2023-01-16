package github.zayn.staticdemo.demo2;

/**
 * @ClassName BaseOne
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2023/1/12 4:13 PM
 **/
public class BaseTwo extends BaseOne {
    public BaseTwo() {
        System.out.println("BaseTwo构造器");
    }

    {
        System.out.println("BaseTwo初始化块");
        System.out.println();
    }

    static {
        System.out.println("BaseTwo静态初始化块");

    }
}

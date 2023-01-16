package github.zayn.staticdemo.demo1;

/**
 * @EnumName EntranceMachineState
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午8:37
 **/
public class StaticDemo {
    private static int value = 666;

    public static void main(String[] args) throws Exception {
        new StaticDemo().method();
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:MagicNumber"})
    private void method() {
        int value = 123;
        System.out.println(this.value);
    }
}

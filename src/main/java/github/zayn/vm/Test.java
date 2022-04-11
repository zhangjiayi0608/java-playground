package github.zayn.vm;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/25 下午2:49
 **/
public class Test {
    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        int i = 1;
        try {
            System.out.println("执行try：" + i);
            i++;
            System.out.println("执行try结束：" + i);
        } catch (Exception e) {
            System.out.println("报错");
        } finally {
            i++;
            System.out.println("执行finally结束：" + i);
        }
    }


}


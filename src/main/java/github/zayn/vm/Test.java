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
            throw new Exception("报错拉！");
//            System.out.println("执行try：" + i);
//            i++;
//            System.out.println("执行try结束：" + i);
        } catch (Exception e) {
            System.out.println("报错");
        } finally {
            i++;
            System.out.println("执行finally结束：" + i);
        }
    }

//    public static void main(String[] args) {
//        double v = (double) 50 * 100 / 400;
//        int ceil = (int) Math.ceil((double) (50 * 100 / 400));
//        System.out.println(ceil);
//    }


}


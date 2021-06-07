package github.zayn.vm;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @ClassName UnSafeTest
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/12 上午11:21
 **/
@SuppressWarnings("checkstyle:StaticVariableName")
public class UnSafeTest {
    private static  long INDEX;
    private static long MOVING;
    private static Unsafe U;

    static {
        try {

            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
            Class<?> k = UnSafeTest.class;
            INDEX = U.objectFieldOffset(k.getDeclaredField("index"));
            MOVING = U.objectFieldOffset(k.getDeclaredField("moving"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private volatile int index;
    private volatile int moving = 0;

    public static void main(String[] args) {
        UnSafeTest test = new UnSafeTest();
    }
}

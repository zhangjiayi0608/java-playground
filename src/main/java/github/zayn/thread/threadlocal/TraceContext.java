package github.zayn.thread.threadlocal;

/**
 * @ClassName TraceContext
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/24 下午3:35
 **/
public class TraceContext {
    private static final ThreadLocal<Object> CONTEXT = new ThreadLocal<>();

    public static Object getContext() {
        return CONTEXT.get();
    }
    public static void setContext(Object obj) {
        CONTEXT.set(obj);
    }
    public static void removeContext() {
        CONTEXT.remove();
    }
}

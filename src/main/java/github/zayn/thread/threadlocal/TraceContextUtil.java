package github.zayn.thread.threadlocal;

/**
 * @ClassName ThreadLocalUtil
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/24 下午3:11
 **/
public class TraceContextUtil {
    /**
     * 设置调用线程的上下文到当前执行线程中,并返回执行线程之前的上下文
     *
     * @param context
     * @return
     */
    public static Object backupAndSet(Object context) {
        Object backupContext = TraceContext.getContext();
        TraceContext.setContext(context);
        return backupContext;
    }

    /**
     * 恢复执行线程的上下文
     * @param backup
     */
    public static void restoreBackup(Object backup) {
        TraceContext.setContext(backup);
    }

}

package github.zayn.thread.threadlocal;

/**
 * @ClassName TraceRunnable
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/24 下午3:50
 **/
public class TraceRunnable implements Runnable {
    //在初始化TraceRunnable时会获取调用线程的上下文
    private final Object context = TraceContext.getContext();

    private final Runnable runnable;

    public TraceRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        Object backup = TraceContextUtil.backupAndSet(this.context);
        try {
            this.runnable.run();
        } finally {
            TraceContextUtil.restoreBackup(backup);
        }
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public static TraceRunnable get(Runnable runnable) {
        if (runnable == null) {
            return null;
        } else {
            return runnable instanceof TraceRunnable ? (TraceRunnable) runnable : new TraceRunnable(runnable);
        }
    }

}

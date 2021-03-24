package github.zayn.thread.threadlocal;

import java.util.concurrent.Callable;

/**
 * @ClassName TraceCallable
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/24 下午4:41
 **/
public class TraceCallable<V> implements Callable<V> {
    private final Object context = TraceContext.getContext();

    private final Callable<V> callable;

    public TraceCallable(Callable<V> callable) {
        this.callable = callable;
    }

    @Override
    public V call() throws Exception {
        Object backup = TraceContextUtil.backupAndSet(context);
        V result;
        try {
            result = this.callable.call();
        } finally {
            TraceContextUtil.restoreBackup(backup);
        }
        return result;
    }

    public Callable<V> getCallable() {
        return this.callable;
    }

    //返回TraceCallable实例
    public static <T> TraceCallable<T> get(Callable<T> callable) {
        if (callable == null) {
            return null;
        } else {
            return callable instanceof TraceCallable ? (TraceCallable) callable : new TraceCallable(callable);
        }
    }
}

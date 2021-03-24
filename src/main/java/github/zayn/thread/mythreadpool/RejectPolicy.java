package github.zayn.thread.mythreadpool;

/**
 * @author zhangjiayi
 */
public interface RejectPolicy {
    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);

}

package github.zayn.treadtraining.mythreadpool;

/**
 * @author zhangjiayi
 */
public interface RejectPolicy {
    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);

}

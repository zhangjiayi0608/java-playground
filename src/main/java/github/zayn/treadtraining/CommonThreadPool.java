package github.zayn.treadtraining;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommonThreadPool {
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
            10,
            10000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardOldestPolicy());


    public static void execute(Runnable command) {
        threadPoolExecutor.execute(command);
    }

    /**
     * 子线程执行结束future.get()返回null，若没有执行完毕，主线程将会阻塞等待
     */
    public static Future submit(Runnable command) {
        return threadPoolExecutor.submit(command);
    }

    /**
     * 子线程中的返回值可以从返回的future中获取：future.get();
     */
    public static Future submit(Callable command) {
        return threadPoolExecutor.submit(command);
    }
}

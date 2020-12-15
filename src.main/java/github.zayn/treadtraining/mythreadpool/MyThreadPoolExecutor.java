package github.zayn.treadtraining.mythreadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjiayi
 */
public class MyThreadPoolExecutor implements Executor {
    private String name;
    private int coreSize;
    private int maxSize;
    private BlockingQueue<Runnable> blockingQueue;
    private RejectPolicy rejectPolicy;


    private AtomicInteger sequence = new AtomicInteger(0);


    //记录线程数
    private AtomicInteger runningCount = new AtomicInteger(0);

    public MyThreadPoolExecutor(String name, int coreSize, int maxSize, BlockingQueue blockingQueue, RejectPolicy rejectPolicy) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.blockingQueue = blockingQueue;
        this.rejectPolicy = rejectPolicy;
    }

    @Override
    public void execute(Runnable command) {
        int count = runningCount.get();
        if (count < coreSize) {
            if (addWorker(command, true)) {
                return;
            }
            //失败进入下面逻辑
        }
        if (blockingQueue.offer(command)) {
            //成功插入队列
        } else {
            if (!addWorker(command, false)) {
                //拒绝策略
                rejectPolicy.reject(command, this);
            }
        }

    }

    private boolean addWorker(Runnable newTask, boolean isCore) {
        for (; ; ) {
            int max = isCore ? coreSize : maxSize;
            int count = runningCount.get();
            if (count > max) {
                return false;
            }
            if (runningCount.compareAndSet(count, count + 1)) {
                String threadName = (isCore ? "_core" : "_max") + name + sequence.incrementAndGet();
                new Thread(() -> {
                    System.out.println("threadName :" + Thread.currentThread().getName());
                    Runnable task = newTask;
                    while (task != null || (task = getTask()) != null) {
                        try {
                            task.run();
                        } finally {
                            task = null;
                        }
                    }
                }, threadName).start();
                break;
            }
        }
        return true;
    }

    private Runnable getTask() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            runningCount.decrementAndGet();
            return null;
        }
    }

    public static void main(String[] args) {
        Executor myThreadPoolExecutor = new MyThreadPoolExecutor("test", 5, 10, new ArrayBlockingQueue(10), new DiscardRejectPolicy());
        AtomicInteger num = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            myThreadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("running:" + System.currentTimeMillis() + ":" + num.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}

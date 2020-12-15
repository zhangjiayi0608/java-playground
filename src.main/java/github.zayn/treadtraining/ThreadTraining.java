package github.zayn.treadtraining;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ThreadTraining {

    private Thread taskMonitorThread;

    private int i = 1;

    private static final Semaphore semaphore = new Semaphore(2);


    public void threadTraining() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardOldestPolicy());
        Future future = threadPoolExecutor.submit(() -> {
            try {
                count1(i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("计数器1结束 线程：" + currentThread().getName() + "关闭");
                threadPoolExecutor.shutdown();
            }

        });
        Future future2 = threadPoolExecutor.submit(() -> {
            try {
                count2(i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("计数器2结束 线程：" + currentThread().getName() + "关闭");
                threadPoolExecutor.shutdown();
            }

        });
        try {
            future.get();
            future2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("计数器3打点：" + i + ",当前线程：" + currentThread().getName());
        }
    }


    public void threadTraining1() {
        Thread t1 = new Thread(() -> count1(i));
        Thread t2 = new Thread(() -> count2(i));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int k = 0; k < 1000; k++) {
            System.out.println("计数器3打点：" + k + ",当前线程：" + currentThread().getName());
        }
    }


    public void threadTraining3() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int j = 0; j < 10; j++) {
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println("线程名：" + currentThread().getName() + "开始");
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("线程结束 线程：" + currentThread().getName() + "关闭");
                    threadPoolExecutor.shutdown();
                }
            });
        }
    }


    private void count1(int i) {
        while (i < 10) {
            System.out.println("计数器1打点：" + i + "当前线程：" + currentThread().getName());
            i++;
        }
    }

    private synchronized void count2(int i) {
        while (i < 10) {
            System.out.println("计数器2打点：" + i + "当前线程：" + currentThread().getName());
            i++;
        }
    }

    public static void main(String[] args) {
        ThreadTraining threadTraining = new ThreadTraining();
        threadTraining.threadTraining();
    }


}



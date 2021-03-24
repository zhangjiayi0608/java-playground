package github.zayn.thread.producerandconsumer;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁的生产者消费者模型
 */
public class ProducerAndConsumer2 {
    private static final int FULL = 10;
    private static int count = 0;
    private Lock lock = new ReentrantLock();
    //创建两个条件变量，一个为缓冲区非满，一个为缓冲区非空
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    class Producer implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                while (count == FULL) {
                    notFull.await();
                }
                count++;
                System.out.println(Thread.currentThread().getName() + "生产者正在生产，数量为" + count);
                //唤醒消费者
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }


    class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                while (count == 0) {
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者者正在消费，数量为" + count);
                    notFull.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumer2 test1 = new ProducerAndConsumer2();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
    }

}

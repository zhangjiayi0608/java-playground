package github.zayn.treadtraining.producerandconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue
 */
public class ProducerAndConsumer3 {
    private static Integer count = 0;
    final BlockingQueue blockingQueue = new ArrayBlockingQueue<>(10);

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    blockingQueue.put(1);
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    blockingQueue.take();
                    count--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumer3 producerAndConsumer3 = new ProducerAndConsumer3();


        new Thread(producerAndConsumer3.new Consumer()).start();
        // 开启 consumer线程 中队列中消费消息
        new Thread(producerAndConsumer3.new Producer()).start();

    }
}

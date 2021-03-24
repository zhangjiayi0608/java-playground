package github.zayn.thread.producerandconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者 消费者模型
 * wait（）和notify（）方法的实现
 */
public class ProducerAndConsumer1 {
    private static final Integer FULL = 10;
    @SuppressWarnings("checkstyle:StaticVariableName")
    private static String LOCK = "lock";
    private static Integer count = 0;

    private List<String> warehouse = new ArrayList<>();

    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count.equals(FULL)) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    warehouse.add("货物" + i);
                    count++;
                    System.out.println(Thread.currentThread()
                            .getName() + "生产者生产，目前总共有" + count + "，货柜有：" + (warehouse.isEmpty() ? null : warehouse.toString()));
                    LOCK.notifyAll();

                }
            }

        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            //不处理
                        }
                    }
                    warehouse.get(0);
                    warehouse.remove(0);
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count + "，货柜有：" + (warehouse.isEmpty() ? null : warehouse.toString()));
                    LOCK.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumer1 test1 = new ProducerAndConsumer1();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
    }

}
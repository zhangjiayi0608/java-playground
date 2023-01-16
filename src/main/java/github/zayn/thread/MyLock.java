package github.zayn.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyLock {
    private static AtomicInteger i = new AtomicInteger();

    @SuppressWarnings({"checkstyle:EmptyBlock", "checkstyle:EmptyForIteratorPad"})
    public static void main(String[] args) {
        while (true) {
            int current = i.get();
            int target = 4;
            if (!i.compareAndSet(current, target)) {
            }
        }
        //System.out.println(i.compareAndSet(0,2));
    }

}

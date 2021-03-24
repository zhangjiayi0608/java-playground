package github.zayn.vm;

/**
 * 多线程导致栈内存溢出
 */
public class JavaVMStackOOM {

    @SuppressWarnings("checkstyle:EmptyBlock")
    private void dontStop() {
        System.out.println(Thread.currentThread().getName());
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }

}

package cyclic_barrier;

public class CyclicBarrier {
    int parties;
    Runnable afterAction;

    int awaited = 0;

    public CyclicBarrier(int parties) {
        this.parties = parties;
    }

    public CyclicBarrier(int parties, Runnable afterAction) {
        this.parties = parties;
        this.afterAction = afterAction;
    }

    public synchronized void await() throws InterruptedException {
        awaited += 1;
        if (awaited >= parties) {
            awaited = 0;
            if (afterAction != null) {
                afterAction.run();
            }
            this.notifyAll();
        } else {
            this.wait();
        }
    }
}

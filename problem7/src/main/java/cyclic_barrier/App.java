package cyclic_barrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        int n = 2;
        ExecutorService exs = Executors.newFixedThreadPool(n);
        CyclicBarrier barrier = new CyclicBarrier(n, () -> {
            System.out.printf("Cycle\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < n; i += 1) {
            int k = i;
            exs.submit(() -> {
                while (!Thread.interrupted()) {
                    System.out.println(k);
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}

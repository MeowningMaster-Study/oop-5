package cyclic_barrier;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {
    public static void main(String[] args) {
        int n = 2;
        ExecutorService exs = Executors.newFixedThreadPool(n);
        CyclicBarrier barrier = new CyclicBarrier(n, () -> {
            System.out.printf("Cycle\n");
        });

        for (int i = 0; i < n; i += 1) {
            exs.submit(() -> {
                while (!Thread.interrupted()) {
                    System.out.println(i);
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

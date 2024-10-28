package coretask.concurrency.synchronizeds;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ComplexTaskExecutor {
    private final ExecutorService executorService;
    private final CyclicBarrier cyclicBarrier;

    public ComplexTaskExecutor(int count) {
        executorService = Executors.newFixedThreadPool(count);
        cyclicBarrier = new CyclicBarrier(count, () -> System.out.println("Все задачи завершены"));
    }

    public void executeTasks(int numberOfTusks) {
        AtomicInteger number = new AtomicInteger(0);
        for (int i = 0; i < numberOfTusks; i++) {
            executorService.submit(() -> {
                        ComplexTask task = new ComplexTask(number.getAndIncrement());
                        try {
                            task.execute();
                            cyclicBarrier.await();
                        } catch (InterruptedException | BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        executorService.shutdown();
    }
}
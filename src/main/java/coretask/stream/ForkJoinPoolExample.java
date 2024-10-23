package coretask.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FactorialTask extends RecursiveTask<Integer> {
    private static final int FINISH = 3;
    private final int number;
    private final int start;
    public FactorialTask(int number) {
        this(1, number);
    }
    private FactorialTask(int start, int number) {
        this.start = start;
        this.number = number;
    }

    @Override
    protected Integer compute() {
        if ((number - start) <= FINISH) {
            return factorial(start, number);
        } else {
            int mid = (start + number) / 2;
            FactorialTask factorialLeftTask = new FactorialTask(start, mid);
            FactorialTask factorialRightTask = new FactorialTask(mid + 1, number);
            factorialLeftTask.fork();
            Integer first = factorialRightTask.compute();
            Integer second = factorialLeftTask.join();
            return first * second;
        }
    }

    private Integer factorial(int start, int end) {
        int result = 1;
        for (int i = start; i <= end; i++) {
            result *= i;
        }
        return result;
    }
}
public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}

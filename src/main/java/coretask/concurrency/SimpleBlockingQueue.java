package coretask.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> implements BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList();
    private final int sizeQueue;

    public SimpleBlockingQueue (int sizeQueue) {
        this.sizeQueue = sizeQueue;
    }

    @Override
    public synchronized void enqueue(T element) throws InterruptedException {
        while (queue.size() >= sizeQueue) {
            wait();
        }
        queue.offer(element);
        notifyAll();
    }

    @Override
    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T result = queue.poll();
        notifyAll();
        return result;
    }

    @Override
    public synchronized int size() {
        return queue.size();
    }
}

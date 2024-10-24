package coretask.concurrency;

public interface BlockingQueue<T> {
    void enqueue(T element) throws InterruptedException;
    T dequeue() throws InterruptedException;
    int size();
}

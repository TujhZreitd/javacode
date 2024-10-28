package coretask.concurrency.synchronizeds;

public class ComplexTask {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public void execute() {
        System.out.println("Поток " + Thread.currentThread() + " выполняет какую-то работу...");
    }
}

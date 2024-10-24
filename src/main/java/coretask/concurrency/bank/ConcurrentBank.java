package coretask.concurrency.bank;

public interface ConcurrentBank {
    BankAccount createAccount(int balance);

    void transfer(BankAccount bankAccount1, BankAccount bankAccount2, int amount);
    int getTotalBalance();
}

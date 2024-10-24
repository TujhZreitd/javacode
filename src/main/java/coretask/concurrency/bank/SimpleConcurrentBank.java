package coretask.concurrency.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleConcurrentBank implements ConcurrentBank {
    private final ConcurrentHashMap<Integer, BankAccount> bankAccounts = new ConcurrentHashMap<>();
    private static int ids = 0;

    @Override
    public synchronized BankAccount createAccount(int balance) {
        BankAccount bankAccount = new BankAccount(++ids, balance);
        bankAccounts.put(bankAccount.getId(), bankAccount);
        return bankAccount;
    }

    @Override
    public synchronized void transfer(BankAccount bankAccount1, BankAccount bankAccount2, int amount) {
        bankAccount1.withdraw(amount);
        bankAccount2.deposit(amount);
    }

    @Override
    public synchronized int getTotalBalance() {
        int result = 0;
        for (Map.Entry<Integer, BankAccount> entry : bankAccounts.entrySet()) {
            result += entry.getValue().getBalance();
        }
        return result;
    }
}

package coretask.concurrency.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class SimpleConcurrentBank implements ConcurrentBank {
    private final ConcurrentHashMap<Integer, BankAccount> bankAccounts = new ConcurrentHashMap<>();
    private final static AtomicInteger ids = new AtomicInteger(0);

    @Override
    public BankAccount createAccount(int balance) {
        BankAccount bankAccount = new BankAccount(ids.incrementAndGet(), balance);
        bankAccounts.put(bankAccount.getId(), bankAccount);
        return bankAccount;
    }

    @Override
    public void transfer(BankAccount bankAccount1, BankAccount bankAccount2, int amount) {
        Lock lock1 = bankAccount1.getLock();
        Lock lock2 = bankAccount2.getLock();

        if (bankAccount1.getId() < bankAccount2.getId()) {
            lock1.lock();
            try {
                lock2.lock();
                try {
                    bankAccount1.withdraw(amount);
                    bankAccount2.deposit(amount);
                } finally {
                    lock2.unlock();
                }
            } finally {
                lock1.unlock();
            }
        } else {
            lock2.lock();
            try {
                lock1.lock();
                try {
                    bankAccount1.withdraw(amount);
                    bankAccount2.deposit(amount);
                } finally {
                    lock1.unlock();
                }
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override
    public int getTotalBalance() {
        int result = 0;
        for (Map.Entry<Integer, BankAccount> entry : bankAccounts.entrySet()) {
            result += entry.getValue().getBalance();
        }
        return result;
    }
}


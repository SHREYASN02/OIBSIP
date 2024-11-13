package amt_interface;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private double balance = 0.0;
    private List<Transaction> transactionHistory = new ArrayList<>();

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        transactionHistory.add(new Transaction("Withdraw", amount));
        return true;
    }

    public boolean transfer(Account targetAccount, double amount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

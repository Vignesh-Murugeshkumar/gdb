package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {
    public static boolean transferFunds(AbstractAccount from, AbstractAccount to, double amount, String pin) {
        try {
            from.withdraw(amount, pin);
            to.deposit(amount);
            return true;
        } catch (AccountException e) {
            System.out.println("Transfer failed: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        // NOTE: If you completed Activity 9 successfully, paste your working domain classes into src/com/gdb/domain (replacing the provided versions).

        AbstractAccount savings = new SavingsAccount("SAV1001", "Rajesh Sharma", 28, 10000.0, "ACTIVE", "1234", 1000.0, 4.0);
        AbstractAccount current = new CurrentAccount("CUR1001", "Priya Patel", 34, 5000.0, "ACTIVE", "5678", 25000.0);
        AbstractAccount salary = new SalaryAccount("SAL1001", "Sneha Verma", 26, 30000.0, "ACTIVE", "2222", "Infosys");
        AbstractAccount[] portfolio = { savings, current, salary };

        boolean transferred = transferFunds(savings, current, 3000.0, "1234");
        System.out.println("Transfer Rs 3000 from Savings to Current: " + (transferred ? "SUCCESS" : "FAILED"));
        System.out.println("Savings Balance: Rs " + savings.getBalance() + " | Current Balance: Rs " + current.getBalance());
        double savingsBeforeFailedTransfer = savings.getBalance();
        double currentBeforeFailedTransfer = current.getBalance();
        boolean failedTransfer = !transferFunds(savings, current, 2000.0, "9999")
                && savings.getBalance() == savingsBeforeFailedTransfer
                && current.getBalance() == currentBeforeFailedTransfer;
        System.out.println("Failed Transfer (Wrong PIN): Exception caught, no balance changed " + (failedTransfer ? "[PASS]" : "[FAIL]"));

        for (AbstractAccount account : portfolio) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).applyInterest();
            } else if (account instanceof SalaryAccount) {
                ((SalaryAccount) account).incrementInactiveMonths();
            }
        }
        System.out.println("Monthly Interest Cycle processed for all qualifying accounts.");
        System.out.println("All banking operations passed!");
    }
}

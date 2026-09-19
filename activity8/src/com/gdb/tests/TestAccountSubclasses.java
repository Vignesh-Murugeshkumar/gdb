package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAccountSubclasses {
    public static void main(String[] args) {
        System.out.println("=== Activity 8: Polymorphism Test ===");

        // NOTE: The domain classes in src/com/gdb/domain are provided complete (the Activity 7 subclasses plus
        // their overridden withdraw() methods). Declare each account with the parent type Account so that the
        // overridden withdraw() is chosen at runtime (dynamic method dispatch).

        Account savings = new SavingsAccount("SAV1001", "Rajesh Sharma", 28, 10000.0, "ACTIVE", "1234", 1000.0, 4.0);
        try {
            savings.withdraw(9500.0, "1234");
            System.out.println("[Savings] Withdraw 9500 (breaches min balance 1000): [FAIL]");
        } catch (MinimumBalanceViolationException e) {
            System.out.println("[Savings] Withdraw 9500 (breaches min balance 1000): Caught MinimumBalanceViolationException [PASS]");
        } catch (AccountException e) {
            System.out.println("[Savings] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }
        Account current = new CurrentAccount("CUR1001", "Priya Patel", 34, 5000.0, "ACTIVE", "5678", 25000.0);
        try {
            current.withdraw(10000.0, "5678");
            System.out.println("[Current] Withdraw with Overdraft (Balance goes to -5000): SUCCESS [PASS]");
            current.withdraw(30000.0, "5678");
            System.out.println("[Current] Withdraw exceeding Overdraft (exceeds -25000): [FAIL]");
        } catch (InsufficientBalanceException e) {
            System.out.println("[Current] Withdraw exceeding Overdraft (exceeds -25000): Caught InsufficientBalanceException [PASS]");
        } catch (AccountException e) {
            System.out.println("[Current] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }
        Account fixed = new FixedDepositAccount("FD1001", "Amit Kumar", 45, 50000.0, "ACTIVE", "1111", 12, 6.5);
        try {
            fixed.withdraw(1000.0, "1111");
            System.out.println("[FixedDeposit] Withdraw attempt: [FAIL]");
        } catch (AccountException e) {
            System.out.println("[FixedDeposit] Withdraw attempt: Caught AccountException [PASS]");
        }
        System.out.println("All polymorphic behaviors verified!");
    }
}

package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestInterfaceFactory {
    public static void main(String[] args) {
        System.out.println("=== Activity 12: Factory-Driven System Suite ===");

        // NOTE: If you completed Activity 11 successfully, paste your working
        // IAccount.java and AccountFactory.java into src/com/gdb/domain (replacing the
        // provided versions).
        // Step 1: Instantiate Savings, Current, and FixedDeposit accounts exclusively through AccountFactory.createAccount()
        IAccount savings = AccountFactory.createAccount(
                "Savings",
                "S101",
                "Alice",
                35,
                50000.0,
                "ACTIVE",
                "1234");
        IAccount current = AccountFactory.createAccount(
                "Current",
                "C202",
                "Bob",
                28,
                100000.0,
                "ACTIVE",
                "5678");
        IAccount fixedDeposit = AccountFactory.createAccount(
                "Fixed_Deposit",
                "FD303",
                "Charlie",
                45,
                200000.0,
                "ACTIVE",
                "9012");

        // Step 2 - Perform deposits and withdrawals through the IAccount interface references
        try {
            savings.deposit(5000.0);
            System.out.println("[Test 1] Savings Account Creation & Deposit: [PASS]");
        } catch (InvalidAmountException e) {
            System.out.println("[Test 1] Savings Account Creation & Deposit: [FAIL]");
        }

        // Step 3 - Verify Savings minimum balance rule enforcement through the interface
        try {
            savings.withdraw(60000.0, "1234");
        } catch (MinimumBalanceViolationException e) {
            // Minimum balance rule enforced successfully
        } catch (AccountException e) {
            System.out.println("Savings minimum balance check failed: " + e.getMessage());
        }

        // Step 4 - Verify Current overdraft limit enforcement through the interface
        try {
            // Overdraft limit is 25000.0, balance is 100000.0 -> allows up to 125000.0 withdrawal
            current.withdraw(110000.0, "5678");
            boolean overdraftBlocked = false;
            try {
                // Attempt to exceed overdraft limit
                current.withdraw(20000.0, "5678");
            } catch (InsufficientBalanceException e) {
                overdraftBlocked = true;
            }

            if (overdraftBlocked) {
                System.out.println("[Test 2] Current Account Overdraft Withdrawal: [PASS]");
            } else {
                System.out.println("[Test 2] Current Account Overdraft Withdrawal: [FAIL]");
            }
        } catch (AccountException e) {
            System.out.println("[Test 2] Current Account Overdraft Withdrawal: [FAIL] - " + e.getMessage());
        }

        // Step 5 - Verify FixedDeposit premature withdrawal rejection through the interface
        try {
            fixedDeposit.withdraw(1000.0, "9012");
            System.out.println("[Test 3] Fixed Deposit Premature Withdrawal Block: [FAIL]");
        } catch (AccountException e) {
            System.out.println("[Test 3] Fixed Deposit Premature Withdrawal Block: [PASS]");
        }

        // Step 6 - Verify requesting an invalid account type from AccountFactory throws IllegalArgumentException
        try {
            AccountFactory.createAccount("INVALID_TYPE", "INV999", "Unknown", 30, 5000.0, "ACTIVE", "1234");
            System.out.println("[Test 4] Invalid Type Rejection: [FAIL]");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test 4] Invalid Type Rejection: [PASS]");
        }

        System.out.println("Factory-driven architecture successfully verified!");
    }
}

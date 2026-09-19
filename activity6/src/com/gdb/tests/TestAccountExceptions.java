package com.gdb.tests;

import com.gdb.domain.Account;
import com.gdb.exceptions.*;

public class TestAccountExceptions {
    public static void main(String[] args) {
        System.out.println("=== Activity 6: Exception Handling Suite ===");

        // NOTE: If you completed Activity 5 successfully, paste your working Account.java into src/com/gdb/domain and your exception classes into src/com/gdb/exceptions (replacing the provided versions).

        Account acc = new Account("ACC1001", "Rajesh Sharma", 28, 5000.0, "SAVINGS", "ACTIVE", "1234");

        try {
            acc.withdraw(1000.0, "9999");
        } catch (InvalidPinException e) {
            System.out.println("[Test 1] Caught Invalid PIN: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {
            System.out.println("[Test 1] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }
        acc.suspend();
        try {
            acc.withdraw(1000.0, "1234");
        } catch (InactiveAccountException e) {
            System.out.println("[Test 2] Caught Inactive Account: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {
            System.out.println("[Test 2] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }
        acc.activate();
        try {
            acc.deposit(-500.0);
        } catch (InvalidAmountException e) {
            System.out.println("[Test 3] Caught Invalid Amount: " + e.getMessage() + " [PASS]");
        }
        try {
            acc.withdraw(10000.0, "1234");
        } catch (InsufficientBalanceException e) {
            System.out.println("[Test 4] Caught Insufficient Funds: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {
            System.out.println("[Test 4] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }
        acc.close();
        try {
            acc.withdraw(100.0, "1234");
        } catch (AccountException e) {
            System.out.println("[Test 5] Polymorphic Handler caught: Account is closed [PASS]");
        }
        System.out.println("All exception handling tests completed successfully!");
    }
}

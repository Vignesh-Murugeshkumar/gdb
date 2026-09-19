package com.gdb.tests;

import com.gdb.domain.Account;

public class TestAccount {
    public static void main(String[] args) {
        System.out.println("=== Activity 2: Test Account Suite ===");

        // NOTE: If you completed Activity 1 successfully, paste your working Account.java code into com.gdb.domain.

        Account acc = new Account("ACC1001", "Rajesh Sharma", 28, 5000.0, "SAVINGS", "ACTIVE");

        boolean t1 = acc.getBalance() == 5000.0;
        System.out.println("Test 1 (Initial Balance 5000.0): " + (t1 ? "[PASS]" : "[FAIL]"));
        boolean t2 = acc.deposit(2000.0) && acc.getBalance() == 7000.0;
        System.out.println("Test 2 (Deposit 2000.0 -> Balance 7000.0): " + (t2 ? "[PASS]" : "[FAIL]"));
        boolean t3 = !acc.deposit(-500.0) && acc.getBalance() == 7000.0;
        System.out.println("Test 3 (Negative Deposit -> Rejected): " + (t3 ? "[PASS]" : "[FAIL]"));
        boolean t4 = acc.withdraw(3000.0) && acc.getBalance() == 4000.0;
        System.out.println("Test 4 (Withdraw 3000.0 -> Balance 4000.0): " + (t4 ? "[PASS]" : "[FAIL]"));
        boolean t5 = !acc.withdraw(10000.0) && acc.getBalance() == 4000.0;
        System.out.println("Test 5 (Exceeding Withdrawal -> Rejected): " + (t5 ? "[PASS]" : "[FAIL]"));
        boolean t6 = !acc.withdraw(-100.0) && acc.getBalance() == 4000.0;
        System.out.println("Test 6 (Negative Withdrawal -> Rejected): " + (t6 ? "[PASS]" : "[FAIL]"));
        System.out.println("All Account tests completed successfully!");
    }
}

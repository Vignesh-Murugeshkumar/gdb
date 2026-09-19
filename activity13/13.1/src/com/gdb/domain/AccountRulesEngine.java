package com.gdb.domain;

import java.util.HashMap;
import java.util.Map;

public class AccountRulesEngine {
    // Step 1: Define the Savings lookup tables: two private static final Map<String, Double> fields
    //   (one for minimum balance, one for interest rate) keyed by tenure bucket, filled in a static { } block:
    //     Bucket      Tenure       Min Balance   Interest Rate
    //     NEW         0 to 1 yr    10000.0       2.70
    //     STANDARD    1 to 3 yrs    7500.0       3.00
    //     PREMIUM     3 to 5 yrs    5000.0       3.50
    //     PRIVILEGE   5+ yrs        2500.0       4.00
    private static final Map<String, Double> savingsMinBalanceMap = new HashMap<>();
    private static final Map<String, Double> savingsInterestRateMap = new HashMap<>();
    static {
        savingsMinBalanceMap.put("NEW", 10000.0);
        savingsMinBalanceMap.put("STANDARD", 7500.0);
        savingsMinBalanceMap.put("PREMIUM", 5000.0);
        savingsMinBalanceMap.put("PRIVILEGE", 2500.0);

        savingsInterestRateMap.put("NEW", 2.70);
        savingsInterestRateMap.put("STANDARD", 3.00);
        savingsInterestRateMap.put("PREMIUM", 3.50);
        savingsInterestRateMap.put("PRIVILEGE", 4.00);
    }

    public static String getSavingsBucket(int tenureYears) {
        if (tenureYears >= 5) return "PRIVILEGE";
        if (tenureYears >= 3) return "PREMIUM";
        if (tenureYears >= 1) return "STANDARD";
        return "NEW";
    }

    public static double getSavingsMinBalance(int tenureYears) {
        return savingsMinBalanceMap.getOrDefault(getSavingsBucket(tenureYears), 10000.0);
    }

    public static double getSavingsInterestRate(int tenureYears) {
        return savingsInterestRateMap.getOrDefault(getSavingsBucket(tenureYears), 2.70);
    }

    public static double getCurrentOverdraftLimit(double monthlyTurnover) {
        return Math.max(25000.0, monthlyTurnover * 2.5);
    }

    public static double getFDInterestRate(int months) {
        if (months >= 36) return 7.50;
        if (months >= 12) return 6.50;
        return 5.00;
    }
}

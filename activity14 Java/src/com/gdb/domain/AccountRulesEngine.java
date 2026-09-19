package com.gdb.domain;

public class AccountRulesEngine {
    private static AccountRulesPropertiesLoader savingsLoader =
        new AccountRulesPropertiesLoader("src/main/resources/config/rules/savings.properties");
    private static AccountRulesPropertiesLoader currentLoader =
        new AccountRulesPropertiesLoader("src/main/resources/config/rules/current.properties");
    private static AccountRulesPropertiesLoader fdLoader =
        new AccountRulesPropertiesLoader("src/main/resources/config/rules/fixeddeposit.properties");
    private static AccountRulesPropertiesLoader salaryLoader =
        new AccountRulesPropertiesLoader("src/main/resources/config/rules/salary.properties");

    // Bucket names are lowercase so they match the keys in savings.properties (e.g. min.balance.new).
    public static String getSavingsBucket(int tenureYears) {
        if (tenureYears >= 5) return "privilege";
        if (tenureYears >= 3) return "premium";
        if (tenureYears >= 1) return "standard";
        return "new";
    }

    public static double getSavingsMinBalance(int tenureYears) {
        return savingsLoader.getDouble("min.balance." + getSavingsBucket(tenureYears), 10000.0);
    }

    public static double getSavingsInterestRate(int tenureYears) {
        return savingsLoader.getDouble("interest.rate." + getSavingsBucket(tenureYears), 2.70);
    }

    public static double getCurrentOverdraftLimit(double monthlyTurnover) {
        double minLimit = currentLoader.getDouble("overdraft.min.limit", 25000.0);
        double multiplier = currentLoader.getDouble("overdraft.multiplier", 2.5);
        return Math.max(minLimit, monthlyTurnover * multiplier);
    }

    public static double getFDInterestRate(int months) {
        if (months >= 36) return fdLoader.getDouble("interest.rate.long", 7.50);
        if (months >= 12) return fdLoader.getDouble("interest.rate.medium", 6.50);
        return fdLoader.getDouble("interest.rate.short", 5.00);
    }

    public static int getSalaryAutoDeactivateMonths() {
        return salaryLoader.getInt("auto.deactivate.months", 3);
    }

    public static double getSalaryMinimumMonthlyCredit() {
        return salaryLoader.getDouble("minimum.monthly.credit", 10000.0);
    }
}

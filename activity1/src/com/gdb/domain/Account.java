package com.gdb.domain;

public class Account {
    // TODO: Step 1 - Declare the 6 private fields:
    // - accountNumber (String)
    // - name (String)
    // - age (int)
    // - balance (double)
    // - accountType (String)
    // - status (String)
    private String accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;


    public Account(String accountNumber, String name, int age, double balance, String accountType, String status) {
        // TODO: Step 2 - Initialize instance variables with parameters
        this.accountNumber = accountNumber;
        this.name = name;       
        this.age = age;
        this.balance = balance;
        this.accountType = accountType;
        this.status = status;
    }

    public boolean deposit(double amount) {
        // TODO: Step 3 - Validate amount > 0, increase balance, and return true; return false otherwise
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        // TODO: Step 4 - Validate amount > 0 and balance >= amount, deduct from balance, and return true; return false otherwise
        if (amount > 0 && balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public void displayAccountInfo() {
        // TODO: Step 5 - Print formatted account information (AccountNumber, Name, Age, Balance, AccountType, Status)
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);    
        System.out.println("Age: " + age);
        System.out.println("Balance: Rs " + balance);
        System.out.println("Account Type: " + accountType);
        System.out.println("Status: " + status);
    }

    // TODO: Step 6 - Declare public getters and setters for all private fields
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private double balance;
    private List<Transaction> transactionHistory = new ArrayList<>();

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
            addTransaction("Deposit", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
            addTransaction("Withdrawal", -amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void addTransaction(String type, double amount) {
        Transaction transaction = new Transaction(type, amount);
        transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    // Other methods

    // performTransaction

     
}

class Transaction {
    private Date timestamp;
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.timestamp = new Date();
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return timestamp + " - " + type + ": " + amount;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;

        while (true) {
            displayMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 2:
                    performTransaction("deposit");
                    break;
                case 3:
                    performTransaction("withdraw");
                    break;
                case 4:
                    displayTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM.");
                    return;
                default:
                    System.out.println("Invalid choice.");

            }
        }
    }

    private void displayTransactionHistory() {
        List<Transaction> history = account.getTransactionHistory();
        System.out.println("Transaction History:");
        for (Transaction transaction : history) {
            System.out.println(transaction);
        }
    }

    // Other methods
    private void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    //Perform Transaction

    private void performTransaction(String transactionType) {
        System.out.print("Enter " + transactionType + " amount: ");
        double amount = scanner.nextDouble();

        if (transactionType.equals("deposit")) {
            account.deposit(amount);
        } else if (transactionType.equals("withdraw")) {
            account.withdraw(amount);
        }
    }
}

public class ATM_Interface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000); // Initial balance of 1000
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}

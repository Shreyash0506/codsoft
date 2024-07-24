import java.util.Scanner;


class BankAccount {
    private double balance;

    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    
    public double getBalance() {
        return balance;
    }

    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
          
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
    }
}

// new class ATM
public class ATM {
    private BankAccount account;

    
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display the ATM menu
    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Withdraw");

      
        System.out.println("2. Deposit");
      
        System.out.println("3. Check Balance");
      
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    
    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Please take your cash.");
        }
    }

    // Method to handle deposit
    public void deposit(double amount) {
        account.deposit(amount);
    }

    
    public void checkBalance() {
        System.out.println("Your current balance is: $" + account.getBalance());
    }

    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500); // Initialize with $500 balance
        ATM atm = new ATM(account);
        atm.start();
    }
}


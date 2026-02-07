import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class BankConsole {

    static String name;
    static long AcNo;
    static double bla;
    static boolean accountExists = false;

    static String accRegex = "[0-9]{10}";
    static String nameRegex = "[A-Za-z ]+";

    static File file = new File("account.txt");

    
    static void saveToFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.println(AcNo);
            pw.println(name);
            pw.println(bla);
            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving data");
        }
    }

    
    static void loadFromFile() {
        try {
            if (file.exists()) {
                Scanner fileReader = new Scanner(file);
                AcNo = Long.parseLong(fileReader.nextLine());
                name = fileReader.nextLine();
                bla = Double.parseDouble(fileReader.nextLine());
                accountExists = true;
                fileReader.close();
            }
        } catch (Exception e) {
            System.out.println("Error loading data");
        }
    }

    
    static void CreateAccount(Scanner sc) {

        System.out.println("Enter your Account Number:");
        AcNo = sc.nextLong();
        sc.nextLine();

        if (!Pattern.matches(accRegex, Long.toString(AcNo))) {
            System.out.println("Invalid Account Number");
            System.out.println("Account number should be in 10 numbers");
            return;
        }

        System.out.println("Enter your Name:");
        name = sc.nextLine();

        if (!Pattern.matches(nameRegex, name)) {
            System.out.println("Invalid Name");
            return;
        }

        bla = 0;
        accountExists = true;

        saveToFile();

        System.out.println("Account created successfully ");
    }
    static void BalanceCheck() {
        System.out.println("Account No: " + AcNo);
        System.out.println("Name: " + name);
        System.out.println("Current Balance: " + bla);
    }
    static void Deposit(Scanner sc) {
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();

        if (amt > 0) {
            bla += amt;
            saveToFile();
            System.out.println("Amount deposited successfully ");
        } else {
            System.out.println("Invalid amount");
        }
    }
    static void Withdrawl(Scanner sc) {
        System.out.print("Enter amount to withdraw: ");
        double wld = sc.nextDouble();

        if (wld > 0 && wld <= bla) {
            bla -= wld;
            saveToFile();
            System.out.println("Amount withdrawn successfully ");
        } else {
            System.out.println("Invalid withdrawal amount");
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadFromFile();  

        while (true) {

            System.out.println("\n----- Welcome to Idiyappam Bank -----");
            System.out.println("1. Create Account");
            System.out.println("2. Balance Check");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int a = sc.nextInt();

            switch (a) {
                case 1:
                    CreateAccount(sc);
                    break;

                case 2:
                    if (accountExists)
                        BalanceCheck();
                    else
                        System.out.println("First create an account");
                    break;

                case 3:
                    if (accountExists)
                        Deposit(sc);
                    else
                        System.out.println("First create an account");
                    break;

                case 4:
                    if (accountExists)
                        Withdrawl(sc);
                    else
                        System.out.println("First create an account");
                    break;

                case 5:
                    System.out.println("Thank you for using our bank ");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

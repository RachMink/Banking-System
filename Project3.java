import java.util.Scanner;

public class Project3 {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage in command line: java Project2 filename");
            System.exit(1);
        }
        SavingsAccount.setAnnualInterestRate(1.5);

        Bank bank = new Bank(args[0]);
        Scanner keyBoard = new Scanner(System.in);
        char choice = printMenuAndGetChoice(keyBoard);

        while (choice != 'Q') {
            // L :lookup balance
            if (choice == 'L') {
                System.out.print("Account Number?");
                int accountnumber = keyBoard.nextInt();
                if (bank.getBalance(accountnumber) != null) {
                    System.out.println(
                            "Balance for account number: " + accountnumber + " is " + bank.getBalance(accountnumber));
                } else {
                    System.out.println("ERROR: Account does not exist");
                }
            }
            // P: print info about account
            else if (choice == 'P') {
                System.out.print("Account Number?");
                int accountnumber = keyBoard.nextInt();
                if (bank.getAccountInfo(accountnumber) == null) {
                    System.out.println("ERROR: account does not exist");
                } else {
                    System.out.println(bank.getAccountInfo(accountnumber));
                }
            }
            // T: print transactions
            else if (choice == 'T') {
                System.out.print("Account Number?");
                int accountnumber = keyBoard.nextInt();
                if (bank.getAccountInfo(accountnumber) == null) {
                    System.out.println("ERROR: account does not exist");
                } else {
                    bank.printArrayListTransactions(accountnumber);
                }
            }
            // D: deposit money
            else if (choice == 'D') {
                System.out.print("Account Number?");
                int accountnumber = keyBoard.nextInt();
                if (bank.contains(accountnumber)) {
                    System.out.print("Amount Depositing?");
                    MonetaryValue depositAmount = MonetaryValue.read(keyBoard);
                    bank.deposit(accountnumber, depositAmount);
                } else {
                    System.out.println("ERROR: account does not exist");
                }
            }
            // W: withdraw money
            else if (choice == 'W') {
                System.out.print("Account Number?");
                int accountnumber = keyBoard.nextInt();

                if (bank.contains(accountnumber)) {
                    System.out.print("Amount Withdrawing?");
                    MonetaryValue withdrawAmount = MonetaryValue.read(keyBoard);
                    try {
                        bank.withdraw(accountnumber, withdrawAmount);
                    } catch (InsufficientFundException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else {
                    System.out.println("ERROR: account does not exist");
                }
            }
            // A: add account
            else if (choice == 'A') {
                try {
                    System.out.println("Account Number? ");
                    int accountNumber = keyBoard.nextInt();
                    if (bank.contains(accountNumber)) {
                        System.out.println("Sorry, this account already exists");
                    } else {
                        System.out.print("First and last name? ");
                        Name name = Name.read(keyBoard);
                        System.out.print("Balance?");
                        MonetaryValue balance = MonetaryValue.read(keyBoard);
                        System.out.print("Account type? (C for checking, S for savings)");
                        char accountType = keyBoard.next().toUpperCase().charAt(0);

                        if (accountType == 'C') {
                            System.out.println("Overdraft Limit?");
                            MonetaryValue overDraftLimit = MonetaryValue.read(keyBoard);

                            CheckingAccount newAccount = new CheckingAccount(accountNumber, name, balance,
                                    overDraftLimit);
                            if (bank.add(newAccount)) {
                                System.out.println("Account successfully added");
                            }
                        } else if (accountType == 'S') {
                            SavingsAccount newAccount = new SavingsAccount(accountNumber, name, balance);
                            if (bank.add(newAccount)) {
                                System.out.println("Account successfully added");
                            }
                        } else {
                            System.out.println("Invalid account type");
                        }
                    }
                } catch (DuplicateAccountNumberException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            // R: remove account
            else if (choice == 'R') {
                System.out.print("Account number?");
                int accountNumber = keyBoard.nextInt();

                if (bank.remove(accountNumber)) {
                    System.out.println("Account was successfully removed");
                } else {
                    System.out.println("Account still remains");
                }
            } else {
                System.out.println("Choice is invalid, please try again");
            }
            choice = printMenuAndGetChoice(keyBoard);
        }
        keyBoard.close();
    }

    public static char printMenuAndGetChoice(Scanner sc) {
        System.out.println();
        System.out.println("To lookup a balance: L");
        System.out.println("To print information about an account: P");
        System.out.println("To print transactions for an account: T");
        System.out.println("To deposit money: D");
        System.out.println("To withdraw money: W");
        System.out.println("To add an account: A");
        System.out.println("To remove an account: R");
        System.out.println("To quit: Q");
        System.out.print("Enter a character (uppercase or lowercase): ");

        char choice = sc.next().toUpperCase().charAt(0);

        System.out.println();
        return choice;
    }
}

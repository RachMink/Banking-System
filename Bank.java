import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Bank {

    private HashMap<Integer, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Bank(String filename) throws FileNotFoundException {
        accounts = new HashMap<>();
        Scanner sc = new Scanner(new File(filename));

        while (sc.hasNextLine()) {
            try {
                char type = sc.next().charAt(0);

                BankAccount ba;
                if (type == 'S') {
                    ba = SavingsAccount.read(sc);
                } else {
                    ba = CheckingAccount.read(sc);
                }
                if (accounts.containsKey(ba.getAccountNumber())) {
                    throw new DuplicateAccountNumberException(ba.getAccountNumber());
                }
                accounts.put(ba.getAccountNumber(), ba);

            } catch (InputMismatchException ex) {
                System.out.println("Oops, input does not match proper input format");
                sc.nextLine();
            } catch (NegativeBalanceException ex) {
                System.out.println(ex.getMessage());
                sc.nextLine();
            } catch (DuplicateAccountNumberException ex) {
                System.out.println(ex.getMessage());
                sc.nextLine();
            }
        }
        sc.close();
    }

    public boolean contains(int accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public MonetaryValue getBalance(int accountNumber) {
        if (contains(accountNumber)) {
            return accounts.get(accountNumber).getBalance();
        }
        return null;
    }

    public String getAccountInfo(int accountNumber) {
        if (contains(accountNumber)) {

            String result = accounts.get(accountNumber).toString();

            if (accounts.get(accountNumber) instanceof SavingsAccount) {
                SavingsAccount newSavings = (SavingsAccount) accounts.get(accountNumber);
                return result + "\n" + "Monthly Interest:" + newSavings.getMonthlyInterest().toString();
            } else if (accounts.get(accountNumber) instanceof CheckingAccount) {
                return result + "\nMonthly Interest: " + new MonetaryValue();// question: can we get rid of savings acoount tostring?
            }
            return accounts.get(accountNumber).toString();
        }
        return null;
    }

    public boolean deposit(int accountNumber, MonetaryValue amount) {
        if (contains(accountNumber)) {
            if (amount.isNegative()) {
                System.out.println("ERROR: cannot deposit negative amount of money.");
                return false;
            } else {
                accounts.get(accountNumber).deposit(amount);
                System.out.println("Deposit was successful. Balance of account is now: "
                        + accounts.get(accountNumber).getBalance());
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(int accountNumber, MonetaryValue amount) throws InsufficientFundException {
        if (contains(accountNumber) && accounts.get(accountNumber).withdraw(amount)) {
            System.out.println(
                    "Withdrawl was successful! Balance of account is now: " + accounts.get(accountNumber).getBalance());
            return true;
        }
        return false;
    }

    public boolean add(BankAccount account) throws DuplicateAccountNumberException {
        if (contains(account.getAccountNumber())) {
            throw new DuplicateAccountNumberException(account.getAccountNumber());
        }
        accounts.put(account.getAccountNumber(), account);

        return true;
    }

    public boolean remove(int accountnumber) {
        if (!contains(accountnumber)) {
            return false;
        }
        accounts.remove(accountnumber);
        return true;
    }

    public void printArrayListTransactions(int accountNumber) {
        if (!contains(accountNumber)) {
            System.out.println("oops, this account number doesn't exist");
        } else {
            ArrayList<Transaction> arrListTransaction = accounts.get(accountNumber).getArrayListTransaction();//question: why do we make another ArrayList refference variable?
            System.out.println("All transactions for account number " + accountNumber);
            for (Transaction t : arrListTransaction) {
                System.out.println(t.toString());
            }

        }
    }
}

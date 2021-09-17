import java.util.ArrayList;

public abstract class BankAccount implements Comparable<BankAccount> {
    private int accountNumber;
    private Name name;
    protected MonetaryValue balance;
    private ArrayList<Transaction> arrListTransaction;

    public BankAccount() {
        this(0, new Name(), new MonetaryValue());
    }

    public BankAccount(int accountNumber, Name name, MonetaryValue balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        arrListTransaction = new ArrayList<>();
        Transaction t = new Transaction("Create new Account", new MonetaryValue(0), new MonetaryValue(balance), true);
        arrListTransaction.add(t);
    }

    public BankAccount(BankAccount oldAccount) {
        this.accountNumber = oldAccount.accountNumber;
        this.name = oldAccount.name;
        this.balance = oldAccount.balance;
        this.arrListTransaction = oldAccount.arrListTransaction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BankAccount) {
            BankAccount other = (BankAccount) obj;
            return this.accountNumber == other.accountNumber && this.balance.equals(other.balance)
                    && this.name.equals(other.name) && this.arrListTransaction.equals(other.arrListTransaction);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\n" + "Name: " + name + "\n" + "Balance:" + balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public MonetaryValue getBalance() {
        return balance;
    }

    public boolean deposit(MonetaryValue money) {

        if (money.isNegative()) {
            arrListTransaction.add(new Transaction("Deposit", money, new MonetaryValue(balance), false));
            return false;
        } else {
            balance.add(money);
            arrListTransaction.add(new Transaction("Deposit", money, new MonetaryValue(balance), true));
            return true;
        }
    }

    public boolean withdraw(MonetaryValue money) throws InsufficientFundException {
        if (money.isNegative()) {
            System.out.println("ERROR: cannot withdraw negative amount");
            arrListTransaction.add(new Transaction("Withdraw", money, new MonetaryValue(balance), false));
            return false;
        } else if (money.isGreaterThan(availableAmount())) {
            arrListTransaction.add(new Transaction("Withdraw", money, new MonetaryValue(balance), false));
            throw new InsufficientFundException(money, availableAmount());
        } else {
            balance.subtract(money);
            arrListTransaction.add(new Transaction("Withdraw", money, new MonetaryValue(balance), true));
            return true;
        }
    }

    @Override
    public int compareTo(BankAccount other) {
        if (this.accountNumber > other.accountNumber) {
            return 1;
        } else if (this.accountNumber < other.accountNumber) {
            return -1;
        } else {
            return 0;
        }
    }

    public ArrayList<Transaction> getArrayListTransaction() {
        return arrListTransaction;
    }

    public abstract MonetaryValue availableAmount();
}

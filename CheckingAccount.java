public class CheckingAccount extends BankAccount {

    private MonetaryValue overdraftLimit;

    public CheckingAccount() {
        super();
        overdraftLimit = new MonetaryValue();
    }

    public CheckingAccount(int accountNumber, Name name, MonetaryValue balance, MonetaryValue overdraftLimit) {
        super(accountNumber, name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public CheckingAccount(CheckingAccount copyOf) {
        super(copyOf);
        this.overdraftLimit = copyOf.overdraftLimit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CheckingAccount) {
            CheckingAccount other = (CheckingAccount) obj;
            return super.equals(other) && this.overdraftLimit.equals(other.overdraftLimit);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Checking Account:\n" + super.toString() + "\n" + "Overdraft Limit:" + overdraftLimit;
    }

    @Override
    public MonetaryValue availableAmount() {
        MonetaryValue totalAmount = new MonetaryValue(getBalance().doubleValue() + overdraftLimit.doubleValue());
        return totalAmount;
    }

    public static CheckingAccount read(java.util.Scanner sc) {
        if (sc.hasNext()) {
            CheckingAccount newAccount = new CheckingAccount(sc.nextInt(), Name.read(sc), MonetaryValue.read(sc),
                    MonetaryValue.read(sc));
            return newAccount;
        }
        return null;
    }
    
}

public class SavingsAccount extends BankAccount {

    private static double annualInterestRate = 0.0;

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(int accountNumber, Name name, MonetaryValue balance) {
        super(accountNumber, name, balance);
    }

    public SavingsAccount(SavingsAccount old) {
        super(old);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SavingsAccount) {
            SavingsAccount other = (SavingsAccount) obj;
            return super.equals(other);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Savings Account:\n" + super.toString() + "\n" + "Annual Interest Rate: " + annualInterestRate + "%";
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        SavingsAccount.annualInterestRate = annualInterestRate;
    }

    public MonetaryValue getMonthlyInterest() {
        double generalMonthlyInterestRate = annualInterestRate / 12;
        MonetaryValue accountMonthIntRate = new MonetaryValue(
                getBalance().doubleValue() * (generalMonthlyInterestRate / 100));
        return accountMonthIntRate;
    }

    public static SavingsAccount read(java.util.Scanner sc) throws NegativeBalanceException {
        if (sc.hasNext()) {
            int accountNumber = sc.nextInt();
            Name name = Name.read(sc);
            MonetaryValue balance = MonetaryValue.read(sc);

            if (balance.isNegative()) {
                throw new NegativeBalanceException(balance);
            } else {
                return new SavingsAccount(accountNumber, name, balance);
            }
        }
        return null;
    }

    @Override
    public MonetaryValue availableAmount() {
        return balance;
    }
}

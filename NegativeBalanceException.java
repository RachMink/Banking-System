public class NegativeBalanceException extends Exception {

    public NegativeBalanceException() {
        super("Negative Balance");
    }

    public NegativeBalanceException(MonetaryValue balance) {
        super("Negative balance: " + balance);
    }
}

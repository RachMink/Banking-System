public class InsufficientFundException extends Exception {
    public InsufficientFundException() {
        super("trying to withdraw too much money");
    }

    public InsufficientFundException(MonetaryValue ammountWithdrawing, MonetaryValue available) {
        super("Inssufcient funds in account. \n Amount trying to withdraw: " + ammountWithdrawing
                + "\n Amount available in account: " + available);
    }
}

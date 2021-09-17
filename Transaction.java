public class Transaction {
    private String transactionType;
    private MonetaryValue amount;
    private MonetaryValue balance;
    private boolean wasSuccessful;

    public Transaction(String transactionType, MonetaryValue amount, MonetaryValue balance, boolean wasSuccessful) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.wasSuccessful = wasSuccessful;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Transaction) {
            Transaction newrefObj = (Transaction) obj;
            return this.transactionType.equals(newrefObj.transactionType) && this.amount.equals(newrefObj.amount)
                    && this.balance.equals(newrefObj.balance) && this.wasSuccessful == newrefObj.wasSuccessful;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Transaction Type: " + transactionType + "\nAmount " + amount + "\nNew Balance: " + balance
                + "\nWas Successful? " + wasSuccessful + "\n";
    }

}

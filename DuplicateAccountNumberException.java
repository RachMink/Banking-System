public class DuplicateAccountNumberException extends Exception {

    public DuplicateAccountNumberException() {
        super("This Account number already exists");
    }

    public DuplicateAccountNumberException(int accountNumber) {
        super("duplicate account number: " + accountNumber);
    }
}

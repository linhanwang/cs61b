/* BadTransactionException.java */

public class BadTransactionException extends Exception {
    public int amt;

    public BadTransactionException(int badAmt) {
        super("Invalid amount: " + badAmt);

        amt = badAmt;
    }
}

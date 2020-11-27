package exceptions;

public class NullObjectException extends Exception {
    public NullObjectException() {
        super("Some Object that was received is null");
    }
}

package exceptions;

public class EmptyException extends Exception {
    public EmptyException() {
        super("Something is Empty");
    }
}

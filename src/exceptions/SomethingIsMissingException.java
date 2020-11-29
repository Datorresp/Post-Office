package exceptions;

public class SomethingIsMissingException extends Exception {
    public SomethingIsMissingException() {
        super("Something is missing");
    }
}

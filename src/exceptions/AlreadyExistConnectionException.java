package exceptions;

import model.Wholesaler;

public class AlreadyExistConnectionException extends Exception {
    public AlreadyExistConnectionException(Wholesaler w1, Wholesaler w2) {
        super("Already exist a connection between \n" +
                "- "+w1+"\n"+
                "- "+w2);
    }
}

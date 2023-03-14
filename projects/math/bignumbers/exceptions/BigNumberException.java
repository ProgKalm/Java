package projects.math.bignumbers.exceptions;

public class BigNumberException extends Throwable {

    public BigNumberException(String message) {
        super("BigNumberException: %s".formatted(message));
    }

}

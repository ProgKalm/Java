package projects.math.bignumbers.exceptions;

public class InvalidNumberFormatException  extends BigNumberException {

    public InvalidNumberFormatException(String num) {
        super("Can't cast string '%s' to number".formatted(num));
    }
}

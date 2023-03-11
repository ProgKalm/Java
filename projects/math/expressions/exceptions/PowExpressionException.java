package projects.math.expressions.exceptions;

public class PowExpressionException extends ExpressionException {
    public PowExpressionException(Number degree) {
        super("Attempt to raise to the power of %s not included in the segment [%s; %s]".formatted(
                degree.toString(), Integer.MIN_VALUE, Integer.MAX_VALUE
        ));
    }
}

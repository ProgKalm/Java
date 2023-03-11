package projects.math.expressions.exceptions;

public class SubSqrtExpressionException extends  ExpressionException {

    public SubSqrtExpressionException(Number number) {
        super("Try getting sqrt from '%s'".formatted(number.toString()));
    }
}

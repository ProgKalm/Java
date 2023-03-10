package projects.math.expressions.exceptions;

import projects.math.expressions.classes.Expression;

public class DivisionByZeroException extends ExpressionException {


    public DivisionByZeroException(Expression expression) {
        super("Try calculate division by zero: %s".formatted(expression.toString()));
    }
}

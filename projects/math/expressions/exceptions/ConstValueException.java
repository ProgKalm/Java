package projects.math.expressions.exceptions;

public class ConstValueException extends ExpressionException {

    public ConstValueException(String invalidData) {
        super("Trying cast to number data equal '%s'".formatted(invalidData));
    }
}

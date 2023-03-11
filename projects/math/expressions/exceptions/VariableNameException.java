package projects.math.expressions.exceptions;

public class VariableNameException extends ExpressionException {
    public VariableNameException() {
        super("Try to create/get variable by empty-name.");
    }
}

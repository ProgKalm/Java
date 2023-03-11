package projects.math.expressions.parser.exception;

public class ParseInvalidException extends ParserExpressionException {

    public ParseInvalidException(String message) {
        super("Trying parse expression and getting exception with message: %s".formatted(message));
    }
}

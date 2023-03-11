package projects.math.expressions.parser.exception;

public class ParserInitializeException extends ParserExpressionException {

    public ParserInitializeException() {
        super("Try initialize parser by empty string or null-value string");
    }
}

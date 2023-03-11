package projects.math.expressions.parser.exception;

public class InvalidParseExpressionException extends ParserExpressionException {

    public InvalidParseExpressionException(boolean isNull) {
        super(InvalidParseExpressionException.getMsg(isNull));
    }

    private static String getMsg(boolean isNull) {
        if (isNull) {
            return "Result of parse is null.";
        }
        return "Parser stop working, but the entire line was not processed.";
    }
}

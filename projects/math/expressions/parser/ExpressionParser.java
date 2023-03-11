package projects.math.expressions.parser;

import projects.math.expressions.*;
import projects.math.expressions.exceptions.ConstValueException;
import projects.math.expressions.exceptions.ExpressionCastException;
import projects.math.expressions.exceptions.VariableNameException;
import projects.math.expressions.parser.exception.InvalidParseExpressionException;
import projects.math.expressions.parser.exception.ParseInvalidException;
import projects.math.expressions.parser.exception.ParserExpressionException;

public class ExpressionParser extends StringParser<Expression> {


    @Override
    public Expression parse(String data) throws ParserExpressionException {
        initialize(data);
        return parse();
    }

    private Expression parse() throws ParserExpressionException {
        Expression expression = parseExpression();
        if (expression == null || !isEndOfParse()) {
            throw new InvalidParseExpressionException(expression == null);
        }
        return expression;
    }

    private Expression parseExpression() throws ParserExpressionException {
        skipWhiteSpaces();
        Expression ex = parseTerm();
        while (!isEndOfParse()) {
            skipWhiteSpaces();
            if (find('+')) {
                try {
                    next();
                    ex = new Summary(ex, parseExpression());
                } catch (Exception e) {
                    throw new ParseInvalidException(e.getMessage());
                }
            } else if (find('-')) {
                try {
                    next();
                    ex = new Subtract(ex, parseExpression());
                } catch (Exception e) {
                    throw new ParseInvalidException(e.getMessage());
                }
            } else {
                break;
            }
        }
        return ex;
    }

    private Expression parseTerm() throws ParserExpressionException {
        skipWhiteSpaces();
        Expression ex = parseBinaryFuncs();
        while (!isEndOfParse()) {
            skipWhiteSpaces();
            if (find('*')) {
                try {
                    next();
                    ex = new Multiply(ex, parseTerm());
                } catch (Exception e) {
                    throw new ParseInvalidException(e.getMessage());
                }
            } else if (find('/')) {
                try {
                    next();
                    ex = new Division(ex, parseTerm());
                } catch (Exception e) {
                    throw new ParseInvalidException(e.getMessage());
                }
            } else {
                break;
            }
        }
        return ex;
    }

    private Expression parseBinaryFuncs() throws ParserExpressionException {
        skipWhiteSpaces();
        Expression ex = parseUnaryFuncs();
        while (!isEndOfParse()) {
            skipWhiteSpaces();
            if (find('^')) {
                try {
                    next();
                    ex = new Pow(ex, parseExpression());
                } catch (Exception e) {
                    throw new ParseInvalidException(e.getMessage());
                }
            } else {
                break;
            }
        }
        return ex;
    }

    private Expression parseUnaryFuncs() throws ParserExpressionException {
        skipWhiteSpaces();
        if (find('-')) {
            try {
                next();
                return new Negate(parseUnaryFuncs());
            } catch (ExpressionCastException e) {
                throw new ParseInvalidException(e.getMessage());
            }
        } else if (find('√')) {
            try {
                next();
                return new Sqrt(parseUnaryFuncs());
            } catch (ExpressionCastException e) {
                throw new ParseInvalidException(e.getMessage());
            }
        } else if (find('(')) {
            next();
            Expression ex = parseExpression();
            skipWhiteSpaces();
            if (find(')') && ex != null) {
                next();
                return ex;
            }
            throw new InvalidParseExpressionException(ex == null);
        } else if (Character.isDigit(getSymbol())) {
            try {
                return new Const(parseNumber());
            } catch (ConstValueException e) {
                throw new ParseInvalidException(e.getMessage());
            }
        } else if (Character.isAlphabetic(getSymbol())) {
            try {
                return new Variable(getVarName());
            } catch (VariableNameException e) {
                throw new ParseInvalidException(e.getMessage());
            }
        }
        return null;
    }

    private String getVarName() {
        StringBuilder builder = new StringBuilder();
        while (Character.isAlphabetic(getSymbol())) {
            builder.append(getSymbol());
            next();
        }
        return builder.toString();
    }

    private String parseNumber() throws ParseInvalidException {
        StringBuilder builder = new StringBuilder();
        int dotCount = 0;
        while (Character.isDigit(getSymbol()) || find('.')) {
            builder.append(getSymbol());
            if (find('.')) {
                dotCount++;
            }
            next();
        }
        if (dotCount > 1) {
            throw new ParseInvalidException("Try find number, but get its: %s".formatted(builder.toString()));
        }
        return builder.toString();
    }
}
/*
E -> T | T + E | T - E
T -> UF | UF * T | UF / T | BF | BF * T | BF / T
F -> func
*/
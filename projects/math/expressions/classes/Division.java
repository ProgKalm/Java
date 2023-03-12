package projects.math.expressions.classes;

import projects.math.expressions.exceptions.DivisionByZeroException;
import projects.math.expressions.exceptions.ExpressionCastException;

import java.math.BigDecimal;
import java.math.MathContext;

public class Division extends BinaryExpression {

    public Division(Expression first, Expression second) throws ExpressionCastException {
        super("/", first, second);
    }

    @Override
    protected BigDecimal calc(BigDecimal first, BigDecimal second) throws DivisionByZeroException {
        if (second.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException(this);
        }
        return first.divide(second, MathContext.DECIMAL64);
    }
}

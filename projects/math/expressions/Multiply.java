package projects.math.expressions;

import projects.math.expressions.exceptions.ExpressionCastException;

import java.math.BigDecimal;

public class Multiply extends BinaryExpression {

    public Multiply(Expression first, Expression second) throws ExpressionCastException {
        super("*", first, second);
    }

    @Override
    protected BigDecimal calc(BigDecimal first, BigDecimal second) {
        return first.multiply(second);
    }
}

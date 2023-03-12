package projects.math.expressions.classes;

import projects.math.expressions.exceptions.ExpressionCastException;

import java.math.BigDecimal;

public class Subtract extends BinaryExpression {

    public Subtract(Expression first, Expression second) throws ExpressionCastException {
        super("-", first, second);
    }

    @Override
    protected BigDecimal calc(BigDecimal first, BigDecimal second) {
        return first.subtract(second);
    }
}

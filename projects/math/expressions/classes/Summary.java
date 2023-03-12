package projects.math.expressions.classes;

import projects.math.expressions.exceptions.ExpressionCastException;

import java.math.BigDecimal;

public class Summary extends BinaryExpression {
    public Summary(Expression first, Expression second) throws ExpressionCastException {
        super("+", first, second);
    }

    @Override
    protected BigDecimal calc(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }
}

package projects.math.expressions;

import projects.math.expressions.exceptions.ExpressionCastException;
import projects.math.expressions.exceptions.SubSqrtExpressionException;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sqrt extends UnaryExpression {

    public Sqrt(Expression expression) throws ExpressionCastException {
        super("√", expression);
    }

    @Override
    protected BigDecimal calc(BigDecimal value) throws SubSqrtExpressionException {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new SubSqrtExpressionException(value);
        }
        return value.sqrt(MathContext.DECIMAL64);
    }
}

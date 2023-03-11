package projects.math.expressions;

import projects.math.expressions.exceptions.ExpressionCastException;
import java.math.BigDecimal;

public class Negate extends UnaryExpression {

    public Negate(Expression expression) throws ExpressionCastException {
        super("-", expression);
    }

    @Override
    protected BigDecimal calc(BigDecimal value) {
        return value.negate();
    }
}

package projects.math.expressions.classes;

import projects.math.expressions.exceptions.ExpressionCastException;
import projects.math.expressions.exceptions.ExpressionException;
import projects.math.expressions.exceptions.PowExpressionException;

import java.math.BigDecimal;

public class Pow extends BinaryExpression {
    public Pow(Expression first, Expression second) throws ExpressionCastException {
        super("^", first, second);
    }

    @Override
    protected BigDecimal calc(BigDecimal first, BigDecimal second) throws ExpressionException {
        try {
            return first.pow(second.intValueExact());
        } catch (ArithmeticException exception) {
            throw new PowExpressionException(second);
        }
    }

    @Override
    public String toString() {
        return super.toString().replace(" ", "");
    }
}

package projects.math.expressions.classes;

import projects.math.expressions.exceptions.ExpressionCastException;
import projects.math.expressions.exceptions.ExpressionException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class UnaryExpression implements Expression {

    private final String operand;
    private Expression expression;

    public UnaryExpression(String operand, Expression expression) throws ExpressionCastException {
        if (operand == null || expression == null || operand.length() == 0) {
            throw new ExpressionCastException("Try cast UnaryExpression with operand/expression equal null-value.");
        }
        this.operand = operand;
        this.expression = expression;
    }

    public Expression getSubExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        if (expression != null) {
            this.expression = expression;
        }
    }

    protected abstract BigDecimal calc(BigDecimal value) throws ExpressionException;

    @Override
    public BigDecimal calculate() throws ExpressionException {
        return calc(expression.calculate());
    }

    @Override
    public BigDecimal calculate(Map<String, Number> variablesValue) throws ExpressionException {
        return calc(expression.calculate(variablesValue));
    }

    @Override
    public Set<String> getVariablesNames() {
        return expression.getVariablesNames();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnaryExpression that)) {
            return false;
        }

        if (!Objects.equals(operand, that.operand)) {
            return false;
        }
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        int result = operand.hashCode();
        result = 31 * result + (expression != null ? expression.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "%s%s".formatted(operand, expression.toString());
    }
}

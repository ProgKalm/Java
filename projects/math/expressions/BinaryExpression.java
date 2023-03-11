package projects.math.expressions;

import projects.math.expressions.exceptions.DivisionByZeroException;
import projects.math.expressions.exceptions.ExpressionCastException;
import projects.math.expressions.exceptions.ExpressionException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class BinaryExpression implements Expression {

    private final String operand;
    private Expression firstSubExpression;
    private Expression secondSubExpression;

    public BinaryExpression(String operand, Expression first, Expression second) throws ExpressionCastException {
        if (operand == null || first == null || second == null || operand.length() == 0) {
            throw new ExpressionCastException("Try cast UnaryExpression with operand/expression equal null-value.");
        }
        this.operand = operand;
        firstSubExpression = first;
        secondSubExpression = second;
    }

    public Expression getFirstSubExpression() {
        return firstSubExpression;
    }

    public void setFirstSubExpression(Expression newValue) {
        if (newValue != null) {
            this.firstSubExpression = newValue;
        }
    }

    public Expression getSecondSubExpression() {
        return secondSubExpression;
    }

    public void setSecondSubExpression(Expression newValue) {
        if (newValue != null) {
            this.secondSubExpression = newValue;
        }
    }

    protected abstract BigDecimal calc(BigDecimal first, BigDecimal second) throws ExpressionException;

    @Override
    public BigDecimal calculate() throws Exception {
        return calc(firstSubExpression.calculate(), secondSubExpression.calculate());
    }

    @Override
    public BigDecimal calculate(Map<String, Number> variablesValue) throws Exception {
        return calc(firstSubExpression.calculate(variablesValue), secondSubExpression.calculate(variablesValue));
    }

    @Override
    public Set<String> getVariablesNames() {
        Set<String> set = firstSubExpression.getVariablesNames();
        set.addAll(secondSubExpression.getVariablesNames());
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BinaryExpression that)) {
            return false;
        }

        if (!Objects.equals(operand, that.operand)) {
            return false;
        }
        return Objects.equals(firstSubExpression, that.firstSubExpression) && Objects.equals(secondSubExpression, that.secondSubExpression);
    }

    @Override
    public int hashCode() {
        int result = operand.hashCode();
        result = 31 * result + (getFirstSubExpression() != null ? getFirstSubExpression().hashCode() : 0);
        result = 31 * result + (getSecondSubExpression() != null ? getSecondSubExpression().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(%s %s %s)".formatted(firstSubExpression.toString(), operand, secondSubExpression.toString());
    }
}

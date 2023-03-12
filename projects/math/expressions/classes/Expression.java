package projects.math.expressions.classes;

import projects.math.expressions.exceptions.ExpressionException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;


public interface Expression {

    BigDecimal calculate() throws ExpressionException;

    BigDecimal calculate(Map<String, Number> variablesValue) throws ExpressionException;

    Set<String> getVariablesNames();

    String toString();
}

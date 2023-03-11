package projects.math.expressions;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;


public interface Expression {

    BigDecimal calculate() throws Exception;

    BigDecimal calculate(Map<String, Number> variablesValue) throws Exception;

    Set<String> getVariablesNames();

    String toString();
}

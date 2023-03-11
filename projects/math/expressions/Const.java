package projects.math.expressions;

import projects.math.expressions.exceptions.ConstValueException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Const implements Expression {

    private final BigDecimal value;

    public Const() {
        value = BigDecimal.ZERO;
    }

    public Const(Number value) {
        if (value == null) {
            this.value = BigDecimal.ZERO;
        } else {
            this.value = new BigDecimal(value.toString());
        }
    }

    public Const(String value) throws ConstValueException {
        if (value == null || value.length() == 0) {
            this.value = BigDecimal.ZERO;
        } else {
            try {
                this.value = new BigDecimal(value);
            } catch (NumberFormatException exception) {
                throw new ConstValueException(value);
            }
        }
    }
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Const c)) return false;

        return Objects.equals(getValue(), c.getValue());
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }

    @Override
    public BigDecimal calculate() {
        return value;
    }

    @Override
    public BigDecimal calculate(Map<String, Number> variablesValue) {
        return value;
    }

    @Override
    public Set<String> getVariablesNames() {
        return new HashSet<>();
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}

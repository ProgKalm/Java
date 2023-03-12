package projects.math.expressions.classes;

import projects.math.expressions.exceptions.VariableNameException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Variable implements Expression {

    private final String name;

    public Variable(String name) throws VariableNameException {
        if (name == null || name.length() == 0) {
            throw new VariableNameException();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Variable variable)) {
            return false;
        }

        return getName() != null ? getName().equals(variable.getName()) : variable.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public BigDecimal calculate() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculate(Map<String, Number> variablesValue) {
        Number value = variablesValue.get(getName());
        if (value == null) {
            return calculate();
        }
        return new BigDecimal(value.toString());
    }

    @Override
    public Set<String> getVariablesNames() {
        return new HashSet<String>(Set.of(getName()));
    }

    @Override
    public String toString() {
        return getName();
    }
}

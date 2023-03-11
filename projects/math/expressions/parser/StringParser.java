package projects.math.expressions.parser;

import projects.math.expressions.parser.exception.ParserExpressionException;
import projects.math.expressions.parser.exception.ParserInitializeException;

public abstract class StringParser<T> {

    private String data;
    private int currentPos;
    private final char endSymbol = '\0';

    public abstract T parse(String data) throws Exception;

    protected void initialize(String stringToParse) throws ParserInitializeException {
        if (stringToParse == null || stringToParse.equals("")) {
            throw new ParserInitializeException();
        }
        data = stringToParse;
        currentPos = 0;
    }

    protected char getSymbol() {
        return getSymbol(currentPos);
    }

    protected char getSymbol(int index) {
        if (index < data.length() && index >= 0) {
            return data.charAt(index);
        }
        return endSymbol;
    }

    protected char getSymbolAndNext() {
        if (currentPos < data.length()) {
            char symbol = data.charAt(currentPos);
            next();
            return symbol;
        }
        return endSymbol;
    }

    protected void next() {
        currentPos++;
    }

    protected void prev() {
        if (currentPos != 0) {
            currentPos--;
        }
    }

    protected void skipWhiteSpaces() {
        while (Character.isWhitespace(getSymbol())) {
            next();
        }
    }

    protected boolean find(char symbol) {
        return find(symbol, currentPos);
    }

    protected boolean find(String sub) {
        if (sub == null || sub.length() == 0 || sub.length() > data.length()) {
            return false;
        } else if (sub.length() == data.length()) {
            return sub.equals(data);
        }
        for (int i = 0; i < sub.length(); i++) {
            if (find(sub.charAt(i), currentPos + i)) {
                return false;
            }
        }
        return true;

    }

    private boolean find(char symbol, int index) {
        if (index < 0 || index >= data.length() || symbol == endSymbol) {
            return false;
        }
        return symbol == getSymbol(index);
    }

    protected boolean isEndOfParse() {
        return getSymbol() == endSymbol;
    }
}

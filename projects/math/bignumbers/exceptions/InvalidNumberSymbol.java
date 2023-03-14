package projects.math.bignumbers.exceptions;

public class InvalidNumberSymbol extends BigNumberException {

    public InvalidNumberSymbol(char symbol) {
        super("Uncorrected symbol %s by int-code %d. It must be more than %d".formatted(
                symbol,
                (int) symbol,
                (int) '0'
        ));
    }

    public InvalidNumberSymbol(char symbol, int base) {
        super("Invalid symbol %s to convert it base %d, int-code %d because must be %d <= int-code <= %d".formatted(
                symbol,
                base,
                (int) symbol,
                (int) '0',
                base
        ));
    }


}

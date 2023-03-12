package projects.markup.exceptions;

public class NullInitializeException extends NullValueException {

    public NullInitializeException() {
        super("Initialize markup element by null value");
    }
}

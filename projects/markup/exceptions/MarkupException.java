package projects.markup.exceptions;

public abstract class MarkupException extends Exception {
    public MarkupException(String message) {
        super("MarkupException: %s".formatted(message));
    }
}

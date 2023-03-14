package projects.markup.exceptions;

public class ElementInitException extends MarkupException {

    public ElementInitException(String className, String cause) {
        super("Try init class %s but get cause: '%s'".formatted(className, cause));
    }
}

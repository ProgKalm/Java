package projects.markup;

public class LineBreak implements MarkupElement {
    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append("<br>");
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        builder.append(System.lineSeparator());
    }

    @Override
    public int length() {
        return System.lineSeparator().length();
    }

    @Override
    public String toString() {
        return System.lineSeparator();
    }
}

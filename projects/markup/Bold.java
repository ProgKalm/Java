package projects.markup;

public record Bold(MarkupElement element) implements MarkupElement {

    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append("<strong>");
        element.toHtmlString(builder);
        builder.append("</strong>");
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        builder.append("**");
        element.toMarkDownString(builder);
        builder.append("**");
    }

    @Override
    public int length() {
        return element.length();
    }
}

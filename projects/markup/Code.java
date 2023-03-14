package projects.markup;

public record Code(MarkupElement element) implements MarkupElement {

    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append("<code>");
        element.toHtmlString(builder);
        builder.append("</code>");
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        builder.append("`");
        element.toMarkDownString(builder);
        builder.append("`");
    }

    @Override
    public int length() {
        return element.length();
    }
}

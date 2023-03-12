package projects.markup;

public record Italic(MarkupElement element) implements MarkupElement {

    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append("<em>");
        element.toHtmlString(builder);
        builder.append("</em>");
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        builder.append("_");
        element.toMarkDownString(builder);
        builder.append("_");
    }

    @Override
    public int length() {
        return element.length();
    }
}

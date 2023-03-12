package projects.markup;

import projects.markup.exceptions.NullInitializeException;

public class Link extends MarkupSpecialElement {

    public Link(MarkupElement title, String url) throws NullInitializeException {
        super(title, url);
    }

    public Link(MarkupElement title, String url, String inline) throws NullInitializeException {
        super(title, url, inline);
    }

    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append("<a href=\"").append(getUrl()).append("\" title=\"");
        builder.append(getInline()).append("\">");
        getTitle().toHtmlString(builder);
        builder.append("</a>");
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        builder.append("[");
        getTitle().toMarkDownString(builder);
        builder.append("](").append(getUrl()).append(" \"").append(getInline()).append("\")");

    }
}

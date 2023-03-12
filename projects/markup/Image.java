package projects.markup;

import projects.markup.exceptions.NullInitializeException;

public class Image extends MarkupSpecialElement {

    public Image(MarkupElement title, String url) throws NullInitializeException {
        super(title, url);
    }

    public Image(MarkupElement title, String url, String inline) throws NullInitializeException {
        super(title, url, inline);
    }

    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append("<img src=\"").append(getUrl()).append("\" alt=\"");
        getTitle().toHtmlString(builder);
        builder.append("\" title=\"").append(getInline()).append("\"/>");
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        builder.append("![");
        getTitle().toMarkDownString(builder);
        builder.append("](").append(getUrl()).append(" \"").append(getInline()).append("\")");

    }
}

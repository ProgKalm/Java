package projects.markup;

import projects.markup.exceptions.NullInitializeException;

public abstract class MarkupSpecialElement implements MarkupElement {

    private String inline;
    private MarkupElement title;
    private String url;

    public MarkupSpecialElement(MarkupElement title, String url) throws NullInitializeException {
        setInline("");
        setTitle(title);
        setUrl(url);
    }

    public MarkupSpecialElement(MarkupElement title, String url, String inline) throws NullInitializeException {
        setInline(inline);
        setTitle(title);
        setUrl(url);
    }

    public String getInline() {
        return inline;
    }

    public void setInline(String inline) {
        if (inline != null) {
            this.inline = inline;
        } else {
            this.inline = "";
        }
    }

    public MarkupElement getTitle() {
        return title;
    }

    public void setTitle(MarkupElement title) throws NullInitializeException {
        if (title != null) {
            this.title = title;
        } else {
            throw new NullInitializeException();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws NullInitializeException {
        if (url != null) {
            this.url = url;
        } else {
            throw new NullInitializeException();
        }
    }

    @Override
    public int length() {
        return url.length() + title.length() + inline.length();
    }
}

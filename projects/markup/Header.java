package projects.markup;

import projects.markup.exceptions.ElementInitException;

public class Header implements MarkupElement {

    private final MarkupElement text;
    private int level;

    public Header(int level, MarkupElement headerText) throws ElementInitException {
        if (level <= 0 || level > 6 || headerText == null) {
            throw new ElementInitException(getClass().getName(), getCause(level, headerText));
        }
        this.level = level;
        this.text = headerText;
    }

    public MarkupElement getText() {
        return text;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) throws ElementInitException {
        if (level <= 0 || level > 6) {
            throw new ElementInitException(getClass().getName(), getCause(level, null));
        }
        this.level = level;
    }

    private String getCause(int level, MarkupElement headerText) {
        if (level < 0 || level > 6) {
            return "Getting invalid header level %d. It can be from 1 to 6".formatted(level);
        }
        return "Getting MarkupElement as null.";
    }

    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append("<h%d>".formatted(getLevel()));
        text.toHtmlString(builder);
        builder.append("</h%d>".formatted(getLevel()));
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        builder.append("#".repeat(level));
        text.toHtmlString(builder);
        builder.append(System.lineSeparator());
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public String toString() {
        return "Header{" + "text=" + text + '}';
    }

}

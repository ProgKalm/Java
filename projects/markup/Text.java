package projects.markup;

import projects.markup.exceptions.NullInitializeException;


public class Text implements MarkupElement {

    private final StringBuilder text;

    public Text() {
        text = new StringBuilder("");
    }

    public Text(String text) throws NullInitializeException {
        if (text == null) {
            throw new NullInitializeException();
        }
        this.text = new StringBuilder(text);
    }

    public Text(String text, boolean isRaised) throws NullInitializeException {
        if (text == null && isRaised) {
            throw new NullInitializeException();
        } else if (text == null) {
            text = "";
        }
        this.text = new StringBuilder(text);
    }

    public void addEnd(String text) {
        this.text.append(text);
    }

    public void addFront(String text) {
        this.text.insert(0, text);
    }

    public void insert(int index, String text) {
        if (index < 0) {
            addFront(text);
        } else if (index >= text.length()) {
            addEnd(text);
            return;
        }
        this.text.insert(index, text);
    }

    public String remove(int from, int to) {
        return replace(from, to, "");
    }

    public String replace(int from, int to, String sub) {
        from = checkIndex(from);
        to = checkIndex(to);
        if (from >= to) {
            int tmp = from;
            from = to;
            to = tmp;
        }
        String s = substring(from, to);
        text.replace(from, to, sub);
        return s;
    }

    public String substring(int from, int to) {
        from = checkIndex(from);
        to = checkIndex(to);
        if (from >= to) {
            int tmp = from;
            from = to;
            to = tmp;
        }
        return text.substring(from, to);
    }

    public void toUpperCase() {
        text.append(clear().toUpperCase());
    }

    public void toLowerCase() {
        text.append(clear().toLowerCase());
    }

    public String clear() {
        String last = getText();
        text.delete(0, text.length());
        return last;
    }

    private int checkIndex(int i) {
        if (i < 0) {
            return 0;
        } else if (i > text.length()) {
            return text.length();
        }
        return i;
    }

    public String getText() {
        return text.toString();
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public String toString() {
        return "MarkupElement.Text{text='%s'}".formatted(getText());
    }

    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append(getText());
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        builder.append(getText());
    }
}

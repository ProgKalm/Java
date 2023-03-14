package projects.markup;

import java.util.List;

public class Paragraph extends MarkupElementContainer {

    public Paragraph(MarkupElement... elements) {
        super(elements);
    }

    public Paragraph(List<MarkupElement> elements) {
        super(elements);
    }

    @Override
    public void toHtmlString(StringBuilder builder) {
        builder.append("<p>");
        for (MarkupElement element : getElements()) {
            element.toHtmlString(builder);
        }
        builder.append("</p>");
    }

    @Override
    public void toMarkDownString(StringBuilder builder) {
        for (MarkupElement element : getElements()) {
            element.toMarkDownString(builder);
        }
    }

    @Override
    public String toString() {
        return "Paragraph{" + "elements=" + elements + '}';
    }
}

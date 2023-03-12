package projects.markup;

import java.util.LinkedList;
import java.util.List;

public abstract class MarkupElementContainer implements MarkupElement {

    protected final List<MarkupElement> elements;

    public MarkupElementContainer(MarkupElement... elements) {
        this.elements = new LinkedList<>(List.of(elements));
    }

    public MarkupElementContainer(List<MarkupElement> elements) {
        this.elements = new LinkedList<>(elements);
    }

    @Override
    public int length() {
        int len = 0;
        for (MarkupElement element: elements) {
            len += element.length();
        }
        return len;
    }

    public List<MarkupElement> getElements() {
        return elements;
    }
}

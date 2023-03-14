package projects.markup;

public interface MarkupElement {

    void toHtmlString(StringBuilder builder);

    void toMarkDownString(StringBuilder builder);

    int length();

}

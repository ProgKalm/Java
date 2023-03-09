package advance.datastructures.amortisations;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SimpleStack<T> {

    private StackNode<T> head;
    private int size;

    @SafeVarargs
    public SimpleStack(T... values) {
        size = values.length;
        for (T value : values) {
            push(value);
        }
    }

    public void push(T value) {
        StackNode<T> newHead = new StackNode<>(value);
        newHead.setPrev(head);
        head = newHead;
        size++;
    }

    public T pop() {
        if (getSize() > 0) {
            T value = head.getValue();
            head = head.getPrev();
            size--;
            return value;
        }
        return null;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        StackNode<T> node = head;
        while (node != null) {
            builder.append(node);
            node = node.getPrev();
            if (node != null) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    public List<T> toList() {
        List<T> result = new LinkedList<>();
        StackNode<T> node = head;
        while (node != null) {
            result.add(node.getValue());
            node = node.getPrev();
        }
        return result;
    }

    public SimpleStack<T> reverse() {
        StackNode<T> node = head;
        SimpleStack<T> stack = new SimpleStack<>();
        while (node != null) {
            stack.push(node.getValue());
            node = node.getPrev();
        }
        return stack;
    }

    public void clear() {
        size = 0;
        head = null;
    }

    public SimpleStack<T> copy() {
        SimpleStack<T> stack = new SimpleStack<>();
        List<T> list = toList();
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }
        return stack;
    }

    public T[] toArray() {
        return (T[]) toList().toArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleStack<?> that)) {
            return false;
        }
        if (getSize() != that.getSize()) {
            return false;
        }
        return Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + getSize();
        return result;
    }

    private static class StackNode<N> {
        private StackNode<N> prev;
        private N value;

        public StackNode(N value) {
            this.value = value;
        }

        public StackNode<N> getPrev() {
            return prev;
        }

        public void setPrev(StackNode<N> prev) {
            this.prev = prev;
        }

        public N getValue() {
            return value;
        }

        public void setValue(N value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return getValue().toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof StackNode<?> stackNode)) {
                return false;
            }
            if (getPrev() != null ? !getPrev().equals(stackNode.getPrev()) : stackNode.getPrev() != null) {
                return false;
            }
            return getValue() != null ? getValue().equals(stackNode.getValue()) : stackNode.getValue() == null;
        }

        @Override
        public int hashCode() {
            int result = getPrev() != null ? getPrev().hashCode() : 0;
            result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
            return result;
        }
    }

}

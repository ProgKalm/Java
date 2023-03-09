package advance.datastructures.amortisations;

import java.util.Objects;

public class SinglyLinkedList<T> {

    private Node<T> head;
    private int size;

    @SafeVarargs
    public SinglyLinkedList(T... values) {
        size = values.length;
        head = null;
        for (T value : values) {
            add(value);
        }
    }

    public T get(int index) {
        if (isValidIndex(index)) {
            Node<T> node = getNodeByIndex(index);
            return node.getValue();
        }
        return null;
    }

    public void set(int index, T value) {
        if (isValidIndex(index)) {
            Node<T> node = getNodeByIndex(index);
            if (node != null) {
                node.setValue(value);
            }
        } else if (index == size) {
            add(value);
        }
    }

    public void insert(int index, T value) {
        if (isValidIndex(index)) {
            Node<T> prevNode = null;
            if (index > 0) {
                prevNode = getNodeByIndex(index - 1);
            }
            Node<T> insertNode = new Node<>(value);
            Node<T> nextNode = getNodeByIndex(index);
            insertNode.setNext(nextNode);
            if (prevNode == null) {
                head = insertNode;
            } else {
                prevNode.setNext(insertNode);
            }
            size++;
        } else if (index == size) {
            add(value);
        }

    }

    public T delete(int index) {
        if (isValidIndex(index)) {
            T value;
            if (index == 0) {
                value = head.getValue();
                head = head.getNext();
            } else {
                Node<T> prevNode = getNodeByIndex(index - 1);
                Node<T> delNode = prevNode.getNext();
                prevNode.setNext(delNode.getNext());
                value = delNode.getValue();
            }
            size--;
            return value;
        }
        return null;
    }

    public T delete() {
        return delete(size - 1);
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            Node<T> lastNode = getLastNode();
            lastNode.setNext(node);
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        size= 0;
        head = null;
    }

    public SinglyLinkedList<T> copy() {
        return new SinglyLinkedList<>(toArray());
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[getSize()];
        int index = 0;
        Node<T> node = head;
        while (node != null) {
            array[index] = node.getValue();
            node = node.getNext();
        }
        return array;
    }

    private Node<T> getLastNode() {
        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }
        return node;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("(");
        Node<T> node = head;
        while (node != null) {
            builder.append(node);
            node = node.getNext();
            if (node != null) {
                builder.append(" -> ");
            }
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SinglyLinkedList<?> that)){
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

    private static class Node<N> {

        private N value;
        private Node<N> next;

        public Node(N value) {
            this.value = value;
            this.next = null;
        }

        public Node(N value, Node<N> next) {
            this.value = value;
            this.next = next;
        }

        public N getValue() {
            return value;
        }

        public void setValue(N value) {
            this.value = value;
        }

        public Node<N> getNext() {
            return next;
        }

        public void setNext(Node<N> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node<?> node)) {
                return false;
            }
            if (getValue() != null ? !getValue().equals(node.getValue()) : node.getValue() != null) return false;
            return getNext() != null ? getNext().equals(node.getNext()) : node.getNext() == null;
        }

        @Override
        public int hashCode() {
            int result = getValue() != null ? getValue().hashCode() : 0;
            result = 31 * result + (getNext() != null ? getNext().hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return value.toString();
        }


    }

}

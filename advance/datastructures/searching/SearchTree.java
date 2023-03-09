package advance.datastructures.searching;

import java.util.Arrays;
import java.util.Objects;

public class SearchTree<T> {

    private TreeNode<T> root;

    @SafeVarargs
    public SearchTree(T... values) {
        root = null;
        for (T value : values) {
            insert(value);
        }
    }

    public boolean search(T key) {
        TreeNode<T> node = root;
        while (node != null) {
            if (Objects.equals(node.getValue(), key)) {
                break;
            }
            if (compareIsLow(node.getValue(), key)) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }

        }
        return node != null;
    }

    public T max() {
        if (!isEmpty()) {
            return findMax(root).getValue();
        }
        return null;
    }

    private TreeNode<T> findMax(TreeNode<T> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    public T min() {
        if (!isEmpty()) {
            return findMin(root).getValue();
        }
        return null;
    }

    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private TreeNode<T> insert(TreeNode<T> node, T value) {
        if (node == null) {
            return new TreeNode<>(value);
        }
        if (compareIsLow(node.getValue(), value)) {
            node.setRight(insert(node.getRight(), value));
        } else if (compareIsLow(value, node.getValue())) {
            node.setLeft(insert(node.getLeft(), value));
        }
        return node;
    }

    public void delete(T value) {
        root = delete(root, value);
    }

    private TreeNode<T> delete(TreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (compareIsLow(node.getValue(), value)) {
            node.setRight(delete(node.getRight(), value));
        } else if (compareIsMore(node.getValue(), value)) {
            node.setLeft(delete(node.getLeft(), value));
        } else if (node.getLeft() != null && node.getRight() != null) {
            TreeNode<T> minNode = findMin(node.getRight());
            node.setValue(minNode.getValue());
            node.setRight(delete(node.getRight(), minNode.getValue()));
        } else {
            if (node.getLeft() != null) {
                node = node.getLeft();
            } else if (node.getRight() != null) {
                node = node.getRight();
            } else {
                node = null;
            }
        }
        return node;
    }

    public SearchTree<T> getSubTree(int index) {
        return null;
    }

    public SearchTree<T> getSubTree(T value) {
        return null;
    }



    public T[] inorderTraversal() {
        T[] array = (T[]) new Object[getSize()];
        inorderTraversal(array, root, 0);
        return array;
    }

    public T[] preorderTraversal() {
        T[] array = (T[]) new Object[getSize()];
        preorderTraversal(array, root, 0);
        return array;
    }

    public T[] postorderTraversal() {
        T[] array = (T[]) new Object[getSize()];
        postorderTraversal(array, root, 0);
        return array;
    }

    private int inorderTraversal(T[] array, TreeNode<T> node, int index) {
        if (isValidIndex(index) && node != null) {
            index = inorderTraversal(array, node.getLeft(), index);
            array[index] = node.getValue();
            index++;
            return inorderTraversal(array, node.getRight(), index);
        }
        return index;
    }

    private int preorderTraversal(T[] array, TreeNode<T> node, int index) {
        if (isValidIndex(index) && node != null) {
            array[index] = node.getValue();
            index++;
            index = preorderTraversal(array, node.getLeft(), index);
            return preorderTraversal(array, node.getRight(), index);
        }
        return index;
    }

    private int postorderTraversal(T[] array, TreeNode<T> node, int index) {
        if (isValidIndex(index) && node != null) {
            index = postorderTraversal(array, node.getLeft(), index);
            index = postorderTraversal(array, node.getRight(), index);
            array[index] = node.getValue();
            index++;
            return index;
        }
        return index;
    }

    private boolean isValidIndex(int index) {
        return 0 <= index && index < getSize();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        if (root == null) {
            return 0;
        }
        return root.getSize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SearchTree<?> that)) {
            return false;
        }
        if (getSize() != that.getSize()) {
            return false;
        }
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        int result = root != null ? root.hashCode() : 0;
        result = 31 * result + getSize() * 25;
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(inorderTraversal());
    }

    private boolean compareIsLow(T first, T second) {
        return Objects.hashCode(first) < Objects.hashCode(second);
    }

    private boolean compareIsMore(T first, T second) {
        return Objects.hashCode(first) > Objects.hashCode(second);
    }

    private static class TreeNode<N> {
        private N value;
        private TreeNode<N> left;
        private TreeNode<N> right;

        public TreeNode(N value) {
            this.value = value;
        }

        public TreeNode(TreeNode<N> left, TreeNode<N> right, N value) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public N getValue() {
            return value;
        }

        public void setValue(N value) {
            this.value = value;
        }

        public TreeNode<N> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<N> left) {
            this.left = left;
        }

        public TreeNode<N> getRight() {
            return right;
        }

        public void setRight(TreeNode<N> right) {
            this.right = right;
        }

        public int getSize() {
            int left = getLeft() == null ? 0 : getLeft().getSize();
            int right = getRight() == null ? 0 : getRight().getSize();
            return 1 + left + right;
        }

        public boolean isHasChild() {
            return getLeft() != null || getRight() != null;
        }

        @Override
        public String toString() {
            return Objects.toString(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof TreeNode<?> treeNode)) {
                return false;
            }
            return Objects.equals(getValue(), treeNode.getValue()) &&
                    Objects.equals(getLeft(), treeNode.getLeft()) &&
                    Objects.equals(getRight(), treeNode.getRight());
        }

        @Override
        public int hashCode() {
            int result = getValue() != null ? getValue().hashCode() : 0;
            result = 31 * result + (getLeft() != null ? getLeft().hashCode() : 0);
            result = 31 * result + (getRight() != null ? getRight().hashCode() : 0);
            return result;
        }
    }

}
/*
 */
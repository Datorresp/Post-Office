package dataStructures;

public class Node<T> {
    private T type;
    private Node<T> next;
    private Node<T> prev;

    public Node(T type, Node<T> next) {
        this.type = type;
        this.next = next;
        prev = null;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}